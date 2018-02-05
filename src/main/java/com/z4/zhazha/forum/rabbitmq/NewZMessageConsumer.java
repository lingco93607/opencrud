package com.z4.zhazha.forum.rabbitmq;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Date;
import java.util.HashMap;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import com.z4.zhazha.forum.dao.Dao;
import com.z4.zhazha.forum.pojo.Article;
import com.z4.zhazha.forum.pojo.Review;
import com.z4.zhazha.forum.pojo.ZMessage;
import com.z4.zhazha.forum.service.IService;

/**
 * 
 * @author xintt
 * @date 2017.12.22
 * rabbitMQ消费者：论坛产生消息后，进行持久化处理
 *
 */
public class NewZMessageConsumer implements MessageListener {
	
	@Value("${MESSAGE_TARGET_TYPE_ARTICLE}")
	int MESSAGE_TARGET_TYPE_ARTICLE;
	
	@Value("${MESSAGE_TARGET_TYPE_REVIEW}")
	int MESSAGE_TARGET_TYPE_REVIEW;
	
	@Value("${MESSAGE_TARGET_TYPE_QUESTION}")
	int MESSAGE_TARGET_TYPE_QUESTION;
	
	@Autowired
	@Qualifier("zmessagejpa")
	Dao<ZMessage> zmsgDao;
	
	@Autowired
	@Qualifier("articlejpa")
	Dao<Article> articleDao;
	
	@Autowired
	@Qualifier("reviewservice")
	private IService<Review> reviewService;

	@SuppressWarnings("unchecked")
	@Override
	public void onMessage(Message message) {
		try {
			ByteArrayInputStream byteInt = new ByteArrayInputStream(message.getBody());
			ObjectInputStream objInt = new ObjectInputStream(byteInt);
			HashMap<String, Object> map = (HashMap<String, Object>) objInt.readObject();			
			int obj = (int) map.get("obj");
			String objType = (String) map.get("objType");
			int writer = (int) map.get("writer");
			int msgType = (int) map.get("msgType");
	
			ZMessage zmsg = new ZMessage();
			if ("article".equalsIgnoreCase(objType)) {
				Article article = articleDao.get(Article.class, obj);
				zmsg.setTargetID(article.getArticleID());
				zmsg.setUserID(article.getAuthorID());
				zmsg.setTargetType(MESSAGE_TARGET_TYPE_ARTICLE);
			} else if ("review".equalsIgnoreCase(objType)) {
				Review review = (Review) reviewService.get(Review.class, obj);;
				zmsg.setTargetID(review.getReviewID());
				zmsg.setUserID(review.getAuthorID());
				zmsg.setTargetType(MESSAGE_TARGET_TYPE_REVIEW);
			} /*
				 * else if (obj instanceof Question) {
				 * zmsg.setTargetType(MESSAGE_TARGET_TYPE_QUESTION); }
				 */else {
				System.out.println("此新消息不是帖子、评论或问题相关的消息，类型不明！");
			}
			zmsg.setType(msgType);
			zmsg.setPostTime(new Date());
			zmsg.setRead(false);
			zmsg.setWriterID(writer);

			boolean create = zmsgDao.save(zmsg);
			if (create) {
				System.out.println("NewZMessageConsumer：成功创建新消息！1, obj:" + obj + " 2,writer:" + writer + " 3, msgType:" + msgType);
			}else {
				System.out.println("NewZMessageConsumer：创建新消息失败！1, obj:" + obj + " 2,writer:" + writer + " 3, msgType:" + msgType);
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
