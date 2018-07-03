
package com.gws.newframe.configuration;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.JedisPool;


/**
 * redis配置
 *
 * @author wangdong  2016年4月15日 下午3:07:39
 */
@Configuration
public class JedisConfig {

    @Value("${redis.host}")
    private String host;

    @Value("${redis.port}")
    private int port;

    @Value("${redis.password}")
    private String password;

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName(host);
        factory.setPort(port);
        if (StringUtils.isNotEmpty(password)){
            factory.setPassword(password);
        }
        return factory;
    }

    @Bean
    public JedisPool redisPoolFactory() {

        return new JedisPool();
    }

}
	