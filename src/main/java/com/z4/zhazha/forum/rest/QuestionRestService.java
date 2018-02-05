package com.z4.zhazha.forum.rest;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


import org.springframework.stereotype.Service;

import com.z4.zhazha.forum.pojo.Question;
import com.z4.zhazha.forum.service.IService;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Service
@Path("/question")
public class QuestionRestService {
	
	@Autowired
	@Qualifier("questionservice")
	private IService questionService;
	
	
    @GET
    @Path("/getquestion/{id}")
    @Produces("application/json,application/xml")
	public Question getQuestion(@PathParam(value="id")int id )
	{
		Question bk = (Question) questionService.get(Question.class, id);
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
    @Path("/getquestion")
    @Produces("application/json,application/xml")
	public List<Question>  getQuestionByField(@QueryParam(value="field") String fieldName, @QueryParam(value="fieldtype") int fieldtype, @QueryParam(value="fieldvalue") String fieldvalue )
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

			List<Question>  bk = (ArrayList<Question>) questionService.getBy(Question.class, fieldName, s);
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
    @Path("/getquestions")
    @Produces("application/json,application/xml")
	public List<Question> getQuestions(@QueryParam("startpos") int startPos, @QueryParam("endpos") int endPos)
	{
    	List<Question> bk = (ArrayList<Question>)  questionService.query(Question.class, " 1=1 ", null, startPos, endPos);
		return bk;		
	}
    
    
    @POST
    @Path("/addquestion")
    @Consumes("application/json,application/xml")
	public String addQuestion(Question bk)
	{
    	try {
		 questionService.update(bk);
    	}catch (Exception e)
    	{
    		return e.getMessage();
    	}
		return "add question ok";		
	}


}
