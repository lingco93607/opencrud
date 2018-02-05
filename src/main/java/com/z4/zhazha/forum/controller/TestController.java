package com.z4.zhazha.forum.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.rabbitmq.client.Channel;
import com.z4.zhazha.forum.dao.Dao;
import com.z4.zhazha.forum.pojo.AMessage;
import com.z4.zhazha.forum.pojo.Article;
import com.z4.zhazha.forum.pojo.Review;
import com.z4.zhazha.forum.pojo.ZMessage;
import com.z4.zhazha.forum.pojo.ZUser;
import com.z4.zhazha.forum.rabbitmq.CommenMessage;
import com.z4.zhazha.forum.rabbitmq.MessageSender;
import com.z4.zhazha.forum.rabbitmq.RabbitMessage;
import com.z4.zhazha.forum.rabbitmq.RmqConsumer;
import com.z4.zhazha.forum.rabbitmq.RmqProducer;
import com.z4.zhazha.forum.service.IService;
import com.z4.zhazha.forum.service.JedisClient;
import com.z4.zhazha.forum.util.HttpUtil;
import com.z4.zhazha.forum.util.JsonUtils;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/test")
public class TestController {
	 @Autowired
	 private JedisClient jedisClient;
	 
	 @Value("${MESSAGE_TYPE_COMMENT}")
	 int MESSAGE_TYPE_COMMENT;
	 
	 @Autowired
	 @Qualifier("zuserservice")
	 private IService<ZUser> zuserService;
	 
	 @Autowired
		@Qualifier("articlejpa")
		Dao<Article> articleDao;
	 
	 @Autowired
		@Qualifier("zmessagejpa")
		private Dao<ZMessage> zmsgDao;
	 
	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	 /**
	  * 测试springmvc+redis整合是否成功，从redis读取/写入数据
	  * @author xintt
	  * @param id
	  * @return
	  */
	@RequestMapping(value="redis", method={RequestMethod.GET})
	@ResponseBody
	public String redis(@RequestParam int id) {	
		String resulthget = jedisClient.hget("用户表", id + "");
		if (resulthget != null) {
			System.out.println("有缓存啦啦啦！！！");
			ZUser zuser = JsonUtils.jsonObjectToUser(resulthget);
			System.out.println(zuser.toString());
			return resulthget;
		} else {
			System.out.println("用户表没查过");
			ZUser zuser = zuserService.get(ZUser.class, id);
			String cacheString = JsonUtils.objectToJson(zuser);
			jedisClient.hset("用户表", id + "", cacheString);
			return cacheString;
		}
	}
	
	/**
	 * 将阅读数前top的帖子写入redis
	 */
	@RequestMapping(value="topArticles", method={RequestMethod.GET})
	@ResponseBody
	public List<Article> top10articles2redis(@RequestParam int start,@RequestParam int end ) {	
		Set<String> articleStrs = jedisClient.getTopLast("toparticles", start, end);
		if (null != articleStrs && (articleStrs.size() != 0)) {
			System.out.println("有缓存啦啦啦！！！");
			System.out.println(articleStrs);
			List<Article> articles = JsonUtils.arrayToListObj(JsonUtils.setStringToJArray(articleStrs), Article.class);
			return articles;
		}else {
			String scope = "1=1 order by readnum limit " + end;
			List<Article> articles= (List<Article> ) articleDao.query(Article.class, scope);
			Object[] objs = new Object[articles.size()];
			int i = 0;
			for (Article article: articles) {
				objs[i] = article;
				i++;
			}
			JSONArray cacheString = JsonUtils.objectsToJson(objs);
			jedisClient.zaddList("toparticles", articles);
			return articles;
		}
	}
	
	/**
	 * 从redis读取阅读数前topNum的帖子
	 * 若无缓存，则从数据库查询，并将查询结果写入redis
	 * @return List<Article> 阅读数前topNum的帖子集合
	 */
	@RequestMapping(value="topNumArticles", method={RequestMethod.GET})
	@ResponseBody
	public List<Article> topArticles(@RequestParam int topNum ) {	
		List<String> articleStrs = jedisClient.getTop("toparticles", topNum);
		if (null != articleStrs && (articleStrs.size() != 0)) {
			System.out.println("有缓存啦啦啦！！！");
			System.out.println(articleStrs);
			List<Article> articles = JsonUtils.arrayToListObj(JsonUtils.listStringToJArray(articleStrs), Article.class);
			return articles;
		}else {
			String scope = "1=1 order by readnum limit " + topNum;
			List<Article> articles= (List<Article> ) articleDao.query(Article.class, scope);
			Object[] objs = new Object[articles.size()];
			int i = 0;
			for (Article article: articles) {
				objs[i] = article;
				i++;
			}
			JSONArray cacheString = JsonUtils.objectsToJson(objs);
			jedisClient.zaddList("toparticles", articles);
			return articles;
		}
	}
	
