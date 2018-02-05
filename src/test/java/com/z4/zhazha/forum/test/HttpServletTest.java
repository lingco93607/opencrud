package com.z4.zhazha.forum.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class HttpServletTest {
	CloseableHttpClient httpclient = HttpClients.createDefault();
	@Test
	public void test(){
		//String uriAPI = "http://106.15.193.89:8080/zhazha/action/zHero/getHeroPropertyValue?heroID=1&property=money";//Post方式没有参数在这里  
		String uriAPI = "http://localhost:8080/zhazha/action/zHero/getHeroPropertyValue";//Post方式没有参数在这里  
		//String uriAPI = "http://106.15.193.89:8080/zhazha/action/zHero/getHeroPropertyValue";//Post方式没有参数在这里  
	    String result = "";  
	    HttpPost httpRequst = new HttpPost(uriAPI);//创建HttpPost对象  
	    
	      
	    List<NameValuePair> params=new ArrayList<NameValuePair>();
	    params.add(new BasicNameValuePair("heroID", "1")); 
	    params.add(new BasicNameValuePair("property", "money"));
	      
	    try {  
	        httpRequst.setEntity(new UrlEncodedFormEntity(params,HTTP.UTF_8));  
	        HttpResponse httpResponse = httpclient.execute(httpRequst);  
	        int statusCode = httpResponse.getStatusLine().getStatusCode();
	        if(httpResponse.getStatusLine().getStatusCode() == 200)  
	        {  
	            HttpEntity httpEntity = httpResponse.getEntity();  
	            result = EntityUtils.toString(httpEntity);//取出应答字符串  
	            System.out.println(result);
	        }  
	    } catch (UnsupportedEncodingException e1) {  
	        // TODO Auto-generated catch block  
	        e1.printStackTrace();  
	        result = e1.getMessage().toString();  
	    }  
	    catch (ClientProtocolException e2) {  
	        // TODO Auto-generated catch block  
	        e2.printStackTrace();  
	        result = e2.getMessage().toString();  
	    }  
	    catch (IOException e3) {  
	        // TODO Auto-generated catch block  
	        e3.printStackTrace();  
	        result = e3.getMessage().toString();  
	    } 
	    finally {
			//关闭连接，释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	    //return result;  
	}
	

}
