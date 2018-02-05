package com.z4.zhazha.forum.test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.junit.Test;

public class DecodeTest {
	@Test
	public void test() {
		int userID = 1;
		String property = "money";
		String url = "http://106.15.193.89:8080/zhazha/action/zHero/getHeroPropertyValue?";
		try {
			url += "heroID=" + URLEncoder.encode(String.valueOf(userID), "utf-8");
			url += "&property=" + URLEncoder.encode(property, "utf-8");
			System.out.print(url);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	

}
