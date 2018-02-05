package com.z4.zhazha.forum.controller;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.servlet.ModelAndView;

import com.z4.zhazha.forum.dto.PaginationList;
import com.z4.zhazha.forum.pojo.Article;
import com.z4.zhazha.forum.pojo.CReview;
import com.z4.zhazha.forum.pojo.Review;
import com.z4.zhazha.forum.service.IService;
import com.z4.zhazha.forum.util.HttpUtil;
import com.z4.zhazha.forum.util.ResultInfo;

/**
 * 
 * @author xintt
 * @date 2017.10.26
 */
@Controller
@RequestMapping("/review")
public class ReviewController {
	
	@Autowired
	@Qualifier("reviewservice")
	private IService<Review> reviewService;
	
	@Value("${msgSuccess}")
	String msgSuccess;
	
	private static Logger log = LoggerFactory.getLogger(ReviewController.class);
	
	/**
	 * 对一级评论进行评论
	 * @param articleID
	 * @param reviewID
	 * @param userUid
	 * @param text
	 * @return 评论状态：0-成功；1-失败
	 */
	@RequestMapping(value="writeCReview", produces="application/json;charset=UTF-8",method={RequestMethod.POST})
	@ResponseBody
	public int writeReview(@RequestParam int articleID, @RequestParam int reviewID, @RequestParam int userUid,
			@RequestParam String text) {
		int status = 0;
		
		CReview creview = new CReview();
		creview.setText(text);
		creview.setTime(new Date());
		
		Review review = reviewService.get(Review.class, reviewID);
		review.addCReview(creview);
		boolean writeCReview = reviewService.update(review);
		if (!writeCReview) {
			status = 1;
		}
		return status;
	}
	
	@RequestMapping(value = "voteReview", produces="application/json;charset=UTF-8",method = {RequestMethod.GET})
	@ResponseBody
	public int voteReview(@RequestParam int articleID, @RequestParam int reviewID, @RequestParam int userUid) {
		int status = 0;
		
		Review review = reviewService.get(Review.class, reviewID);
		String msg = HttpUtil.sendRequestMainPropertyChange(String.valueOf(review.getAuthorID()), "property", "1");
		if (!msgSuccess.equals(msg)) {				
			/*若帖子查询成功，但是帖子作者属性值修改失败，如何处理？
			 * 加入请求队列，一定时间间隔后再次发送请求？
			 * 还是由主后台存储失败的请求，一定间隔后再次处理？*/
		}		
		return status;
	}
	
	/**
	 * <p>跳转到book的list页面<br/>
	 * @title book<br/>
	 * @date 2014年11月21日 下午2:53:21<br/>
	 * @author dongjz<br/>
	 * @version v1.0.0
	 * </p>
	 * 
	 * @param request
	 * @return
	 * <pre>
	 * 无匹配数据：
	 * 成功：
	 * 失败：
	 * </pre>
	 */
	@RequestMapping(value="/index",method={RequestMethod.GET})
	public ModelAndView review(HttpServletRequest request){
	  return new ModelAndView("review/review","params",null);		
	}
	
	@RequestMapping(value="/getreviews",method={RequestMethod.GET})
	@ResponseBody
	public List<Review> getReviews(HttpServletRequest request,  @RequestParam(value = "start") int startPos,
			@RequestParam(value = "end") int endPos)
	{
		List<Review> bk = new ArrayList<Review>();
		bk=(List<Review>) reviewService.query(Review.class, " 1=1 ", null, startPos, endPos);
		return bk;		
	}
	
	@RequestMapping(value="/getallreviews",method={RequestMethod.GET})
	@ResponseBody
	public List<Review> getReviews(HttpServletRequest request)
	{
		List<Review> bk = new ArrayList<Review>();
		bk=(List<Review>) reviewService.query(Review.class, " 1=1 ");
		return bk;		
	}
	
	/**
	 * <p>根据书的ID获得对应的书，没有则返回空<br/>
	 * @title getBook<br/>
	 * @date 2014年11月21日 下午2:44:40<br/>
	 * @author dongjz<br/>
	 * @version v1.0.0
	 * </p>
	 * 
	 * @param request
	 * @param id
	 * @param model
	 * @return
	 * <pre>
	 * 无匹配数据：
	 * 成功：
	 * 失败：
	 * </pre>
	 */
	@RequestMapping(value="/getreview/{id}",method={RequestMethod.GET})
	@ResponseBody
	public ModelAndView getReview(HttpServletRequest request, @PathVariable Long id){
		Review bk = null;
		if(id!=-1l&&!"".equals(id)){
		   bk = (Review) reviewService.get(Review.class,id);
		}
		return new ModelAndView("review/review_info","params",bk);	
	}
	
	/**
	 * <p>分页查询<br/>
	 * @title getBooks<br/>
	 * @date 2014年11月21日 下午2:54:59<br/>
	 * @author dongjz<br/>
	 * @version v1.0.0
	 * </p>
	 * 
	 * @param pagesize
	 * @param pagenum
	 * @param name
	 * @return
	 * <pre>
	 * 无匹配数据：
	 * 成功：
	 * 失败：
	 * </pre>
	 */
	@RequestMapping(value="/getreviewList",method={RequestMethod.GET})
	@ResponseBody
	public PaginationList getreviewList(@RequestParam Integer pagesize, @RequestParam Integer pagenum,@RequestParam("name") String name){
		PaginationList list = new PaginationList();
		String sql = " 1=1 ";
		try {
			String column_name = new String(name.getBytes("ISO-8859-1"), "UTF-8");
			if(column_name!=null&&!"".equals(column_name)){
				sql+=" and name like "+"'%"+column_name+"%'";
			}
			list = reviewService.queryByPage(Review.class, sql, pagesize, pagenum);
		} catch (UnsupportedEncodingException e) {
			log.error("getReviewList:", e.getMessage());
		}
		return list;		
	}

	/**
	 * <p>保存<br/>
	 * @title saveOrUpdate<br/>
	 * @date 2014年11月21日 下午3:00:06<br/>
	 * @author dongjz<br/>
	 * @version v1.0.0
	 * </p>
	 * 
	 * @param request
	 * @param book
	 * @return
	 * <pre>
	 * 无匹配数据：
	 * 成功：
	 * 失败：
	 * </pre>
	 */
	@RequestMapping(value = "/savereview", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo saveOrUpdate(HttpServletRequest request,@ModelAttribute Review review) {
		ResultInfo rs = new ResultInfo();
		try {
			reviewService.update(review);
			rs.setMessage("保存成功");
		} catch (Exception e) {
			rs.setMessage("保存失败");
		}
		return rs;
	}
	
	/**
	 * <p>删除记录<br/>
	 * @title delusers<br/>
	 * @date 2014年11月24日 上午10:10:09<br/>
	 * @author dongjz<br/>
	 * @version v1.0.0
	 * </p>
	 * 
	 * @param bookIds
	 * @return
	 * <pre>
	 * 无匹配数据：
	 * 成功：
	 * 失败：
	 * </pre>
	 */
	@RequestMapping(value = "/delreviews", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo delusers(@RequestParam("reviewsId[]") Set<Long> reviewIds) {
		ResultInfo rs = new ResultInfo();
		try {
		    reviewService.delById(Review.class,reviewIds);
			rs.setMessage("删除成功");
		} catch (Exception e) {
			rs.setMessage("删除失败");
		}
		return rs;
	}

}
