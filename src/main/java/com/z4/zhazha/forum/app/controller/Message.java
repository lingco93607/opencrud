package com.z4.zhazha.forum.app.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.z4.zhazha.forum.dao.Dao;
import com.z4.zhazha.forum.pojo.AMessage;
import com.z4.zhazha.forum.pojo.Article;
import com.z4.zhazha.forum.pojo.Review;
import com.z4.zhazha.forum.pojo.ZMessage;
import com.z4.zhazha.forum.service.IService;
import com.z4.zhazha.forum.util.HttpUtil;

/**
 * 处理安卓端关于论坛消息的控制器类
 * @author xintt
 * @date 2017.11.06
 */
@Component
@Controller
@RequestMapping("/amessage")
public class Message {
	@Autowired
	@Qualifier("zmessageservice")
	private IService<ZMessage> zmsgService;
	
	@Autowired
	@Qualifier("zmessagejpa")
	private Dao<ZMessage> zmsgDao;
	
	@Autowired
	@Qualifier("articlejpa")
	private Dao<Article> articleDao;
	
	@Autowired
	@Qualifier("reviewjpa")
	private Dao<Review> reviewDao;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private static int MESSAGE_TYPE_MY = 0;
	private static int MESSAGE_TYPE_COMMENT = 1;
	private static int MESSAGE_TYPE_PRAISE = 2;
	
	private static int MESSAGE_TARGET_TYPE_ARTICLE = 0;
	private static int MESSAGE_TARGET_TYPE_REVIEW = 1;
	private static int MESSAGE_TARGET_TYPE_QUESTION = 2;
	
	/**
	 * 获取用户未读消息的条数
	 * @param map
	 * @return 三类未读消息的数目：@我的，评论，赞
	 */
	@RequestMapping(value="getNewMessages", produces="application/json;charset=UTF-8", method={RequestMethod.POST})
	@ResponseBody
	public Map<String, String> getNewMessages(@RequestBody Map<String, String> map) {
		int status = 0;
		String message = "获取新消息成功";
		Map<String, String> mapResult = new HashMap<String, String>(5);
		
		/*签名校验*/
		boolean appSignValidate = HttpUtil.appSignValidate(map);
		if (!appSignValidate) {
			status = 1;
			message = "签名失败！";
			mapResult.put("status", String.valueOf(status));
			mapResult.put("message", message);
			return mapResult;
		}
		/*签名end*/
		
		int userID = Integer.valueOf(map.get("userID"));
		String scope = "userid=" + userID + " and isread = false and type = ";
		String sqlEit = scope + MESSAGE_TYPE_MY;
		String sqlComment = scope + MESSAGE_TYPE_COMMENT;
		String sqlPraise = scope + MESSAGE_TYPE_PRAISE;
		Long eitMyNum = zmsgDao.getCount(ZMessage.class, sqlEit);
		Long commentNum =zmsgDao.getCount(ZMessage.class, sqlComment);
		Long praiseNum = zmsgDao.getCount(ZMessage.class, sqlPraise);
		
		mapResult.put("eitMyNum", String.valueOf(eitMyNum));
		mapResult.put("commentNum", String.valueOf(commentNum));		
		mapResult.put("praiseNum", String.valueOf(praiseNum));
		mapResult.put("status", String.valueOf(status));
		mapResult.put("message", message);		
		return mapResult;
	}
	
