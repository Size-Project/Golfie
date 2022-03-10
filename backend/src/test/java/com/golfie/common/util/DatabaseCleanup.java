package com.golfie.common.util;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Profile("test")
@Component
public class DatabaseCleanup implements InitializingBean {

    @PersistenceContext
    private EntityManager entityManager;

    private List<String> tableNames;

    private List<String> relationTableNames = new ArrayList<>();

    @Override
    public void afterPropertiesSet() {
        tableNames = entityManager.getMetamodel().getEntities()
                .stream()
                .filter(e -> e.getJavaType().getAnnotation(Entity.class) != null)
                .map(e -> e.getName().toLowerCase())
                .collect(Collectors.toList());

        relationTableNames.add("user_relations");
        relationTableNames.add("feed_image_urls");
        relationTableNames.add("rounding_attendee");
    }

    @Transactional
    public void execute() {
        entityManager.flush();
        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE").executeUpdate();

        for (String tableName : tableNames) {
            entityManager.createNativeQuery("TRUNCATE TABLE " + tableName).executeUpdate();
            entityManager.createNativeQuery("ALTER TABLE " + tableName + " ALTER COLUMN " +
                    "ID RESTART WITH 1").executeUpdate();
        }

        for (String relationTableName : relationTableNames) {
            entityManager.createNativeQuery("TRUNCATE TABLE " + relationTableName).executeUpdate();
        }

        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate();
    }
}
