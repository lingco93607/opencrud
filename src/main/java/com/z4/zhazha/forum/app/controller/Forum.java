package com.z4.zhazha.forum.app.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.z4.zhazha.forum.dao.Dao;
import com.z4.zhazha.forum.pojo.Article;
import com.z4.zhazha.forum.pojo.RelationUserArticle;
import com.z4.zhazha.forum.pojo.Review;
import com.z4.zhazha.forum.pojo.Token;
import com.z4.zhazha.forum.pojo.UserArticleUnionID;
import com.z4.zhazha.forum.pojo.ZUser;
import com.z4.zhazha.forum.service.IService;
import com.z4.zhazha.forum.util.HttpUtil;
import com.z4.zhazha.forum.util.ResultInfo;
import com.z4.zhazha.forum.util.StringUtil;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 
 * @author xintt
 * @date 2017.10.12
 * 
 *用于处理app端发起的和论坛，帖子有关的请求
 */
@Component
@Controller
@RequestMapping("/forum")
public class Forum {
	
	@Autowired
	@Qualifier("articleservice")
	IService<Article> articleService;
	
	@Autowired
	@Qualifier("reviewservice")
	IService<Review> reviewService;
	
	@Autowired
	@Qualifier("articlejpa")
	Dao<Article> articledao;
	
	@Autowired
	@Qualifier("reviewjpa")
	Dao<Review> reviewdao;
	
	@Autowired
	@Qualifier("tokenservice")
	IService<Token> tokenService;
	
	@Autowired
	@Qualifier("relationuajpa")
	Dao<RelationUserArticle> relationuadao;
	
	@Autowired
	@Qualifier("zuserjpa")
	Dao<ZUser> zuserDao;
	
	@Value("${msgSuccess}")
	String msgSuccess;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@RequestMapping(value="/index", method={RequestMethod.POST})
	@ResponseBody
	public ModelAndView index(@RequestBody Map<String, String> map) {
		/*签名校验*/
		boolean appSignValidate = HttpUtil.appSignValidate(map);
		if (!appSignValidate) {
			return new ModelAndView("mphone/forumPhone", null);
		}
		/*签名end*/
		
		Map<String, String> userInfo = new HashMap<String, String>(2);
		userInfo.put("userName", map.get("userName"));
		userInfo.put("userPortrait", map.get("userPortrait"));
		
		ZUser zuser = new ZUser();
		zuser.setzUserName(map.get("userName"));
		zuser.setPortrait(map.get("userPortrait"));
		zuserDao.save(zuser);
		
		return new ModelAndView("mphone/forumPhone", "userInfo", userInfo);
		
	}
	
