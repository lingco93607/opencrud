package com.z4.zhazha.forum.pojo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.xml.bind.annotation.XmlRootElement;

import com.z4.zhazha.forum.annotation.Autocode;
@Entity
@XmlRootElement
@Autocode
public class ZGroup {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="group_seq")
	@SequenceGenerator(name="group_seq",sequenceName="seq_group",allocationSize=1)	
	private int zGroupId;
	
	private int zGroupName;
	
	private int zGroupLevel;
	
	private int zGroupParent;
	
	private Date zCreateTime;

	public int getzGroupId() {
		return zGroupId;
	}

	public void setzGroupId(int zGroupId) {
		this.zGroupId = zGroupId;
	}

	public int getzGroupName() {
		return zGroupName;
	}

	public void setzGroupName(int zGroupName) {
		this.zGroupName = zGroupName;
	}

	public int getzGroupLevel() {
		return zGroupLevel;
	}

	public void setzGroupLevel(int zGroupLevel) {
		this.zGroupLevel = zGroupLevel;
	}

	public int getzGroupParent() {
		return zGroupParent;
	}

	public void setzGroupParent(int zGroupParent) {
		this.zGroupParent = zGroupParent;
	}
	
	

}
