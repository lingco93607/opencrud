package com.z4.zhazha.forum.controller;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;
import com.z4.zhazha.forum.dao.Dao;
import com.z4.zhazha.forum.dao.impl.DaoSupport;
import com.z4.zhazha.forum.dto.PaginationList;
import com.z4.zhazha.forum.pojo.AMessage;
import com.z4.zhazha.forum.pojo.ActiveImg;
import com.z4.zhazha.forum.pojo.Article;
import com.z4.zhazha.forum.pojo.ArticleInfo;
import com.z4.zhazha.forum.pojo.HotArticleInfo;
import com.z4.zhazha.forum.pojo.RelationUserArticle;
import com.z4.zhazha.forum.pojo.Review;
import com.z4.zhazha.forum.pojo.UserArticleUnionID;
import com.z4.zhazha.forum.pojo.ZMessage;
import com.z4.zhazha.forum.pojo.ZUser;
import com.z4.zhazha.forum.rabbitmq.RabbitMessage;
import com.z4.zhazha.forum.rabbitmq.RmqProducer;
import com.z4.zhazha.forum.service.IService;
import com.z4.zhazha.forum.service.JedisClient;
import com.z4.zhazha.forum.util.FileUtil;
import com.z4.zhazha.forum.util.HttpUtil;
import com.z4.zhazha.forum.util.JsonUtils;
import com.z4.zhazha.forum.util.ResultInfo;

/**
 * 
 * @author xiaoy
 * @date 2017年10月24日10:46:45
 * 
 */

@Controller
@RequestMapping("/article")
public class ArticleController {
	
	@Autowired
	@Qualifier("articleservice")
	private IService<Article> articleService;
	
	@Autowired
	@Qualifier("articlejpa")
	Dao<Article> articleDao;
	
	@Autowired
	@Qualifier("reviewservice")
	private IService<Review> reviewService;
	
	@Autowired
	@Qualifier("relationuajpa")
	Dao<RelationUserArticle> relationuaDao;
	
	@Autowired
	@Qualifier("zmessagejpa")
	Dao<ZMessage> zmsgDao;
	
	@Autowired
	@Qualifier("zuserservice")
	private IService<ZUser> zuserService;
	
	@Autowired
	private JedisClient jedisClient;

	@Resource
	private RmqProducer rmqProducer;
	
	@Value("${msgSuccess}")
	String msgSuccess;
	
	@Value("${userDefaultName}")
	String userDefaultName;
	
	@Value("${userDefaultID}")
	int userDefaultID;
	
	@Value("${userDefaultPortrait}")
	String userDefaultPortrait;
	
	@Value("${MESSAGE_TYPE_MY}")
	int MESSAGE_TYPE_MY;
	
	@Value("${MESSAGE_TYPE_COMMENT}")
	int MESSAGE_TYPE_COMMENT;
	
	@Value("${MESSAGE_TYPE_PRAISE}")
	int MESSAGE_TYPE_PRAISE;
	
	@Value("${MESSAGE_TARGET_TYPE_ARTICLE}")
	int MESSAGE_TARGET_TYPE_ARTICLE;
	
	@Value("${MESSAGE_TARGET_TYPE_REVIEW}")
	int MESSAGE_TARGET_TYPE_REVIEW;
	
	@Value("${MESSAGE_TARGET_TYPE_QUESTION}")
	int MESSAGE_TARGET_TYPE_QUESTION;
	
	@Value("${forumImgPath}")
	String forumImgPath;
	
	private static Logger log = LoggerFactory.getLogger(ArticleController.class);
	
