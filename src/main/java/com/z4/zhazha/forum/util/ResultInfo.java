package com.z4.zhazha.forum.util;

import java.util.HashMap;
import java.util.Map;
/**
 * 结果集返回bean
 *
 */
public class ResultInfo {

	protected boolean status = false;
	protected String message = null;
	protected Map<String,Object> data = new HashMap<String,Object>();

	public ResultInfo() {
		this.status = false;
	}
	
	public ResultInfo(boolean success) {
		this.status = success;
	}
	
	
	public ResultInfo(String key,Object object) {
		this.status = true;
		this.data.put(key, object);
	}
	
	public ResultInfo(boolean success, String message) {
		this.status = success;
		this.message = message;
	}
	public ResultInfo(boolean success, String message, Map<String,Object> data) {
		this.status = success;
		this.message = message;
		this.data = data;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}
	public boolean isStatus() {
		return status;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	public void setData(String key, Object data) {
		this.data.put(key, data);
	}
	public void addData(String key, Object data) {
		this.data.put(key, data);
	}

}