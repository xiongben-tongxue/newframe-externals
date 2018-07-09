package com.gws.newframe.configuration.alidruid;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

/**
 * @author:wangdong
 * @description:多数据源配置
 */
@Configuration
public class MultiDataSourceConfig {

    @Primary
    @Bean(initMethod = "init",name = "masterDataSource")
    @ConfigurationProperties("spring.datasource.druid.master")
    public DataSource dataSourceMaster(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(initMethod = "init",name = "slaveDataSource")
    @ConfigurationProperties("spring.datasource.druid.slave")
    public DataSource dataSourceSlave(){
        return DruidDataSourceBuilder.create().build();
    }

}