	/**
	 * 判断用户是否有足够的体力值进行发帖
	 * @author xintt
	 * @param 用户ID
	 * @return 状态值status:0-可以发帖，1-体力不足，2-其他异常
	 */
	@RequestMapping(value="isWriteArticle",  produces="application/json;charset=UTF-8", method={RequestMethod.GET})
	@ResponseBody
	public String isWriteArticle(HttpServletRequest request, @RequestParam int userUid) {
		int status = 0;
		
		/*向主后台发送请求，获取用户体力值*/
		String msg = HttpUtil.sendRequestMainGetPropertyValue(userUid, "money" );
		if(msg != "") {
			try{
				int physicalStrength = Integer.valueOf(msg);
				if (physicalStrength < 0) {
					status = 2;
				} else if (physicalStrength == 0) {
					status = 1;
				}
			}catch(Exception e) {
				e.printStackTrace();
				status = 3;
			}
		} else {
			log.error("错误，向主后台发送请求，获取用户体力值为空");
		}
		
		//return String.valueOf(status);
		return "0";
	}
	
	/**
     * 发帖图像异步上传图像并保存图像
     * @author xiaoyu
     * 2017年11月30日09:54:14
     * 
     */
	 @RequestMapping(value = "/saveArticleImg",  produces="application/json;charset=UTF-8", method = RequestMethod.POST)
	 @ResponseBody
	 public String saveArticleImage(@RequestParam("file") MultipartFile file) throws IOException {
		 String fileName = file.getOriginalFilename();
		
         String tmpFileName = fileName; // 上传的文件名
         int dot = tmpFileName.lastIndexOf('.');
         String ext = ""; 
         if ((dot > -1) && (dot < (tmpFileName.length() - 1))) {
             ext = tmpFileName.substring(dot + 1);
         }
         String targetFileName = "";
         String imgUrl = "";
         // 其他文件格式不处理
         if ("png".equalsIgnoreCase(ext) || "jpg".equalsIgnoreCase(ext) || "gif".equalsIgnoreCase(ext) || "jpeg".equalsIgnoreCase(ext)) {
             // 重命名上传的文件名
             targetFileName = FileUtil.renameFileName(tmpFileName);
             String tempImgPath = forumImgPath + targetFileName;
             File newFile = new File(tempImgPath);
             //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
             file.transferTo(newFile);
             //System.out.println(targetFileName.split("\\.")[0]);
             String[] imgSplit = targetFileName.split("\\.");
             System.out.println(imgSplit[0]);
             //imgUrl = "http://106.15.193.89:8080/zhazhaForum/action/article/img/" + imgSplit[0] + "/" + imgSplit[1];
             imgUrl = "../../action/article/img/" + imgSplit[0] + "/" + imgSplit[1];
         }
         return imgUrl;
	 }
	
	
	 /**
     * 通过url请求返回图像的字节流  
     * @author xiaoyu
     * 2017年11月29日19:06:17
     * 
     */
    @RequestMapping(value = "img/{forumImg}/{formatImg}")
    public void getIcon(@PathVariable("forumImg") String forumImg, @PathVariable(value = "formatImg") String formatImg,
    		HttpServletRequest request, HttpServletResponse response) throws IOException {

        String fileName = forumImgPath + forumImg + "." + formatImg;
        File file = new File(fileName);
        //判断文件是否存在如果不存在就返回默认图标
        if(!(file.exists() && file.canRead())) {
        	System.out.println("图像不存在");
        }
        FileInputStream inputStream = new FileInputStream(file);
        byte[] data = new byte[(int)file.length()];
        int length = inputStream.read(data);
        inputStream.close();
        
        response.setContentType("image/"+ formatImg);
        OutputStream stream = response.getOutputStream();
        stream.write(data);
        stream.flush();
        stream.close();
    }
    
	
	/**
	 * 发帖
	 * @param userUid
	 * @param title
	 * @param text
	 * @return 发帖状态：0-发送成功；1-魅力值已大上限；2-体力值已扣完；3-其他异常
	 */
	@RequestMapping(value="writeArticle", produces="application/json;charset=UTF-8", method={RequestMethod.POST})
	@ResponseBody
	public String writeArticle(@RequestParam int userUid, @RequestParam String title, @RequestParam String text) {
		int status = 0;
		Article article = new Article();
		article.setAuthorID(userUid);
		article.setTitle(title);
		article.setText(text);
		article.setPublishDate(new Date());
		article.setStatus(1);
		article.setVoteNum(0);
		article.setReadNum(0);
		if(!articleService.save(article)) {
			status = 1;
		};
		
		sendRMQPChange( "propertyChange", "money", article.getAuthorID(), "1");
		return String.valueOf(status);
	}	
	
	

