package com.z4.zhazha.forum.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class RegexTest {

	public static boolean isPhoneLegal(String str) {
		String regExp = "^((13[0-9])|(15[^4])|(18[2,3,5-9])|(17[1-8])|(147))\\d{8}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(str);
		return m.matches();
	}
	
	//@Test
	public void testPhoneLegal() {
		String phone = "18811465537";
		boolean isLegal = isPhoneLegal(phone);
		System.out.println(isLegal);
	}
	
	@Test
	public void testEmptyString() {
		String msg = "";
		String suc = "success";
		boolean empty = suc.equals(msg);
		System.out.println(empty);
	}
}
