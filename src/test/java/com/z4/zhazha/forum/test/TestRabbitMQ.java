package com.z4.zhazha.forum.test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeoutException;

import org.junit.Test;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;

public class TestRabbitMQ {

	 private final static String QUEUE_NAME0 = "hello";
	
	 @Test
	 public void test() throws IOException,TimeoutException, ShutdownSignalException, ConsumerCancelledException, InterruptedException {
	 // AMQP的连接其实是对Socket做的封装, 注意以下AMQP协议的版本号，不同版本的协议用法可能不同。
	 ConnectionFactory factory = new ConnectionFactory();
	 factory.setUsername("lingco");
		factory.setPassword("aaaaaa");
		factory.setHost("106.38.121.126");
		factory.setVirtualHost("/");
		factory.setPort(5672);
	 Connection connection = factory.newConnection();
	 // 下一步我们创建一个channel, 通过这个channel就可以完成API中的大部分工作了。
	 Channel channel = connection.createChannel();
	
	 // 为了发送消息, 我们必须声明一个队列，来表示我们的消息最终要发往的目的地。
	 channel.queueDeclare(QUEUE_NAME0, false, false, false, null);
	 String message = "this is a test message!";
	 // 然后我们将一个消息发往这个队列。
	 channel.basicPublish("", QUEUE_NAME0, null, message.getBytes());
	 System.out.println("[" + message + "]");
	 
	 QueueingConsumer consumer = new QueueingConsumer(channel);
	 channel.basicConsume(QUEUE_NAME0, true, consumer);
	 QueueingConsumer.Delivery delivery = consumer.nextDelivery();
     String messageRecv = new String(delivery.getBody());
     System.out.println(" [x] Received '" + messageRecv + "'");
	
	 // 最后，我们关闭channel和连接，释放资源。
	 channel.close();
	 connection.close();
	 }

	private final static String QUEUE_NAME = "forum";
	static boolean isBreak = false;

	public static void main(String[] args) {

		ConnectionFactory factory = new ConnectionFactory();
		factory.setUsername("lingco");
		factory.setPassword("aaaaaa");
		factory.setHost("106.38.121.126");
		factory.setVirtualHost("/");
		factory.setPort(5672);

		try {
			Connection connection = factory.newConnection();
			Channel channel = connection.createChannel();

			Consumer consumerUpdate = new DefaultConsumer(channel) {
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
						byte[] body) throws IOException {
					String message = new String(body, "UTF-8");
					System.out.println(" update '" + message + "'");
					isBreak = true;
				}
			};
			
			Consumer consumerInsert = new DefaultConsumer(channel) {
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
						byte[] body) throws IOException {
					String message = new String(body, "UTF-8");
					System.out.println(" insert '" + message + "'");
					isBreak = true;
				}
			};
			
			
			
			channel.basicConsume("game_updateuser", true, consumerUpdate);
			channel.basicConsume("game_insertuser", true, consumerInsert);

			//I have manually created the queue on RabbitMQ, so we don't need to declare it here
			//channel.queueDeclare(QUEUE_NAME, true, false, false, null);
 

			for (int i = 1; i < 10; i++) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				java.util.Date date = new java.util.Date();
				String message = sdf.format(date);

				if ((i % 2) == 0)
					channel.basicPublish("game_backend", "insert.test", null, ("even " + message).getBytes());
				else
					channel.basicPublish("game_backend", "update.Test", null, ("odd " + message).getBytes());

				System.out.println(" [x] Sent '" + message + "'");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			while (!isBreak) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			channel.close();
			connection.close();
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
