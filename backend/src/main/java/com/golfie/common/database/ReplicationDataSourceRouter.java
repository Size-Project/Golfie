package com.golfie.common.database;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class ReplicationDataSourceRouter extends AbstractRoutingDataSource {
    public static final String DATASOURCE_KEY_MASTER = "master";

    private DataSourceNames<String> dataSourceNames;

    @Override
    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        super.setTargetDataSources(targetDataSources);

        this.dataSourceNames = new DataSourceNames<>(
                targetDataSources.keySet()
                        .stream()
                        .map(Object::toString)
                        .collect(toList())
        );
    }

    @Override
    protected Object determineCurrentLookupKey() {
        boolean isReadOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();

        if (isReadOnly) {
            String dataSourceName = dataSourceNames.getNext();
            logger.info("Connection " + dataSourceName);
            return dataSourceName;
        }

        logger.info("Connection Master");
        return DATASOURCE_KEY_MASTER;
    }

    public static class DataSourceNames<T> {
        private final List<T> dataSourceNames;
        private int sequence = 0;

        public DataSourceNames(List<T> dataSourceNames) {
            this.dataSourceNames = dataSourceNames;
        }

        public T getNext() {
            if (sequence >= dataSourceNames.size()) {
                sequence = 0;
            }
            return dataSourceNames.get(sequence++);
        }
    }

}
