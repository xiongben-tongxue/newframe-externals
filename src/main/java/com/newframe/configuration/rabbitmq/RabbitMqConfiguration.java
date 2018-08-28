package com.newframe.configuration.rabbitmq;

import com.newframe.utils.rabbitmq.RabbitMqFastJsonConverter;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbitmq 相关配置
 * @author wangdong
 */
@Configuration
public class RabbitMqConfiguration {


    /**
     * 配置消息队列模版
     * 并且设置MessageConverter为自定义FastJson转换器
     * @param connectionFactory
     * @return
     */
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(new RabbitMqFastJsonConverter());
        return template;
    }

    /**
     * 自定义队列容器工厂
     * 并且设置MessageConverter为自定义FastJson转换器
     * @param connectionFactory
     * @return
     */
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new RabbitMqFastJsonConverter());
        factory.setDefaultRequeueRejected(false);
        return factory;
    }

}
