package com.z4.zhazha.forum.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.z4.zhazha.forum.annotation.Autocode;

@Entity
//@Autocode
public class Teacher {
	@Id
	private long tid;
	
	public void setTid(long tid) {
		this.tid = tid;
	}

	private String tname;
	
	private int sex;
	
	private String major;



	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}
	
	
}
