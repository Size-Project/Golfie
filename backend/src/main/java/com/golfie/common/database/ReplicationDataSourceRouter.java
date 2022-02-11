package com.golfie.common.database;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.stream.Collectors.toList;

public class ReplicationDataSourceRouter extends AbstractRoutingDataSource {
    public static final String DATASOURCE_KEY_MASTER = "master";
    public static final String DATASOURCE_KEY_SLAVE = "slave";

    private DataSourceNames<String> slaveNames;

    @Override
    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        super.setTargetDataSources(targetDataSources);

        this.slaveNames = new DataSourceNames<>(
                targetDataSources.keySet()
                        .stream()
                        .map(Object::toString)
                        .filter(name -> name.contains(DATASOURCE_KEY_SLAVE))
                        .collect(toList())
        );
    }

    @Override
    protected Object determineCurrentLookupKey() {
        boolean isReadOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();

        if (isReadOnly) {
            String slaveName = slaveNames.getNext();
            logger.info("Connection " + slaveName);
            return slaveName;
        }

        logger.info("Connection Master");
        return DATASOURCE_KEY_MASTER;
    }

    public static class DataSourceNames<T> {
        private final List<T> slaveNames;
        private final AtomicInteger sequence;

        public DataSourceNames(List<T> slaveNames) {
            this.slaveNames = slaveNames;
            sequence = new AtomicInteger(0);
        }

        public T getNext() {
            if (sequence.get() >= slaveNames.size()) {
                sequence.set(0);
            }
            return slaveNames.get(sequence.getAndAdd(1));
        }
    }

}