	/**
	 * 获取某一类型的未读消息
	 * @param map userID：用户ID；messageType：未读消息类型
	 * @return 某一类型的未读消息
	 */
/*	@RequestMapping(value="getTypeMessages", produces="application/json;charset=UTF-8", method={RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getTypeMessages(@RequestBody Map<String, String> map) {
		int status = 0;
		String message = "成功获取指定类型的消息";
		Map<String, Object> mapResult = new HashMap<String, Object>(3);
		
		签名校验
		boolean appSignValidate = HttpUtil.appSignValidate(map);
		if (!appSignValidate) {
			status = 1;
			message = "签名失败！";
			mapResult.put("status", String.valueOf(status));
			mapResult.put("message", message);
			return mapResult;
		}
		签名end
		
		int userID = Integer.valueOf(map.get("userID"));
		int messageType = Integer.valueOf(map.get("messageType"));
		String scope = "userid=" + userID + " and isread = false and type = " + messageType;
		List<ZMessage> msgs = zmsgDao.query(ZMessage.class, scope);
		if (msgs.isEmpty() || msgs.size() == 0) {
			message = "暂无此类新消息";
			mapResult.put("status", String.valueOf(status));
			mapResult.put("message", message);		
			return mapResult;
		}
		
		List<AMessage> aMsgs = new ArrayList<AMessage>();
		for (ZMessage msg : msgs) {
			AMessage amsg = new AMessage();
			amsg.setId(msg.getId());
			amsg.setContent(msg.getContent());
			amsg.setMsgType(msg.getType());
			amsg.setTime(sdf.format(msg.getPostTime()));
			
			JsonObject user = HttpUtil.sendRequestMainGetUser(userID);
			if (null != user) {
				amsg.setWriterPortrait(user.get("userPortrain").getAsString());
				amsg.setWriterName(user.get("userName").getAsString());			
			}
			
			if (msg.getTargetType() == MESSAGE_TARGET_TYPE_ARTICLE) {
				Article article = articleDao.get(Article.class, msg.getTargetID());
				if (null != article) {
					amsg.setMsgImage(article.getFirstImgUrl());
					amsg.setMsgText(article.getTitle());
				}
			}else if (msg.getTargetType() == MESSAGE_TARGET_TYPE_REVIEW) {
				Review review = reviewDao.get(Review.class, msg.getTargetID());
				if (null != review) {
					amsg.setMsgText(review.getText());
				}
			}else if (msg.getTargetType() == MESSAGE_TARGET_TYPE_QUESTION) {
				
			}
			aMsgs.add(amsg);
		}
		mapResult.put("status", String.valueOf(status));
		mapResult.put("message", message);	
		mapResult.put("aMsgs", aMsgs);	
		return mapResult;
	}*/
	
