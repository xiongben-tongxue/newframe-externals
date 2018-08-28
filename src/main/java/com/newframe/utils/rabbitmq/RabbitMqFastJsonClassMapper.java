package com.newframe.utils.rabbitmq;

import org.springframework.amqp.support.converter.DefaultClassMapper;

/**
 * fastjson 转换映射
 * @author wangdong

 */
public class RabbitMqFastJsonClassMapper extends DefaultClassMapper {
    /**
     * 构造函数初始化信任所有pakcage
     */
    public RabbitMqFastJsonClassMapper() {
        super();
        setTrustedPackages("*");
    }
}
