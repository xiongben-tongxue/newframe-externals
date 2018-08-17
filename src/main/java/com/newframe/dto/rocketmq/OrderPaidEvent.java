package com.newframe.dto.rocketmq;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * OrderPaidEvent Created by aqlu on 2017/11/16.
 */
@Data
@AllArgsConstructor
public class OrderPaidEvent implements Serializable {
    private String orderId;

    private BigDecimal paidMoney;
}
