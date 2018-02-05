package com.z4.zhazha.forum.rabbitmq;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.z4.zhazha.forum.dao.Dao;
import com.z4.zhazha.forum.pojo.ZUser;

/**
 * 
 * @author xintt
 * @date 2017.12.21
 * rabbitMQ消费者：处理业务逻辑后台修改用户token的请求消息
 *
 */
public class TokenChangeConsumer implements MessageListener {
	@Autowired
	@Qualifier("zuserjpa")
	Dao<ZUser> zuserDao;

	@SuppressWarnings("unchecked")
	@Override
	public void onMessage(Message message) {
		try {
			ByteArrayInputStream byteInt = new ByteArrayInputStream(message.getBody());
			ObjectInputStream objInt = new ObjectInputStream(byteInt);
			HashMap<String, String> map = (HashMap<String, String>) objInt.readObject();			
			String userName = map.get("userName");
			String tokenValue = map.get("tokenValue");
			
			ZUser zuser = zuserDao.get(ZUser.class, "zusername='" + userName + "'");
			if (null != zuser) {
				zuser.setToken(tokenValue);
				if (zuserDao.update(zuser)) {
					System.out.println("TokenChangeConsumer：论坛用户" + userName + "的token修改成功！");
				}else {
					System.out.println("TokenChangeConsumer：论坛用户" + userName + "的token修改失败！");
				}
			}else {
				System.out.println("TokenChangeConsumer：论坛用户" + userName + "不存在！");				
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
