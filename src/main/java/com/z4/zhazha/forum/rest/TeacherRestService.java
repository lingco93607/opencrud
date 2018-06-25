package com.z4.zhazha.forum.rest;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


import org.springframework.stereotype.Service;

import com.z4.zhazha.forum.pojo.Teacher;
import com.z4.zhazha.forum.service.IService;





import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Service
@Path("/teacher")
public class TeacherRestService {
	
	@Autowired
	@Qualifier("teacherservice")
	private IService teacherService;
	
	
    @GET
    @Path("/getteacher/{id}")
    @Produces("application/json,application/xml")
	public Teacher getTeacher(@PathParam(value="id")int id )
	{
		Teacher bk = (Teacher) teacherService.get(Teacher.class, id);
		return bk;		
	}
	
	/**
	 * get object from a specific field
	 * @param fieldName
	 * @param fieldtype 1 string 2 int 3 float 4 double
	 * @param fieldvalue
	 * @return
	 */
	@GET
    @Path("/getteacher")
    @Produces("application/json,application/xml")
	public List<Teacher>  getTeacherByField(@QueryParam(value="field") String fieldName, @QueryParam(value="fieldtype") int fieldtype, @QueryParam(value="fieldvalue") String fieldvalue )
	{
	
			try {
			Serializable s;
			switch (fieldtype) {
			case 1:
				s = fieldvalue;
				break;
			case 2:
				s = Integer.valueOf(fieldvalue);
				break;
			case 3:
				s = Float.valueOf(fieldvalue);
				break;
			case 4:
				s = Double.valueOf(fieldvalue);
				break;
			default:
				throw new Exception("not valid value type");
			}

			List<Teacher>  bk = (ArrayList<Teacher>) teacherService.getBy(Teacher.class, fieldName, s);
			return bk;	
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	
	}
    

    @GET
    @Path("/getteachers")
    @Produces("application/json,application/xml")
	public List<Teacher> getTeachers(@QueryParam("startpos") int startPos, @QueryParam("endpos") int endPos)
	{
    	List<Teacher> bk = (ArrayList<Teacher>)  teacherService.query(Teacher.class, " 1=1 ", null, startPos, endPos);
		return bk;		
	}
    
    
    @POST
    @Path("/addteacher")
    @Consumes("application/json,application/xml")
	public String addTeacher(Teacher bk)
	{
    	try {
		 teacherService.update(bk);
    	}catch (Exception e)
    	{
    		return e.getMessage();
    	}
		return "add teacher ok";		
	}


}
