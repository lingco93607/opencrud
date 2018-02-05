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
import com.z4.zhazha.forum.service.IService;

/**
 * 
 * @author xintt
 * @date 2017.12.21
 * rabbitMQ消费者：处理业务逻辑后台创建用户的请求消息
 *
 */
public class TokenCreateConsumer implements MessageListener {
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
			
			String scope = "zusername='" + userName + "'";
			ZUser zuser = zuserDao.get(ZUser.class, scope);
			if (null == zuser) {
				zuser = new ZUser();
				zuser.setToken(tokenValue);
				zuser.setzUserName(userName);
				boolean save = zuserDao.save(zuser);
				if (save) {
					System.out.println("TokenCreateConsumer：论坛用户" + userName + "创建成功！");
				}else {
					System.out.println("TokenCreateConsumer：论坛用户" + userName + "创建失败！");
				}
			}else {
				System.out.println("TokenCreateConsumer：论坛用户" + userName + "已存在！");				
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