	/**
	 * 打开阅读页面，获取帖子信息
	 * @param startPos
	 * @param endPos
	 * @param useruuid
	 * @return
	 */
	@RequestMapping(value = "/getArticles", produces="application/json;charset=UTF-8")
	@ResponseBody
	public ResultInfo getArticleInfo(@RequestParam(value = "start") int startPos,
			@RequestParam(value = "end") int endPos, @RequestParam(value = "useruuid") int useruuid) {
		ResultInfo rs = new ResultInfo();
		
		List<Article> articles = new ArrayList<Article>();
		List<ArticleInfo> articleInfo = new ArrayList<ArticleInfo>();
		articles = (List<Article>) articleService.query(Article.class, "1=1 order by publishdate desc", null, startPos, endPos);
		
		String scope = "userID = " + useruuid;				
		if(!articles.isEmpty() && articles.size() != 0) {	
			ZUser zuserInfo = new ZUser();
			for(Article article: articles) {							
				ArticleInfo artInfo = new ArticleInfo();
				zuserInfo = zuserService.get(ZUser.class, article.getAuthorID());
				if (null != zuserInfo) {					
					artInfo.setzNickname(zuserInfo.getzUserName());
					artInfo.setArticleAuthorId(zuserInfo.getzUserId());
					artInfo.setPortrait(zuserInfo.getPortrait());
				} else {
					//该用户已注销 or 测试阶段查询得authorID与userID不匹配
					artInfo.setzNickname(userDefaultName);
					artInfo.setArticleAuthorId(userDefaultID);
					artInfo.setPortrait(userDefaultPortrait);
				}
				artInfo.setArticle(article);	
				scope += " and articleID=" + article.getArticleID();
				RelationUserArticle re = relationuaDao.get(RelationUserArticle.class, scope);
				if (null == re) {
					artInfo.setRead(false);
					artInfo.setVote(false);
				} else {
					artInfo.setVote(re.isVote());
					artInfo.setRead(re.isRead());
				}			
				
				articleInfo.add(artInfo);		
			}			
			rs.setData("articleInfo", articleInfo);	
		} 
		return rs;
	}
	
	/**
	 * 获取某一个帖子详细信息，跳转到帖子查看页面
	 * @param articleID 点击的帖子ID
	 * @param userUid   用户ID
	 * @return   帖子详细信息
	 */
	@RequestMapping(value="getArticle",  produces="application/json;charset=UTF-8", method={RequestMethod.GET})
	@ResponseBody
	public ResultInfo getArticle(@RequestParam int articleID, @RequestParam int userUid) {
		ResultInfo rs = new ResultInfo();
		Article article = articleService.get(Article.class, articleID);
		if (null != article) {
			/*维护用户与帖子间的关系：已阅读该帖*/
			UserArticleUnionID union = new UserArticleUnionID();
			union.setArticleID(articleID);
			union.setUserID(userUid);			
			RelationUserArticle relation = new RelationUserArticle();
			relation.setUaID(union);
			relation.setRead(true);
			//若用户与该帖间的关系存在，则更新；若不存在，则插入该记录
			relationuaDao.update(relation);     
			
			rs.setStatus(true);
			rs.setMessage(msgSuccess);
			rs.setData("article", article);
			rs.setData("isVote", relation.isVote());
			
			/*向主后台发送请求，修改用户属性值*/
			sendRMQPChange( "propertyChange", "money", article.getAuthorID(), "1");
			article.setReadNum(article.getReadNum()+1);
			articleService.update(article);
			return rs;
		}else {
			rs.setStatus(false);
			return rs;
		}
	}
	