	/**
	 * 获取某一类型的未读消息
	 * @param map userID：用户ID；messageType：未读消息类型
	 * @return 某一类型的未读消息
	 */
	@RequestMapping(value="getTypeMessages", produces="application/json;charset=UTF-8", method={RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getTypeMessages(@RequestBody Map<String, String> map) {
		int status = 0;
		String message = "成功获取指定类型的消息";
		Map<String, Object> mapResult = new HashMap<String, Object>(3);
		
		/*签名校验*/
		boolean appSignValidate = HttpUtil.appSignValidate(map);
		if (!appSignValidate) {
			status = 1;
			message = "签名失败！";
			mapResult.put("status", String.valueOf(status));
			mapResult.put("message", message);
			return mapResult;
		}
		/*签名end*/
		
		int userID = Integer.valueOf(map.get("userID"));
		int messageType = Integer.valueOf(map.get("messageType"));
		String scope = "userid=" + userID + " and isread = false and type = " + messageType;
		List<ZMessage> msgs = zmsgDao.query(ZMessage.class, scope);
		if (msgs.isEmpty() || msgs.size() == 0) {
			message = "暂无此类新消息";
			mapResult.put("status", String.valueOf(status));
			mapResult.put("message", message);		
			return mapResult;
		}
		
		List<AMessage> aMsgs = new ArrayList<AMessage>();
		for (ZMessage msg : msgs) {
			AMessage amsg = new AMessage();
			amsg.setId(msg.getId());
			amsg.setContent(msg.getContent());
			amsg.setMsgType(msg.getType());
			amsg.setTime(sdf.format(msg.getPostTime()));
			
			/*JsonObject user = HttpUtil.sendRequestMainGetUser(userID);
			if (null != user) {
				amsg.setWriterPortrait(user.get("userPortrain").getAsString());
				amsg.setWriterName(user.get("userName").getAsString());			
			}*/
			
			if (msg.getTargetType() == MESSAGE_TARGET_TYPE_ARTICLE) {
				Article article = articleDao.get(Article.class, msg.getTargetID());
				if (null != article) {
					amsg.setMsgImage(article.getFirstImgUrl());
					amsg.setMsgText(article.getTitle());
				}
			}else if (msg.getTargetType() == MESSAGE_TARGET_TYPE_REVIEW) {
				Review review = reviewDao.get(Review.class, msg.getTargetID());
				if (null != review) {
					amsg.setMsgText(review.getText());
				}
			}else if (msg.getTargetType() == MESSAGE_TARGET_TYPE_QUESTION) {
				
			}
			aMsgs.add(amsg);
		}
		mapResult.put("status", String.valueOf(status));
		mapResult.put("message", message);	
		mapResult.put("aMsgs", aMsgs);	
		return mapResult;
	}
	
	/**
	 * 点开某一条未读消息
	 * @param map
	 * @return
	 */
	@RequestMapping(value="readMessage", produces="application/json;charset=UTF-8", method={RequestMethod.POST})
	@ResponseBody
	public Map<String, String> readMessage(@RequestBody Map<String, String> map) {
		int status = 0;
		String message = "消息已读";
		Map<String, String> mapResult = new HashMap<String, String>(2);
		
		/*签名校验*/
		boolean appSignValidate = HttpUtil.appSignValidate(map);
		if (!appSignValidate) {
			status = 1;
			message = "签名失败！";
			mapResult.put("status", String.valueOf(status));
			mapResult.put("message", message);
			return mapResult;
		}
		/*签名end*/
		
		int zmsgID;
		try {
			zmsgID = Integer.valueOf(map.get("msgID"));
		}catch (Exception e) {
			status = 2;
			message = "传入的消息ID类型有误，请传入整型字符串！";
			mapResult.put("status", String.valueOf(status));
			mapResult.put("message", message);	
			return mapResult;
		}
		
		ZMessage zmsg = zmsgDao.get(ZMessage.class, zmsgID);	
		zmsg.setRead(true);
		boolean updateZmsg = zmsgDao.update(zmsg);
		if (updateZmsg) {
			mapResult.put("status", String.valueOf(status));
			mapResult.put("message", message);	
			return mapResult;
		} else {
			status = 3;
			message = "消息阅读状态更新失败！";
			mapResult.put("status", String.valueOf(status));
			mapResult.put("message", message);	
			return mapResult;
		}		
	}
	
	/**
	 * 点开“点赞”新消息，即认为已读
	 * @param map
	 * @return
	 */
	@RequestMapping(value="readPraiseMessages", produces="application/json;charset=UTF-8", method={RequestMethod.POST})
	@ResponseBody
	public Map<String, String> readPraiseMessages(@RequestBody Map<String, String> map) {
		int status = 0;
		String message = "消息已读";
		Map<String, String> mapResult = new HashMap<String, String>(2);
		
		/*签名校验*/
		boolean appSignValidate = HttpUtil.appSignValidate(map);
		if (!appSignValidate) {
			status = 1;
			message = "签名失败！";
			mapResult.put("status", String.valueOf(status));
			mapResult.put("message", message);
			return mapResult;
		}
		/*签名end*/
		
		int userID;
		try {
			userID = Integer.valueOf(map.get("userID"));
		}catch (Exception e) {
			status = 2;
			message = "传入的用户ID类型有误，请传入整型字符串！";
			mapResult.put("status", String.valueOf(status));
			mapResult.put("message", message);	
			return mapResult;
		}
		
		String scope = "userID=" + userID + " and isread = false and type = " + MESSAGE_TYPE_PRAISE;
		List<ZMessage> zmsgs = (List<ZMessage>) zmsgDao.query(ZMessage.class, scope);	
		if (null != zmsgs && zmsgs.size() != 0) {
			for (ZMessage zmsg: zmsgs) {
				zmsg.setRead(true);
				boolean updateZmsg = zmsgDao.update(zmsg);
				if (!updateZmsg) {
					status = 3;
					message = "消息阅读状态更新失败！";
					mapResult.put("status", String.valueOf(status));
					mapResult.put("message", message);	
					return mapResult;
				} 
			}
		}
		mapResult.put("status", String.valueOf(status));
		mapResult.put("message", message);	
		return mapResult;
		
	}
	
	/**
	 * 测试查询表条数的功能
	 * @param map
	 * @return
	 */
	@RequestMapping(value="testgetNewMessages", produces="application/json;charset=UTF-8", method={RequestMethod.POST})
	@ResponseBody
	public Map<String, Long> testgetNewMessages(@RequestBody Map<String, Integer> map) {
		int userID = map.get("userID");
		Map<String, Long> mapResult = new HashMap<String, Long>(3);
		//String scope = "select count(*) from zmessage where userid=" + userID + " and isread = false and type = ";
		String scope = "userid=" + userID + " and isread = false and type = ";
		String sqlEit = scope + MESSAGE_TYPE_MY;
		String sqlComment = scope + MESSAGE_TYPE_COMMENT;
		String sqlPraise = scope + MESSAGE_TYPE_PRAISE;
		
		long count = zmsgDao.getCount(ZMessage.class);
		System.out.println("zmessage count:" + count);
		Long eitMyNum = zmsgDao.getCount(ZMessage.class, sqlEit);
		Long commentNum =zmsgDao.getCount(ZMessage.class, sqlComment);
		Long praiseNum = zmsgDao.getCount(ZMessage.class, sqlPraise);
		
		mapResult.put("eitMyNum", eitMyNum);
		mapResult.put("commentNum", commentNum);		
		mapResult.put("praiseNum", praiseNum);
		return mapResult;
	}
	
	@RequestMapping(value="testgetTypeMessages", produces="application/json;charset=UTF-8", method={RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> testgetTypeMessages(@RequestBody Map<String, String> map) {
		int status = 0;
		String message = "消息已读";
		int userID = Integer.valueOf(map.get("userID"));
		int messageType = Integer.valueOf(map.get("messageType"));
		String scope = "userid=" + userID + " and isread = false and type = " + messageType;
		Map<String, Object> mapResult = new HashMap<String, Object>(3);
		//String scope = "select count(*) from zmessage where userid=" + userID + " and isread = false and type = ";
		
		
		List<ZMessage> msgs = zmsgDao.query(ZMessage.class, scope);
		if (msgs.isEmpty() || msgs.size() == 0) {
			message = "暂无此类新消息";
			mapResult.put("status", String.valueOf(status));
			mapResult.put("message", message);		
			return mapResult;
		}
		
		List<AMessage> aMsgs = new ArrayList<AMessage>();
		for (ZMessage msg : msgs) {
			AMessage amsg = new AMessage();
			amsg.setContent(msg.getContent());
			amsg.setMsgType(msg.getType());
			amsg.setTime(sdf.format(msg.getPostTime()));
			
			JsonObject user = HttpUtil.sendRequestMainGetUser(userID);
			if (null != user) {
				amsg.setWriterPortrait(user.get("userPortrain").getAsString());
				amsg.setWriterName(user.get("userName").getAsString());			
			}
			
			if (msg.getTargetType() == MESSAGE_TARGET_TYPE_ARTICLE) {
				Article article = articleDao.get(Article.class, msg.getTargetID());
				if (null != article) {
					amsg.setMsgImage(article.getFirstImgUrl());
					amsg.setMsgText(article.getTitle());
				}
			}else if (msg.getTargetType() == MESSAGE_TARGET_TYPE_REVIEW) {
				Review review = reviewDao.get(Review.class, msg.getTargetID());
				if (null != review) {
					amsg.setMsgText(review.getText());
				}
			}else if (msg.getTargetType() == MESSAGE_TARGET_TYPE_QUESTION) {
				
			}
			aMsgs.add(amsg);
		}
		mapResult.put("status", String.valueOf(status));
		mapResult.put("message", message);	
		mapResult.put("aMsgs", aMsgs);	
		return mapResult;
	}

}
