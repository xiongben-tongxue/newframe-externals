package com.newframe.configuration.alidruid;

import com.newframe.utils.query.BaseRepositoryEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

/**
 * @author:wangdong
 * @description:主库的配置
 */
@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactorySlave" ,
        basePackages = "com.newframe.repositories.dataSlave",
        repositoryBaseClass = BaseRepositoryEx.class)
public class SlaveRepositoryConfig {

    @Autowired
    @Qualifier("slaveDataSource")
    private DataSource slaveDS;

    @Autowired
    private JpaProperties jpaProperties;

    @Bean("entityManagerSecondary")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder){
        return entityManagerFactorySlave(builder).getObject().createEntityManager();
    }

    @Bean("entityManagerFactorySlave")
    public LocalContainerEntityManagerFactoryBean entityManagerFactorySlave(EntityManagerFactoryBuilder entityManagerFactoryBuilder){
        return entityManagerFactoryBuilder.dataSource(slaveDS)
                .properties(getProperties())
                .packages("com.newframe.entity")
                .persistenceUnit("secondaryPersistenceUnit")
                .build();
    }

    private Map<String, Object> getProperties(){
        return jpaProperties.getHibernateProperties(new HibernateSettings());
    }

}
