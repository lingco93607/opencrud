package com.z4.zhazha.forum.controller;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.z4.zhazha.forum.dto.PaginationList;
import com.z4.zhazha.forum.pojo.Question;
import com.z4.zhazha.forum.service.IService;
import com.z4.zhazha.forum.util.ResultInfo;


@Controller
@RequestMapping("/question")
public class QuestionController {
	
	@Autowired
	@Qualifier("questionservice")
	private IService<Question> questionService;
	
	private static Logger log = LoggerFactory.getLogger(QuestionController.class);
	
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
	public ModelAndView question(HttpServletRequest request){
	  return new ModelAndView("question/question","params",null);		
	}
	
	@RequestMapping(value="/getquestions",method={RequestMethod.GET})
	@ResponseBody
	public List<Question> getQuestions(HttpServletRequest request,  @RequestParam(value = "start") int startPos,
			@RequestParam(value = "end") int endPos)
	{
		List<Question> bk = new ArrayList<Question>();
		bk=(List<Question>) questionService.query(Question.class, " 1=1 ", null, startPos, endPos);
		return bk;		
	}
	
	@RequestMapping(value="/getallquestions",method={RequestMethod.GET})
	@ResponseBody
	public List<Question> getQuestions(HttpServletRequest request)
	{
		List<Question> bk = new ArrayList<Question>();
		bk=(List<Question>) questionService.query(Question.class, " 1=1 ");
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
	@RequestMapping(value="/getquestion/{id}",method={RequestMethod.GET})
	@ResponseBody
	public ModelAndView getQuestion(HttpServletRequest request, @PathVariable Long id){
		Question bk = null;
		if(id!=-1l&&!"".equals(id)){
		   bk = (Question) questionService.get(Question.class,id);
		}
		return new ModelAndView("question/question_info","params",bk);	
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
	@RequestMapping(value="/getquestionList",method={RequestMethod.GET})
	@ResponseBody
	public PaginationList getquestionList(@RequestParam Integer pagesize, @RequestParam Integer pagenum,@RequestParam("name") String name){
		PaginationList list = new PaginationList();
		String sql = " 1=1 ";
		try {
			String column_name = new String(name.getBytes("ISO-8859-1"), "UTF-8");
			if(column_name!=null&&!"".equals(column_name)){
				sql+=" and name like "+"'%"+column_name+"%'";
			}
			list = questionService.queryByPage(Question.class, sql, pagesize, pagenum);
		} catch (UnsupportedEncodingException e) {
			log.error("getQuestionList:", e.getMessage());
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
	@RequestMapping(value = "/savequestion", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo saveOrUpdate(HttpServletRequest request,@ModelAttribute Question question) {
		ResultInfo rs = new ResultInfo();
		try {
			questionService.update(question);
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
	@RequestMapping(value = "/delquestions", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo delusers(@RequestParam("questionsId[]") Set<Long> questionIds) {
		ResultInfo rs = new ResultInfo();
		try {
		    questionService.delById(Question.class,questionIds);
			rs.setMessage("删除成功");
		} catch (Exception e) {
			rs.setMessage("删除失败");
		}
		return rs;
	}

}
