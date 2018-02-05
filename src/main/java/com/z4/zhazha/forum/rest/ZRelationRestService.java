package com.z4.zhazha.forum.rest;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


import org.springframework.stereotype.Service;

import com.z4.zhazha.forum.pojo.ZRelation;
import com.z4.zhazha.forum.service.IService;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Service
@Path("/zrelation")
public class ZRelationRestService {
	
	@Autowired
	@Qualifier("zrelationservice")
	private IService zrelationService;
	
	
    @GET
    @Path("/getzrelation/{id}")
    @Produces("application/json,application/xml")
	public ZRelation getZRelation(@PathParam(value="id")int id )
	{
		ZRelation bk = (ZRelation) zrelationService.get(ZRelation.class, id);
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
    @Path("/getzrelation")
    @Produces("application/json,application/xml")
	public List<ZRelation>  getZRelationByField(@QueryParam(value="field") String fieldName, @QueryParam(value="fieldtype") int fieldtype, @QueryParam(value="fieldvalue") String fieldvalue )
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

			List<ZRelation>  bk = (ArrayList<ZRelation>) zrelationService.getBy(ZRelation.class, fieldName, s);
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
    @Path("/getzrelations")
    @Produces("application/json,application/xml")
	public List<ZRelation> getZRelations(@QueryParam("startpos") int startPos, @QueryParam("endpos") int endPos)
	{
    	List<ZRelation> bk = (ArrayList<ZRelation>)  zrelationService.query(ZRelation.class, " 1=1 ", null, startPos, endPos);
		return bk;		
	}
    
    
    @POST
    @Path("/addzrelation")
    @Consumes("application/json,application/xml")
	public String addZRelation(ZRelation bk)
	{
    	try {
		 zrelationService.update(bk);
    	}catch (Exception e)
    	{
    		return e.getMessage();
    	}
		return "add zrelation ok";		
	}


}
