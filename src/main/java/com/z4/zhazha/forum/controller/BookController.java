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
import com.z4.zhazha.forum.pojo.Book;
import com.z4.zhazha.forum.service.IService;
import com.z4.zhazha.forum.util.ResultInfo;


@Controller
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	@Qualifier("bookservice")
	private IService<Book> bookService;
	
	private static Logger log = LoggerFactory.getLogger(BookController.class);
	
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
	public ModelAndView book(HttpServletRequest request){
	  return new ModelAndView("book/book","params",null);		
	}
	
	@RequestMapping(value="/getbooks",method={RequestMethod.GET})
	@ResponseBody
	public List<Book> getBooks(HttpServletRequest request,  @RequestParam(value = "start") int startPos,
			@RequestParam(value = "end") int endPos)
	{
		List<Book> bk = new ArrayList<Book>();
		bk=(List<Book>) bookService.query(Book.class, " 1=1 ", null, startPos, endPos);
		return bk;		
	}
	
	@RequestMapping(value="/getallbooks",method={RequestMethod.GET})
	@ResponseBody
	public List<Book> getBooks(HttpServletRequest request)
	{
		List<Book> bk = new ArrayList<Book>();
		bk=(List<Book>) bookService.query(Book.class, " 1=1 ");
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
	@RequestMapping(value="/getbook/{id}",method={RequestMethod.GET})
	@ResponseBody
	public ModelAndView getBook(HttpServletRequest request, @PathVariable Long id){
		Book bk = null;
		if(id!=-1l&&!"".equals(id)){
		   bk = (Book) bookService.get(Book.class,id);
		}
		return new ModelAndView("book/book_info","params",bk);	
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
	@RequestMapping(value="/getbookList",method={RequestMethod.GET})
	@ResponseBody
	public PaginationList getbookList(@RequestParam Integer pagesize, @RequestParam Integer pagenum,@RequestParam("name") String name){
		PaginationList list = new PaginationList();
		String sql = " 1=1 ";
		try {
			String column_name = new String(name.getBytes("ISO-8859-1"), "UTF-8");
			if(column_name!=null&&!"".equals(column_name)){
				sql+=" and name like "+"'%"+column_name+"%'";
			}
			list = bookService.queryByPage(Book.class, sql, pagesize, pagenum);
		} catch (UnsupportedEncodingException e) {
			log.error("getBookList:", e.getMessage());
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
	@RequestMapping(value = "/savebook", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo saveOrUpdate(HttpServletRequest request,@ModelAttribute Book book) {
		ResultInfo rs = new ResultInfo();
		try {
			bookService.update(book);
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
	@RequestMapping(value = "/delbooks", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo delusers(@RequestParam("booksId[]") Set<Long> bookIds) {
		ResultInfo rs = new ResultInfo();
		try {
		    bookService.delById(Book.class,bookIds);
			rs.setMessage("删除成功");
		} catch (Exception e) {
			rs.setMessage("删除失败");
		}
		return rs;
	}

}
