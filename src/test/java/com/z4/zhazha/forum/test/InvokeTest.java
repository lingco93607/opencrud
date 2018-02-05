package com.z4.zhazha.forum.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class InvokeTest {
	public static class StudentDemo {
		private int id;  
	    private String name;  
	    private String sex;  
	    private String age;  
	    private String brithday;  
	    private String address;  
	  
	    public StudentDemo() {  
	    }  
	  
	    public int getId() {  
	        return id;  
	    }  
	  
	    public void setId(int id) {  
	        this.id = id;  
	    }  
	  
	    public String getName() {  
	        return name;  
	    }  
	  
	    public void setName(String name) {  
	        this.name = name;  
	    }  
	  
	    public String getSex() {  
	        return sex;  
	    }  
	  
	    public void setSex(String sex) {  
	        this.sex = sex;  
	    }  
	  
	    public String getAge() {  
	        return age;  
	    }  
	  
	    public void setAge(String age) {  
	        this.age = age;  
	    }  
	  
	    public String getBrithday() {  
	        return brithday;  
	    }  
	  
	    public void setBrithday(String brithday) {  
	        this.brithday = brithday;  
	    }  
	  
	    public String getAddress() {  
	        return address;  
	    }  
	  
	    public void setAddress(String address) {  
	        this.address = address;  
	    }  
	}
	
	public static void main(String[] args) {
		StudentDemo sd = new StudentDemo();
		sd.setId(1);
		sd.setName("test invoke");
		sd.setAge("15");
		sd.setAddress("aa");
		sd.setBrithday("1990");
		
		for (Method m : sd.getClass().getMethods()) {
			if (m.getName().startsWith("get")) {
				System.out.println("param:" + m.getName().substring(3));
				try {
					System.out.println(m.invoke(sd, args));
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
