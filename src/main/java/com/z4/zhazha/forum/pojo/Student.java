package com.z4.zhazha.forum.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.z4.zhazha.forum.annotation.Autocode;


@Entity
@Autocode
public class Student {
	
	@Id
	private int sid;
	
	private String sname;
	
	private int sex;
	
	private String home;

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}
	
	
	
	

}
