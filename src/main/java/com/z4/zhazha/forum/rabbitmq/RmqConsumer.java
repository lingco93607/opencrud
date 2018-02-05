package com.z4.zhazha.forum.rabbitmq;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.z4.zhazha.forum.pojo.ZUser;
import com.z4.zhazha.forum.service.IService;

@Service
public class RmqConsumer implements MessageListener{
	@Autowired
	 @Qualifier("zuserservice")
	 private IService<ZUser> zuserService;
	
	public void rmqConsumeMessage(Object obj) {
		RabbitMessage rabbitMessage = (RabbitMessage) obj;
		System.out.println("received msg: \n");
		System.out.println(rabbitMessage.getExchange());
		System.out.println(rabbitMessage.getRouteKey());
		System.out.println(rabbitMessage.getParams().toString());
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onMessage(Message message) {
		System.out.println("rmq接收到的消息为：" + message.toString());
		try {
			ByteArrayInputStream byteInt = new ByteArrayInputStream(message.getBody());
			ObjectInputStream objInt = new ObjectInputStream(byteInt);
			HashMap<String, String> map = (HashMap<String, String>) objInt.readObject();
			System.out.println("rmq接收到的消息为：" + map.get("data"));
			if ("propertyChange".equals(map.get("method"))) {
				System.out.println("rmq接收到的消息为：method:" + map.get("method"));
				System.out.println("rmq接收到的消息为：property:" + map.get("property"));
				System.out.println("rmq接收到的消息为：heroID:" + map.get("heroID"));
				System.out.println("rmq接收到的消息为：value:" + map.get("value"));
				int id = Integer.valueOf(map.get("heroID"));
				ZUser zuser = zuserService.get(ZUser.class, id);
				if (null != zuser) {
					System.out.println(zuser.getzUserName());
				}
				
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	
	}
}