	@RequestMapping(value="userInfo", method={RequestMethod.GET})
	@ResponseBody
	public ZUser getUserInfo(@RequestParam int userID) {
		String resulthget = jedisClient.hget("userInfo", userID + "");
		if (null != resulthget && !"null".equals(resulthget)) {
			System.out.println("用户"+userID+ "有缓存：" + resulthget);
			ZUser zuser = new ZUser();
			zuser = (ZUser) JsonUtils.jsonToObject(resulthget,zuser);
			return zuser;
		}else {
			System.out.println("用户"+userID+ "没有缓存！");
			ZUser zuser = zuserService.get(ZUser.class, userID);
			if (null != zuser) {
				String cacheString = JsonUtils.objectToJson(zuser);
				jedisClient.hset("userInfo", userID + "", cacheString);
				System.out.println("已为用户"+userID+ "写入缓存：" + cacheString);
			}else {
				System.out.println("没有用户" + userID + "的信息");
			}
			return zuser;
		}
	}
	
	@RequestMapping(value="getTypeMessages", produces="application/json;charset=UTF-8", method={RequestMethod.GET})
	@ResponseBody
	public Map<String, Object> getTypeMessages() {	
		Map<String, Object> mapResult = new HashMap<String, Object>(3);
		
		int userID = 1;
		int messageType = 0;
		String scope = "userid=" + userID + " and isread = false and type = " + messageType;
		List<ZMessage> msgs = zmsgDao.query(ZMessage.class, scope);
		if (msgs.isEmpty() || msgs.size() == 0) {				
			return mapResult;
		}
		
		List<AMessage> aMsgs = new ArrayList<AMessage>();
		for (ZMessage msg : msgs) {
			AMessage amsg = new AMessage();
			amsg.setContent(msg.getContent());
			amsg.setMsgType(msg.getType());
			amsg.setTime(sdf.format(msg.getPostTime()));
			
			/*JsonObject user = HttpUtil.sendRequestMainGetUser(userID);
			if (null != user) {
				amsg.setWriterPortrait(user.get("userPortrain").getAsString());
				amsg.setWriterName(user.get("userName").getAsString());			
			}*/
			
			if (msg.getTargetType() == 0) {
				Article article = articleDao.get(Article.class, msg.getTargetID());
				if (null != article) {
					amsg.setMsgImage(article.getFirstImgUrl());
					amsg.setMsgText(article.getTitle());
				}
			}
			aMsgs.add(amsg);
		}
		mapResult.put("status", "1");
		mapResult.put("message", "success");	
		mapResult.put("aMsgs", aMsgs);	
		return mapResult;
	}
	
	@Resource
	private MessageSender messageSender;
	
	@Resource
	private RmqProducer rmqProducer;
	
	@Resource 
	private RmqConsumer rmqConsumer;
	
	/*@Resource
	private ConnectionFactory connectionFactory;*/

	@RequestMapping(value="testSendMessage", produces="application/json;charset=UTF-8", method={RequestMethod.GET})
	@ResponseBody
	public void testSendMessage() {
		
		String exchange = "testExchange";//// 交换器
		String routeKey = "forumQueue";// 队列
		
		for (int i=0; i<6; i++) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("data", "hello"+i);
			param.put("method", "propertyChange");
			param.put("property", "gold");
			param.put("heroID", "1");
			param.put("value", "10");

			RabbitMessage msg=new RabbitMessage(exchange,routeKey,param);
			// 发送消息
			rmqProducer.sendMessage(msg);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		// 接收消息
		// rmqConsumer.rmqConsumeMessage(msg);

		/*
		 * CommenMessage message = new CommenMessage();
		 * message.setSource("xintt"); JSONObject obj = new JSONObject();
		 * obj.put("test", "test xintt message"); message.setMessage(obj);
		 * messageSender.setRoutingKey("message.xintt");
		 * messageSender.sendDataToQueue(message);
		 */
	}
	
	@RequestMapping(value="testTokenConsumer", produces="application/json;charset=UTF-8", method={RequestMethod.GET})
	@ResponseBody
	public void testTokenConsumer() {
		
		String exchange = "tokenExchange";//// 交换器
		String routeKey1 = "tokenCreateQueue";// 队列
		String routeKey2 = "tokenChangeQueue";// 队列
			
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userName", "xintiantian");
		param.put("tokenValue", "xintiantian");

		RabbitMessage msg1 = new RabbitMessage(exchange, routeKey1, param);		
		RabbitMessage msg2 = new RabbitMessage(exchange, routeKey2, param);		
		rmqProducer.sendMessage(msg1);
		try {
			Thread.sleep(3000);
			rmqProducer.sendMessage(msg2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="testNewZMessageConsumer", produces="application/json;charset=UTF-8", method={RequestMethod.GET})
	@ResponseBody
	public void testNewZMessageConsumer(@RequestParam int articleID, @RequestParam int userUid, @RequestParam String commonText) {
		Review review = new Review();
		Article article = articleDao.get(Article.class, articleID);
		review.setArticle(article);
		review.setAuthorID(userUid);
		review.setReviewDate(new Date());
		review.setText(commonText);
		article.addReview(review);
		
		String exchange = "zMessageExchange";//// 交换器
		String routeKey = "zMessageQueue";// 队列
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("obj", article.getArticleID());
		param.put("objType", "article");
		param.put("writer", userUid);
		param.put("msgType", MESSAGE_TYPE_COMMENT);

		RabbitMessage msg = new RabbitMessage(exchange, routeKey, param);
		// 发送消息
		rmqProducer.sendMessage(msg);
	}
	
}
