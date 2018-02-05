package com.z4.zhazha.forum.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.z4.zhazha.forum.rabbitmq.CommenMessage;
import com.z4.zhazha.forum.rabbitmq.MessageSender;
import com.z4.zhazha.forum.rabbitmq.RabbitMessage;
import com.z4.zhazha.forum.rabbitmq.RmqProducer;

import net.sf.json.JSONObject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class RabbitMQTest {
	@Resource
	private MessageSender messageSender;

	//@Test
	public void testSendMessage() {
		CommenMessage message = new CommenMessage();
		message.setSource("xintt");
		JSONObject obj = new JSONObject();
		obj.put("test", "test xintt message");
		message.setMessage(obj);
		messageSender.setRoutingKey("message.xintt");
		messageSender.sendDataToQueue(message);
	}
	
	@Resource
	private RmqProducer rmqProducer2;  
	
	@Test
	public void test() throws IOException {

		String exchange = "testExchange";//// 交换器
		String routeKey = "testQueue";// 队列
		String methodName = "test";// 调用的方法
		// 参数
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("data", "hello");

		RabbitMessage msg = new RabbitMessage(exchange, routeKey, methodName, param);
		// 发送消息
		rmqProducer2.sendMessage(msg);

	}
}
