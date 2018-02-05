package com.z4.zhazha.forum.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageSender {
	private AmqpTemplate amqpTemplate;
	private String routingKey;
	
	public AmqpTemplate getAmqpTemplate() {
		return amqpTemplate;
	}
	public void setAmqpTemplate(AmqpTemplate amqpTemplate) {
		this.amqpTemplate = amqpTemplate;
	}
	public String getRoutingKey() {
		return routingKey;
	}
	public void setRoutingKey(String routingKey) {
		this.routingKey = routingKey;
	}
	
	public void sendDataToQueue(Object obj) {
		amqpTemplate.convertAndSend(this.routingKey, obj);
	}

}
