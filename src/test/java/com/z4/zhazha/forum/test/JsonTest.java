package com.z4.zhazha.forum.test;

import java.util.List;

import org.junit.Test;

import com.z4.zhazha.forum.pojo.AMessage;

public class JsonTest {
	@Test
	public void test() {
	/*	String listUsers = userClient.listUsers();
        JSONArray jArray= JSONArray.fromObject(listUsers);
        Collection collection = JSONArray.toCollection(jArray, User.class);
        List<User> userList = new ArrayList<User>();
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            User user = (User) it.next();
            userList.add(user);
        }*/
		
		String json = "{\"message\":\"success\",\"aMsgs\":[{\"writerPortrait\":null,\"writerName\":null,\"content\":null,\"time\":"
				+ "\"2017-11-09 15:18:37\",\"msgImage\":null,\"msgText\":null,\"msgType\":0},{\"writerPortrait\":null,\"writerName\":null,"
				+ "\"content\":\"haha\",\"time\":\"2017-10-30 15:18:37\",\"msgImage\":null,\"msgText\":null,\"msgType\":0}],\"status\":\"1\"}";
		String amsgs = "[{\"writerPortrait\":null,\"writerName\":null,\"content\":null,\"time\":"
				+ "\"2017-11-09 15:18:37\",\"msgImage\":null,\"msgText\":null,\"msgType\":0},{\"writerPortrait\":null,\"writerName\":null,"
				+ "\"content\":\"haha\",\"time\":\"2017-10-30 15:18:37\",\"msgImage\":null,\"msgText\":null,\"msgType\":0}]";
		net.sf.json.JSONArray jArray= net.sf.json.JSONArray.fromObject(amsgs);
		List<AMessage>  list= (List<AMessage>) net.sf.json.JSONArray.toCollection(jArray,AMessage.class);
		System.out.println(list);
	}

}
