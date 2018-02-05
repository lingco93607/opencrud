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
import com.z4.zhazha.forum.pojo.ZUserGroup;
import com.z4.zhazha.forum.service.IService;
import com.z4.zhazha.forum.util.ResultInfo;


@Controller
@RequestMapping("/zusergroup")
public class ZUserGroupController {
	
	@Autowired
	@Qualifier("zusergroupservice")
	private IService<ZUserGroup> zusergroupService;
	
	private static Logger log = LoggerFactory.getLogger(ZUserGroupController.class);
	
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
	public ModelAndView zusergroup(HttpServletRequest request){
	  return new ModelAndView("zusergroup/zusergroup","params",null);		
	}
	
	@RequestMapping(value="/getzusergroups",method={RequestMethod.GET})
	@ResponseBody
	public List<ZUserGroup> getZUserGroups(HttpServletRequest request,  @RequestParam(value = "start") int startPos,
			@RequestParam(value = "end") int endPos)
	{
		List<ZUserGroup> bk = new ArrayList<ZUserGroup>();
		bk=(List<ZUserGroup>) zusergroupService.query(ZUserGroup.class, " 1=1 ", null, startPos, endPos);
		return bk;		
	}
	
	@RequestMapping(value="/getallzusergroups",method={RequestMethod.GET})
	@ResponseBody
	public List<ZUserGroup> getZUserGroups(HttpServletRequest request)
	{
		List<ZUserGroup> bk = new ArrayList<ZUserGroup>();
		bk=(List<ZUserGroup>) zusergroupService.query(ZUserGroup.class, " 1=1 ");
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
	@RequestMapping(value="/getzusergroup/{id}",method={RequestMethod.GET})
	@ResponseBody
	public ModelAndView getZUserGroup(HttpServletRequest request, @PathVariable Long id){
		ZUserGroup bk = null;
		if(id!=-1l&&!"".equals(id)){
		   bk = (ZUserGroup) zusergroupService.get(ZUserGroup.class,id);
		}
		return new ModelAndView("zusergroup/zusergroup_info","params",bk);	
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
	@RequestMapping(value="/getzusergroupList",method={RequestMethod.GET})
	@ResponseBody
	public PaginationList getzusergroupList(@RequestParam Integer pagesize, @RequestParam Integer pagenum,@RequestParam("name") String name){
		PaginationList list = new PaginationList();
		String sql = " 1=1 ";
		try {
			String column_name = new String(name.getBytes("ISO-8859-1"), "UTF-8");
			if(column_name!=null&&!"".equals(column_name)){
				sql+=" and name like "+"'%"+column_name+"%'";
			}
			list = zusergroupService.queryByPage(ZUserGroup.class, sql, pagesize, pagenum);
		} catch (UnsupportedEncodingException e) {
			log.error("getZUserGroupList:", e.getMessage());
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
	@RequestMapping(value = "/savezusergroup", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo saveOrUpdate(HttpServletRequest request,@ModelAttribute ZUserGroup zusergroup) {
		ResultInfo rs = new ResultInfo();
		try {
			zusergroupService.update(zusergroup);
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
	@RequestMapping(value = "/delzusergroups", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo delusers(@RequestParam("zusergroupsId[]") Set<Long> zusergroupIds) {
		ResultInfo rs = new ResultInfo();
		try {
		    zusergroupService.delById(ZUserGroup.class,zusergroupIds);
			rs.setMessage("删除成功");
		} catch (Exception e) {
			rs.setMessage("删除失败");
		}
		return rs;
	}

}
