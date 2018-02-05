package com.z4.zhazha.forum.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;



public class UtilFunctions {

	

	
	public static boolean getChance(double d)
	{
		double r = Math.random() * 100;
		if (r < d) 
		    return true;
		else 
		    return false;

	}
	
	public static String getTableColQuery(String tableName, String colName)
	{
		String query = "from {tableName} where {columnName} = :value";
		query = query.replace("{tableName}", tableName);
		query = query.replace("{columnName}", colName);
		return query;
	}
	
	public static String calSkill()
	{
		ScriptEngineManager manager = new ScriptEngineManager ();  
        ScriptEngine engine = manager.getEngineByName ("js");  
        String script = "(1*880+1)*0.02";  
        try {  
            return (String) engine.eval (script);  
        } catch (ScriptException e) {  
            e.printStackTrace();
            return null;
        }
	}
	
	public static String getHashString(String password)
	{
        MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
 
			byte byteData[] = md.digest();	
	        StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
		         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		        }
		 
		        System.out.println("Digest(in hex format):: " + sb.toString());
		 
		        //convert the byte to hex format method 2
		        StringBuffer hexString = new StringBuffer();
		    	for (int i=0;i<byteData.length;i++) {
		    		String hex=Integer.toHexString(0xff & byteData[i]);
		   	     	if(hex.length()==1) hexString.append('0');
		   	     	hexString.append(hex);
		    	}
		    	System.out.println("Digest(in hex format):: " + hexString.toString());
		    	return hexString.toString();
		    	
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	
	//function to calculate distance between two lat lon, unit=k means km
	public static double distance(double lat1, double lon1, double lat2, double lon2, char unit)
	{
	    double theta = lon1 - lon2;
	    double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
	    dist = Math.acos(dist);
	    dist = rad2deg(dist);
	    dist = dist * 60 * 1.1515;
	     
	    if (unit == 'K') {
	        dist = dist * 1.609344;
	    }
	    else if (unit == 'N') {
	        dist = dist * 0.8684;
	    }
	     
	    return (dist);
	}
	 
	/**
	 * <p>This function converts decimal degrees to radians.</p>
	 * 
	 * @param deg - the decimal to convert to radians
	 * @return the decimal converted to radians
	 */
	private  final static double deg2rad(double deg)
	{
	    return (deg * Math.PI / 180.0);
	}
	 
	/**
	 * <p>This function converts radians to decimal degrees.</p>
	 * 
	 * @param rad - the radian to convert
	 * @return the radian converted to decimal degrees
	 */
	private  final static double rad2deg(double rad)
	{
	    return (rad * 180 / Math.PI);
	}
}