	/**
	 * 传出10个热帖
	 * @author dengzs
	 * @param 需要传出的贴子数量
	 * @return  bean对象
				articleID：文章评论ID
				title:文章帖子
				type：文章属性
				publishDate: 文章发布时间
				authorID：作者ID
				authorNickname： 发帖人的昵称
	 */
	@RequestMapping(value="getHotAritcal",  produces="application/json;charset=UTF-8", method={RequestMethod.GET})
	@ResponseBody
	public ResultInfo getHotAritcal(@RequestParam int num) {
		ResultInfo rs = new ResultInfo();
		
		List<Article> articles = new ArrayList<Article>();
		articles = (List<Article>) articleService.query(Article.class,"1=1 order by readnum desc", null, 0 ,num );
		
		List<HotArticleInfo> hotArticleInfos = new ArrayList<HotArticleInfo>();
		if(!articles.isEmpty() && articles.size() != 0) {	
			ZUser zuserInfo = new ZUser();
			for(Article article: articles) {							
				HotArticleInfo hotArtInfo = new HotArticleInfo();
				zuserInfo = zuserService.get(ZUser.class, article.getAuthorID());
				if (null != zuserInfo) {					
					hotArtInfo.setzNickname(zuserInfo.getzUserName());
				} else {
					//该用户已注销 or 测试阶段查询得authorID与userID不匹配
					hotArtInfo.setzNickname(userDefaultName);
				}
				hotArtInfo.setArticleID(article.getArticleID());
				hotArtInfo.setAuthorID(article.getAuthorID());
				hotArtInfo.setPublishDate(article.getPublishDate());
				hotArtInfo.setStatus(article.getStatus());
				hotArtInfo.setTitle(article.getTitle());
				hotArtInfo.setType(article.getType());
				hotArticleInfos.add(hotArtInfo);		
			}			
			rs.setData("hotArticleInfo", hotArticleInfos);	
		} 
		return rs;
	}
	
	/**
	 * 接口LT007A
	 * method:get,
		async: false,
		传入参数说明：num=10表示请求10个活动轮播图
	 *传出说明：
	 *		bean对象
	 *		articleID：文章评论ID
	 *		title:文章帖子
	 *		type：文章属性
	 *		articleFirstPicPath：首张图片地址
	 *		publishDate: 活动图像发表时间
	 *
	 */
	@RequestMapping(value="getActiveImg",  produces="application/json;charset=UTF-8", method={RequestMethod.GET})
	@ResponseBody
	public ResultInfo getActiveImg(@RequestParam int num) {
		ResultInfo rs = new ResultInfo();
		List<Article> articles = new ArrayList<Article>();
		articles = (List<Article>) articleService.query(Article.class,"1=1 order by readnum desc", null, 0 ,num );
		
		List<ActiveImg> activeImgs = new ArrayList<ActiveImg>();
		if(!articles.isEmpty() && articles.size() != 0) {	
			
			for(Article article: articles) {							
				ActiveImg activeImg = new ActiveImg();
				activeImg.setArticleID(article.getArticleID());
				activeImg.setPublishDate(article.getPublishDate());
				activeImg.setTitle(article.getTitle());
				activeImg.setType(article.getType());
				activeImg.setFirstImgUrl(article.getFirstImgUrl());
				activeImgs.add(activeImg);		
			}			
			rs.setData("activeImgs", activeImgs);	
		} 
		return rs;
	}
	
