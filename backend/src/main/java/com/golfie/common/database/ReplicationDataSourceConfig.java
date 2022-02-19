package com.golfie.common.database;

import com.golfie.common.database.ReplicationDataSourceProperties.Slave;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import javax.sql.DataSource;
import java.util.LinkedHashMap;
import java.util.Map;

@Profile("dev")
@RequiredArgsConstructor
@EnableConfigurationProperties(ReplicationDataSourceProperties.class)
@Configuration
public class ReplicationDataSourceConfig {

    private final ReplicationDataSourceProperties dataSourceProperties;
    private final JpaProperties jpaProperties;

    @Bean
    public DataSource routingDataSource() {
        Map<Object, Object> dataSources = new LinkedHashMap<>();
        DataSource masterDateSource = createMasterDateSource();
        dataSources.put("master", masterDateSource);

        for (Slave slave : dataSourceProperties.getSlaves().values()) {
            DataSource slaveDataSource = createSlaveDataSource(slave);
            dataSources.put(slave.getName(), slaveDataSource);
        }

        ReplicationDataSourceRouter replicationRoutingDataSource = new ReplicationDataSourceRouter();
        replicationRoutingDataSource.setDefaultTargetDataSource(masterDateSource);
        replicationRoutingDataSource.setTargetDataSources(dataSources);
        return replicationRoutingDataSource;
    }

    @Bean
    public DataSource dataSource() {
        return new LazyConnectionDataSourceProxy(routingDataSource());
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource());
        entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactory.setJpaPropertyMap(jpaProperties.getProperties());
        entityManagerFactory.setPackagesToScan("com.golfie");
        return entityManagerFactory;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return jpaTransactionManager;
    }

    public DataSource createMasterDateSource() {
        ReplicationDataSourceProperties.Master master = dataSourceProperties.getMaster();
        return createDataSource(
                master.getDriverClassName(),
                master.getUrl(),
                master.getUsername(),
                master.getPassword()
        );
    }

    public DataSource createSlaveDataSource(Slave slave) {
        return createDataSource(
                slave.getDriverClassName(),
                slave.getUrl(),
                slave.getUsername(),
                slave.getPassword()
        );
    }

    private DataSource createDataSource(String driverClassName, String url, String username, String password) {
        return DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .url(url)
                .driverClassName(driverClassName)
                .username(username)
                .password(password)
                .build();
    }
}
