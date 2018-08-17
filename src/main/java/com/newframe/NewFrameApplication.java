package com.newframe;

import com.newframe.dto.rocketmq.OrderPaidEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.starter.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.starter.core.RocketMQListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

@SpringBootApplication
public class NewFrameApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewFrameApplication.class, args);
	}

	@Slf4j
	@Service
	@RocketMQMessageListener(topic = "test-topic-1", consumerGroup = "my-consumer_test-topic-1")
	public static class MyConsumer1 implements RocketMQListener<String> {
		@Override
		public void onMessage(String message) {
			System.out.println(message);
		}
	}

	@Slf4j
	@Service
	@RocketMQMessageListener(topic = "test-topic-2", consumerGroup = "my-consumer_test-topic-2")
	public static class MyConsumer2 implements RocketMQListener<OrderPaidEvent>{
		@Override
		public void onMessage(OrderPaidEvent orderPaidEvent) {

			System.out.println(orderPaidEvent.toString());
		}
	}
}
