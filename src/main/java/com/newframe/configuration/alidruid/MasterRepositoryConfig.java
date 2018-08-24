package com.newframe.configuration.alidruid;

import com.newframe.utils.query.BaseRepositoryEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactoryPrimary" ,
        basePackages = "com.newframe.repositories.dataMaster",
        repositoryBaseClass = BaseRepositoryEx.class)
public class MasterRepositoryConfig {

    @Autowired
    @Qualifier("masterDataSource")
    private DataSource masterDS;

    @Autowired
    private JpaProperties jpaProperties;

    @Bean("entityManagerPrimary")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder){
        return entityManagerFactoryPrimary(builder).getObject().createEntityManager();
    }

    @Primary
    @Bean("entityManagerFactoryPrimary")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary(EntityManagerFactoryBuilder entityManagerFactoryBuilder){
        return entityManagerFactoryBuilder.dataSource(masterDS)
                .properties(getProperties())
                .packages("com.newframe.entity")
                .persistenceUnit("primaryPersistenceUnit")
                .build();
    }

    private Map<String, Object> getProperties(){
        return jpaProperties.getHibernateProperties(new HibernateSettings());
    }

}
