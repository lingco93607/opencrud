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
//@Autocode
public class ZHistory {

	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="history_seq")
	@SequenceGenerator(name="history_seq",sequenceName="seq_history",allocationSize=1)
	private int zUserId;
	private double zLat;
	private double zLon;
	private String zLocation;
	private Date zTime;
	public int getzUserId() {
		return zUserId;
	}
	public void setzUserId(int zUserId) {
		this.zUserId = zUserId;
	}
	public double getzLat() {
		return zLat;
	}
	public void setzLat(double zLat) {
		this.zLat = zLat;
	}
	public double getzLon() {
		return zLon;
	}
	public void setzLon(double zLon) {
		this.zLon = zLon;
	}
	public String getzLocation() {
		return zLocation;
	}
	public void setzLocation(String zLocation) {
		this.zLocation = zLocation;
	}
	public Date getzTime() {
		return zTime;
	}
	public void setzTime(Date zTime) {
		this.zTime = zTime;
	}
	
}
