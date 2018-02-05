package com.z4.zhazha.forum.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.SequenceGenerator;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
//@Autocode
public class ZUser {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="user_seq")
	@SequenceGenerator(name="user_seq",sequenceName="seq_user",allocationSize=1)
	private int zUserId;
	private String zUserName;
	private String token;
	private String portrait;

	public int getzUserId() {
		return zUserId;
	}

	public void setzUserId(int zUserId) {
		this.zUserId = zUserId;
	}

	public String getzUserName() {
		return zUserName;
	}

	public void setzUserName(String zUserName) {
		this.zUserName = zUserName;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPortrait() {
		return portrait;
	}

	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}
}
