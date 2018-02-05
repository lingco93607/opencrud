package com.z4.zhazha.forum.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.xml.bind.annotation.XmlRootElement;

import com.z4.zhazha.forum.annotation.Autocode;

@Entity
@XmlRootElement
//@Autocode
public class ZUserGroup {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="usergroup_seq")
	@SequenceGenerator(name="usergroup_seq",sequenceName="seq_usergroup",allocationSize=1)
	private int zUserId;
	private int zGroupId;
	
	private int isAdministrator;
	private int isCreator;
	public int getzUserId() {
		return zUserId;
	}
	public void setzUserId(int zUserId) {
		this.zUserId = zUserId;
	}
	public int getzGroupId() {
		return zGroupId;
	}
	public void setzGroupId(int zGroupId) {
		this.zGroupId = zGroupId;
	}
	public int getIsAdministrator() {
		return isAdministrator;
	}
	public void setIsAdministrator(int isAdministrator) {
		this.isAdministrator = isAdministrator;
	}
	public int getIsCreator() {
		return isCreator;
	}
	public void setIsCreator(int isCreator) {
		this.isCreator = isCreator;
	}
	
	

}
