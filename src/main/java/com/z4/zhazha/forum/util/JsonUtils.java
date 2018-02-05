package com.z4.zhazha.forum.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.z4.zhazha.forum.pojo.ZUser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by ${符柱成} on 2017/3/31.
 */
public class JsonUtils {
	public static JSONArray setStringToJArray(Set<String> strs) {
		JSONArray array = new JSONArray(strs.size());
		Iterator itr = strs.iterator();
		while (itr.hasNext()) {
			System.out.println(itr.next().toString());
			array.add(itr.next().toString());
		}
		return array;
	}
	
	public static JSONArray listStringToJArray(List<String> strs) {
		JSONArray array = new JSONArray(strs.size());
		for (String str : strs) {
			array.add(str);
		}
		return array;
	}
	
	public static String objectToJson(Object data) {
		String json = JSONArray.toJSONString(data);
		return json;
	}

	public static ZUser jsonObjectToUser(String userString) {
		ZUser user = JSONObject.parseObject(userString.toString(), ZUser.class);
		return user;
	}

	public static Object jsonToObject(String userString, Object obj) {
		Object t = JSONObject.parseObject(userString.toString(), obj.getClass());
		return t;
	}

	public static List<ZUser> objectToUser(JSONArray array) {
		List<ZUser> userList = new ArrayList<>();
		for (int i = 0; i < array.size(); i++) {
			System.out.print("array.get(i)    " + array.get(i).toString());
			// parseObject解析的是一个字符串，强转成为String是不行的，必须打印成String语句
			ZUser user = JSONObject.parseObject(array.get(i).toString(), ZUser.class);
			userList.add(user);
		}
		return userList;
	}
	
	public static <T> List<T> arrayToListObj(JSONArray array, Class<T> clz) {
		List<T> objs = new ArrayList<>();
		for (int i=0; i<array.size(); i++) {
			System.out.println("array.get(i) " + array.get(i).toString());
			T obj = JSONObject.parseObject((String) array.get(i), clz);
			objs.add(obj);
		}
		return objs;
	}

	public static JSONArray objectsToJson(Object[] objs) {
		JSONArray array = new JSONArray();
		if (null != objs) {
			for (Object obj : objs) {
				String json = JSONArray.toJSONString(obj);
				array.add(json);
			}
		}
		return array;
	}
}
