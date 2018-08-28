package com.newframe.consumer;

import com.alibaba.fastjson.JSON;
import com.newframe.configuration.rabbitmq.QueueConstants;
import com.newframe.dto.user.User;
import com.newframe.entity.rabbitmq.MessageEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * 消息队列 - 消息消费者
 * @author wangdong

 */
@Component
public class MessageConsumer {
    /**
     * logger instance
     */
    static Logger logger = LoggerFactory.getLogger(MessageConsumer.class);

    /**
     * 这个目前因为队列名字和下面的一样
     * @param messageEntity
     */
    @RabbitListener(queues = QueueConstants.MESSAGE_QUEUE_NAME)
    @RabbitHandler
    public void handler(@Payload MessageEntity messageEntity) {
        logger.info("消费内容：{}", JSON.toJSONString(messageEntity));
    }

    /**
     * 所以二者只能存在一个
     * @param user
     */
    @RabbitListener(queues = QueueConstants.MESSAGE_QUEUE_NAME)
    @RabbitHandler
    public void handler(@Payload User user) {
        logger.info("消费内容：{}", JSON.toJSONString(user));
    }
}
