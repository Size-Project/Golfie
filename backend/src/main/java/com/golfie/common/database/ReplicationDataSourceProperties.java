package com.golfie.common.database;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
@ConfigurationProperties("spring.datasource")
public class ReplicationDataSourceProperties {

    private final Master master = new Master();
    private final Map<String, Slave> slaves = new HashMap<>();

    @Setter
    @Getter
    public static class Master {
        private String id;
        private String driverClassName;
        private String url;
        private String username;
        private String password;
    }

    @Setter
    @Getter
    public static class Slave {
        private String name;
        private String driverClassName;
        private String url;
        private String username;
        private String password;
    }

}

