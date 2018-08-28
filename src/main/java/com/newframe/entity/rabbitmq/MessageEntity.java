package com.newframe.entity.rabbitmq;

import lombok.Data;

import java.io.Serializable;

/**
 * 消息实体
 * @author wangdong
 */
@Data
public class MessageEntity implements Serializable {
    /**
     * 消息内容
     */
    private String content;
}
