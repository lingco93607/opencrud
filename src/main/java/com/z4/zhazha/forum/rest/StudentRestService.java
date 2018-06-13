package com.z4.zhazha.forum.rest;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


import org.springframework.stereotype.Service;

import com.z4.zhazha.forum.pojo.Student;
import com.z4.zhazha.forum.service.IService;





import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Service
@Path("/student")
public class StudentRestService {
	
	@Autowired
	@Qualifier("studentservice")
	private IService studentService;
	
	
    @GET
    @Path("/getstudent/{id}")
    @Produces("application/json,application/xml")
	public Student getStudent(@PathParam(value="id")int id )
	{
		Student bk = (Student) studentService.get(Student.class, id);
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
    @Path("/getstudent")
    @Produces("application/json,application/xml")
	public List<Student>  getStudentByField(@QueryParam(value="field") String fieldName, @QueryParam(value="fieldtype") int fieldtype, @QueryParam(value="fieldvalue") String fieldvalue )
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

			List<Student>  bk = (ArrayList<Student>) studentService.getBy(Student.class, fieldName, s);
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
    @Path("/getstudents")
    @Produces("application/json,application/xml")
	public List<Student> getStudents(@QueryParam("startpos") int startPos, @QueryParam("endpos") int endPos)
	{
    	List<Student> bk = (ArrayList<Student>)  studentService.query(Student.class, " 1=1 ", null, startPos, endPos);
		return bk;		
	}
    
    
    @POST
    @Path("/addstudent")
    @Consumes("application/json,application/xml")
	public String addStudent(Student bk)
	{
    	try {
		 studentService.update(bk);
    	}catch (Exception e)
    	{
    		return e.getMessage();
    	}
		return "add student ok";		
	}


}