	/*
	 * 读取某个帖子
	 */
	@RequestMapping(value="/readArticle", produces="application/json;charset=UTF-8", method={RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> readArticle(@RequestBody Map<String, String> map) {
		/* 签名校验*/
		String userID = map.get("userID");
		String authorID = map.get("authorID");
		String articleID = map.get("articleID");
		String time = map.get("time");
		String appSign = map.get("app_sign");
		
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("userID", userID);
		packageParams.put("authorID", authorID);
		packageParams.put("articleID", articleID);
		packageParams.put("time", time);
		String sign = StringUtil.createSign(packageParams);
		
		int status = 0;
		String message = "读取成功";
		Map<String, Object> mapResult = new HashMap<String, Object>(3);
		
		if(!appSign.equals(sign)) {
			status = 1;
			message = "签名失败！";
			mapResult.put("status", status);
			mapResult.put("message", message);
			return mapResult;
		}
		/*签名end*/
		
		int articleIDInt = Integer.valueOf(articleID);
		Article article = articleService.get(Article.class, articleIDInt);
		if (article != null ) {
			if (article.getStatus() == 0) {
				status = 2;
				message = "该贴已被管理员下线！";
			}else {
				mapResult.put("article", article);
				article.setReadNum(article.getReadNum() + 1);
				articleService.update(article);
				
				/*向主后台发送请求，修改用户属性值*/
				String msg = HttpUtil.sendRequestMainPropertyChange(authorID, "property", "10");				
				if (!msgSuccess.equals(msg)) {
					status = 4;
					message = "用户属性修改失败！";
				}
				/*向主后台发送请求，修改用户属性值 end*/
			}
		} else {
			status = 3;
			message = "该贴不存在！";
		}
		
		mapResult.put("status", status);
		mapResult.put("message", message);
		return mapResult;
	}
	
	/*
	 * 获取所有帖子
	 */
	@RequestMapping(value="getAllArticles", produces="application/json;charset=UTF-8", method={RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getAllArticles(@RequestBody Map<String, String> map) {
		/* 签名校验*/
		String userID = map.get("userID");
		String appSign = map.get("app_sign");
		
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("userID", userID);
		String sign = StringUtil.createSign(packageParams);
	
		int status = 0;
		String message = "读取成功";
		Map<String, Object> mapResult = new HashMap<String, Object>(3);
		
		if(!appSign.equals(sign)) {
			status = 1;
			message = "签名失败！";
			mapResult.put("status", status);
			mapResult.put("message", message);
			return mapResult;
		}
		/*签名end*/
		
		List<Article> articles = articleService.query(Article.class, null);
		if (articles.size() != 0 && !articles.isEmpty()) {
			mapResult.put("articles", articles);
		}else {
			mapResult.put("articles", null);
		}
		
		return mapResult;
	}
	
	/*
	 *发布新贴
	 */
	@RequestMapping(value="writeArticle", produces="application/json;charset=UTF-8", method={RequestMethod.POST}) 
	@ResponseBody
	public Map<String, String> writeArticle(@RequestBody Map<String, String> map) {
		int status = 0;
		String message = "发帖成功";
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
		
		String tokenValue = map.get("token");
		Token token = tokenService.getBy(Token.class, "token", tokenValue);
		if (null != token) {
			int userID = token.getUserId();
			Article article = new Article();
			article.setAuthorID(userID);
			article.setType(Integer.valueOf(map.get("type")));
			try {
				article.setPublishDate(sdf.parse(map.get("publishDate")));
			} catch (java.text.ParseException e) {
				e.printStackTrace();
			}
			article.setText(map.get("text"));
			article.setTitle(map.get("title"));
			
			articleService.save(article);
			
			/*向主后台发送请求，修改用户属性值*/
			String msg = HttpUtil.sendRequestMainPropertyChange(String.valueOf(userID), "property", "10");				
			if (!msgSuccess.equals(msg)) {
				status = 3;
				message = "用户属性修改失败！";
			}
			/*向主后台发送请求，修改用户属性值 end*/
		} else {
			status = 2;
			message = "请重新登录！";
		}
		
		
		mapResult.put("status", String.valueOf(status));
		mapResult.put("message", message);		
		return mapResult;
	}
	
	/*
	 * 给帖子点赞
	 */
	@RequestMapping(value="likeArticle", produces="application/json;charset=UTF-8", method={RequestMethod.POST})
	@ResponseBody
	public Map<String, String> likeArticle(@RequestBody Map<String, String> map) {
		int status = 0;
		String message = "点赞成功";
		Map<String, String> mapResult = new HashMap<String, String>(2);
		
		/* 签名校验*/
		boolean appSignValidate = HttpUtil.appSignValidate(map);
		if (!appSignValidate) {
			status = 1;
			message = "签名失败！";
			mapResult.put("status", String.valueOf(status));
			mapResult.put("message", message);
			return mapResult;
		}
		/*签名end*/
		
		String tokenValue = map.get("token");
		Token token = tokenService.getBy(Token.class, "token", tokenValue);
		if (null != token) {
			Article article = articleService.get(Article.class, map.get("articleID"));
			if (null != article && article.getStatus() != 0) {
				article.setVoteNum(article.getVoteNum() +1);
				article.setReadNum(article.getReadNum() +1);
				articleService.update(article);
				
				/*向主后台发送请求，修改用户属性值*/
				String msg = HttpUtil.sendRequestMainPropertyChange(String.valueOf(article.getAuthorID()), "property", "10");				
				if (!msgSuccess.equals(msg)) {
					status = 3;
					message = "用户属性修改失败！";
				}
				/*向主后台发送请求，修改用户属性值 end*/
			}else {
				status =4;
				message = "该文章不存在，可能已被管理员下线！";
			}
		} else {
			status = 2;
			message = "请重新登录！";
		}
		
		mapResult.put("status", String.valueOf(status));
		mapResult.put("message", message);	
		return mapResult;
	}
	
	/*
	 * 评论帖子
	 * 簪不实行对评论进行评论的功能
	 */
	@RequestMapping(value="reviewArticle", produces="application/json;charset=UTF-8", method={RequestMethod.POST}) 
	@ResponseBody
	public Map<String, String> reviewArticle(@RequestBody Map<String, String> map) {
		int status = 0;
		String message = "评论成功";
		Map<String, String> mapResult = new HashMap<String, String>(2);
		
		/* 签名校验*/
		boolean appSignValidate = HttpUtil.appSignValidate(map);
		if (!appSignValidate) {
			status = 1;
			message = "签名失败！";
			mapResult.put("status", String.valueOf(status));
			mapResult.put("message", message);
			return mapResult;
		}
		/*签名end*/
		
		String tokenValue = map.get("token");
		Token token = tokenService.getBy(Token.class, "token", tokenValue);
		if (null != token) {
			
		}
		
		return mapResult;
	}
		
	/*
	 * 删除帖子
	 */
	@RequestMapping(value="deleteArticle", produces="application/json;charset=UTF-8", method={RequestMethod.DELETE}) 
	@ResponseBody
	public Map<String, String> deleteArticle(@RequestBody Map<String, String> map) {
		int status = 0;
		String message = "删除成功";
		Map<String, String> mapResult = new HashMap<String, String>(2);
		
		return mapResult;
	}
	
	@RequestMapping(value="test", method={RequestMethod.GET})
	public void testOneToManyMap() {
		Article article1 = new Article();
		article1.setAuthorID(1);
		article1.setReadNum(1);
		article1.setStatus(0);
		article1.setType(0);
		article1.setVoteNum(0);
		articleService.save(article1);
		
		article1 = articledao.get(Article.class, "articleID", "authorid", "1");
		
		Review review1 = new Review();
		review1.setAuthorID(1);
		
		Review review2 = new Review();
		review2.setAuthorID(2);		
		
		article1 = articleService.get(Article.class, article1.getArticleID());
		System.out.println("article1 ID: " + article1.getArticleID());
		article1.addReview(review1);
		article1.addReview(review2);
		articleService.update(article1);
		
		article1 = articleService.get(Article.class, article1.getArticleID());
		System.out.println("article1 ID: " + article1.getArticleID());
		System.out.println("article1 reviews: " + article1.getReviews().size());
		System.out.println("review1 ID: " + review1.getArticle().getArticleID());
		
		article1.deleteReview(review2);	
		articleService.update(article1);
		System.out.println("article1 reviews: " + article1.getReviews().size());
		
		article1 = articleService.get(Article.class, article1.getArticleID());
		Set<Review> reviews = article1.getReviews();
		if (reviews.size() != 0){
			for (Review review: reviews) {
				if (null != review && (review.getAuthorID() == 1)) {
					article1.deleteReview(review);
					System.out.println("article1 reviews: " + article1.getReviews().size());	
					reviewService.del(review);
					articleService.update(article1);
					reviewService.execute( "delete from review where reviewID = " + review.getReviewID());
					List<Review> reviews1 = reviewdao.query(Review.class, "1=1");
					System.out.println(reviews1.size());
					
					
					article1 = articleService.get(Article.class, article1.getArticleID());
					articleService.del(article1);
					reviews1 = reviewdao.query(Review.class, "1=1");
					System.out.println(reviews1.size());
				}
			}
		}
	}
	
	/**
	 * 接收业务逻辑模块发来的用户注册信息
	 * @param map
	 * @return msg：success/failure
	 */
	@RequestMapping(value="userRegister", method={RequestMethod.POST})
	@ResponseBody
	public String userRegister( @RequestParam String userName, @RequestParam String tokenValue) {
		String msg = "failure";
		try {
			userName = URLDecoder.decode(userName, "utf-8");
			tokenValue = URLDecoder.decode(tokenValue, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String scope = "zusername='" + userName + "'";
		ZUser zuser = zuserDao.get(ZUser.class, scope);
		if (null == zuser) {
			zuser = new ZUser();
			zuser.setToken(tokenValue);
			zuser.setzUserName(userName);
			boolean save = zuserDao.save(zuser);
			if (save) {
				return msgSuccess;
			}else {
				return msg;
			}
		}else {
			return msg + ": user withe name " + userName + " already existed";
		}
	}
	
	/**
	 * 接收业务逻辑模块发来的用户登录更新信息
	 * @param map
	 * @return msg：success/failure
	 */
	@RequestMapping(value="tokenChange", method={RequestMethod.POST})
	@ResponseBody
	public String tokenChange(@RequestParam String userName, @RequestParam String tokenValue) {
		String msg = "failure";
		
		try {
			userName = URLDecoder.decode(userName, "utf-8");
			tokenValue = URLDecoder.decode(tokenValue, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		ZUser zuser = zuserDao.get(ZUser.class, "zusername='" + userName + "'");
		if (null != zuser) {
			zuser.setToken(tokenValue);
			if (zuserDao.update(zuser)) {
				return msgSuccess;
			}else {
				return msg;
			}
		}else {
			msg +="\n There is no user with username=" + userName + " in forum";
			return msg;
		}
	}
	
	/**
	 * 接收业务逻辑模块发来的用户注销请求
	 * @param map
	 * @return msg：success/failure
	 */
	@RequestMapping(value="userDel", method={RequestMethod.DELETE})
	@ResponseBody
	public String userDel(@RequestParam String userName) {
		String msg = "failure";
		
		try {
			userName = URLDecoder.decode(userName, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		ZUser zuser = zuserDao.get(ZUser.class, "zusername='" + userName + "'");
		if (null != zuser) {
			boolean del = zuserDao.del(zuser);
			if (del) {
				return msgSuccess;
			}else {
				return msg;
			}
		} else {
			msg += "\n There is no user with username=" + userName + " in forum";
			return msg;
		}
	}
	
	/**
	 * 测试用户和帖子间的关联表RelationUserArticle，
	 * @return 
	 */
	@RequestMapping(value="testUnionID", method={RequestMethod.GET})
	@ResponseBody
	public boolean testUnionID(){
		RelationUserArticle relationUA = new RelationUserArticle();
		UserArticleUnionID unionID = new UserArticleUnionID();
		unionID.setArticleID(1);
		unionID.setUserID(1);
		relationUA.setRead(true);
		relationUA.setVote(false);
		relationUA.setUaID(unionID);
		boolean save = relationuadao.update(relationUA);
		
		String scope = "userID = 1 and articleID = 1";
		List<RelationUserArticle> relationUAs = relationuadao.query(RelationUserArticle.class, scope);
		System.out.println(relationUAs.toString());
		return save;
	}
	
	/**
	 *测试 获取某一个帖子详细信息，跳转到帖子查看页面
	 * @param articleID 点击的帖子ID
	 * @param userUid   用户ID，貌似不需要？
	 * @return   帖子详情页面
	 */
	@RequestMapping(value="testgetArticle", method={RequestMethod.GET})
	@ResponseBody
	public Article getArticle(@RequestParam int articleID, @RequestParam int userUid) {
		ResultInfo rs = new ResultInfo();
		Article article = articleService.get(Article.class, articleID);
		Set<Review> reviews = new HashSet<Review>();
		if (null != article) {
			reviews = article.getReviews();
			if (reviews.size() != 0 || !reviews.isEmpty()) {
				for (Review review : reviews) {
					System.out.println(review.getReviewID());
					System.out.println(review.getText());
					System.out.println(review.getArticle());
					/*JsonConfig config = new JsonConfig();
					config.setExcludes(new String[] {"article"});
					JSONObject json = JSONObject.fromObject(review, config);
					String strJson=json.toString();
					System.out.println(strJson);*/
				}
			}
			rs.setData("article", article);
		}
		//return new ModelAndView("/article/article.jsp", "article", article);
		return article;
	}

}
