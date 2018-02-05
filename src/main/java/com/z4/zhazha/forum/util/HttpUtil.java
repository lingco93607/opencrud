package com.z4.zhazha.forum.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.gson.JsonObject;


/**
 * 
 * @author xintt
 * @date 2017.10.18
 *
 */
@Component
public class HttpUtil {
			
	static int responseCodeSuccess;	
	static String urlMainPropertyChange;	
	static String urlMainGetPropertyValue;	
	static String urlMainGetUser;
	static CloseableHttpClient httpclient = HttpClients.createDefault();
	
	@Value("${responseCodeSuccess}")
	public void setresponseCodeSuccess(int codeSuccess) {
		responseCodeSuccess = codeSuccess;
	}
	
	@Value("${urlMainPropertyChange}")
	public void seturlMainPropertyChange(String mainPropertyChange) {
		urlMainPropertyChange = mainPropertyChange;
	}
	
	@Value("${urlMainGetPropertyValue}")
	public void seturlMainGetPropertyValue(String mainGetPropertyValue) {
		urlMainGetPropertyValue = mainGetPropertyValue;
	}
	
	@Value("${urlMainGetUser}")
	public void seturlMainGetUser(String mainGetUser) {
		urlMainGetUser = mainGetUser;
	}
	
	/**
	 * 向主后台发送请求，修改用户某个属性值
	 */
	public static String sendRequestMainPropertyChange(String authorID, String propertyName, String changeValue) {
		String url = urlMainPropertyChange;		
		HttpPost httppost=new HttpPost(url);
		
		String msg = "";
		HttpResponse response = null;
		List<NameValuePair> params=new ArrayList<NameValuePair>();
	    try {
			params.add(new BasicNameValuePair("heroID", URLEncoder.encode(String.valueOf(authorID), "utf-8")));
			params.add(new BasicNameValuePair("property", URLEncoder.encode(String.valueOf(propertyName), "utf-8")));
			params.add(new BasicNameValuePair("changeValue", URLEncoder.encode(String.valueOf(changeValue), "utf-8")));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		try {
			httppost.setEntity(new UrlEncodedFormEntity(params,HTTP.UTF_8));  
			response = new DefaultHttpClient().execute(httppost);
			StatusLine statusLine=response.getStatusLine();
			int responseCode=statusLine.getStatusCode();
			if(responseCode==responseCodeSuccess){
				HttpEntity entity=response.getEntity(); 
				msg =  EntityUtils.toString(entity, "utf-8");
			}
		}catch(IOException e) {
			e.printStackTrace();
		} finally {
			//关闭连接，释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return msg;
	}
	
	/**
	 * 向主后台发送请求，获取用户某个属性值
	 */
	public static String sendRequestMainGetPropertyValue(int userID, String propertyName) {
		String url = urlMainGetPropertyValue;
		HttpPost httppost=new HttpPost(url);
		List<NameValuePair> params=new ArrayList<NameValuePair>();
	    try {
			params.add(new BasicNameValuePair("heroID", URLEncoder.encode(String.valueOf(userID), "utf-8")));
			params.add(new BasicNameValuePair("property", URLEncoder.encode(String.valueOf(propertyName), "utf-8")));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
	    
		String msg = "";
		HttpResponse response = null;
		try {
			httppost.setEntity(new UrlEncodedFormEntity(params,HTTP.UTF_8));  
			response = new DefaultHttpClient().execute(httppost);
			StatusLine statusLine=response.getStatusLine();
			int responseCode=statusLine.getStatusCode();
			if(responseCode==responseCodeSuccess){
				HttpEntity entity=response.getEntity(); 
				msg =  EntityUtils.toString(entity, "utf-8");
			}
		}catch(IOException e) {
			e.printStackTrace();
		} finally {
			//关闭连接，释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return msg;
	}
	
	public static JsonObject sendRequestMainGetUser(int userID) {
		String msg = "";
		String url = urlMainGetPropertyValue;
		HttpPost httppost=new HttpPost(url);		
		HttpResponse response = null;
		List<NameValuePair> params=new ArrayList<NameValuePair>();
	    try {
			params.add(new BasicNameValuePair("heroID", URLEncoder.encode(String.valueOf(userID), "utf-8")));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} 
		try {
			httppost.setEntity(new UrlEncodedFormEntity(params,HTTP.UTF_8));  
			response = new DefaultHttpClient().execute(httppost);
			StatusLine statusLine=response.getStatusLine();
			int responseCode=statusLine.getStatusCode();
			if(responseCode==responseCodeSuccess){
				HttpEntity entity=response.getEntity(); 
				JsonObject json = new JsonObject();
				json = json.getAsJsonObject(EntityUtils.toString(entity, "utf-8"));
				return json;
			}
		}catch(IOException e) {
			e.printStackTrace();
		} finally {
			//关闭连接，释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public static boolean appSignValidate(Map<String, String> map) {
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		String appSign = map.get("app_sign");
		if (null== appSign) {
			return false;
		}
		
		Set set = map.entrySet();
		Iterator it = set.iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
			String key = entry.getKey();
			String value = entry.getValue();
			if (!"app_sign".equals(key) && !"token".equals(key)) {
				if (value != null && !value.equals("")) {
					packageParams.put(key, value);
				}	
			}					
		}
		
		String sign = StringUtil.createSign(packageParams);
		return (appSign.equals(sign)?true:false);
	}

}