	/**
	 * 
	 * @param articleID 点击的帖子ID
	 * @param review ID
	 * @param userUid   用户ID  暂未用
	 * @return   子评论信息
	 */
	@RequestMapping(value="getCReviews",  produces="application/json;charset=UTF-8", method={RequestMethod.GET})
	@ResponseBody
	public ResultInfo getCReviews(@RequestParam int articleID, @RequestParam int userUid, @RequestParam int reviewID) {
		ResultInfo rs = new ResultInfo();
		Article article = articleService.get(Article.class, articleID);
		if (null != article) {
			Review newReview = reviewService.get(Review.class, reviewID);
			rs.setData("review", newReview);
			return rs;
		}
		return null;
	}
	
	/**
	 * 
	 * @param articleID 点击的帖子ID
	 * @param userUid   用户ID  暂未用
	 * @return   更加帖子ID获取评论信息
	 */
	@RequestMapping(value="getReviews", method={RequestMethod.GET})
	@ResponseBody
	public ResultInfo getReviews(@RequestParam int articleID, @RequestParam int userUid) {
		ResultInfo rs = new ResultInfo();
		Article article = articleService.get(Article.class, articleID);
		if (null != article) {
			rs.setData("review", article.getReviews());
			return rs;
		}
		return null;
	}
	
	/**
	 * 给帖子点赞
	 * @param articleID
	 * @param userUid
	 * @return 若点赞成功，返回true,否则返回false
	 */
	@RequestMapping(value="voteArticle",  produces="application/json;charset=UTF-8", method={RequestMethod.POST})
	@ResponseBody
	public boolean voteArticle(@RequestParam int articleID, @RequestParam int userUid, @RequestParam boolean vote) {
		Article article = articleService.get(Article.class, articleID);
		if (vote) {
			article.setVoteNum(article.getVoteNum()+1);
			sendRMQZMsg(articleID, "article", userUid, MESSAGE_TYPE_PRAISE);
		}else {
			article.setVoteNum(article.getVoteNum()-1);
		}
		/*维护用户与帖子间的关系：(取消）点赞*/
		RelationUserArticle relationUA = new RelationUserArticle();
		UserArticleUnionID union = new UserArticleUnionID();
		union.setArticleID(articleID);
		union.setUserID(userUid);
		relationUA.setUaID(union);
		relationUA.setVote(vote);
		relationuaDao.update(relationUA);  
			
		sendRMQPChange( "propertyChange", "money", articleID, "1");
		vote = articleService.update(article);
		return vote;
	}
	
	/**
	 * 评论一个帖子
	 * @param articleID
	 * @param userUid
	 * @param commonText
	 * @return
	 */
	@RequestMapping(value="reviewArticle",  produces="application/json;charset=UTF-8", method={RequestMethod.POST})
	@ResponseBody
	public ResultInfo reviewArticle(@RequestParam int articleID, @RequestParam int userUid, @RequestParam String commonText) {
		ResultInfo info = new ResultInfo();
		
		Review review = new Review();
		Article article = articleService.get(Article.class, articleID);
		review.setArticle(article);
		review.setAuthorID(userUid);
		review.setReviewDate(new Date());
		review.setText(commonText);
		
		article.addReview(review);
		info.setStatus(articleService.update(article));
		
		sendRMQZMsg(articleID, "article", userUid, MESSAGE_TYPE_COMMENT);
		return info;
	}
	
	private void sendRMQPChange( String method, String prop, int heroID, String value) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("method", method);
		param.put("property", prop);
		param.put("heroID", String.valueOf(heroID));
		param.put("value", value);
		RabbitMessage msg=new RabbitMessage("forumExchange","forumQueueLocal", param);
		rmqProducer.sendMessage(msg);
	}
	
	private void sendRMQZMsg( int obj, String objType, int writer, int msgType) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("obj", obj);
		param.put("objType", "article");
		param.put("writer", writer);
		param.put("msgType", msgType);
		RabbitMessage msg=new RabbitMessage("zMessageExchange","zMessageQueue", param);
		rmqProducer.sendMessage(msg);
	}
	
}
