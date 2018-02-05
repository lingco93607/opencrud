package com.z4.zhazha.forum.rabbitmq;

import java.util.Map;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
public class RmqProducer {
	private AmqpTemplate amqpTemplate;
	
	public void sendMessage(RabbitMessage message) {
		try{
			System.out.println("send message template" + amqpTemplate.toString());
			amqpTemplate.convertAndSend(message.getExchange(), message.getRouteKey(), message.getParams());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public AmqpTemplate getAmqpTemplate() {
		return amqpTemplate;
	}

	public void setAmqpTemplate(AmqpTemplate amqpTemplate) {
		this.amqpTemplate = amqpTemplate;
	}

}
