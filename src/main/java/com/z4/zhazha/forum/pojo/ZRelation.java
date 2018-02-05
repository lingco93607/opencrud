package com.z4.zhazha.forum.pojo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.z4.zhazha.forum.annotation.Autocode;
@Entity
@XmlRootElement
//@Autocode
@IdClass(FriendsId.class)

public class ZRelation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1860059070658798097L;

	@Id
	private int zAUserId;

	@Id
	private int zBUserId;

	private int zRelation;

	private int zStatus;
	private String zNote;

	private int zSentFrequent;
	private int zQuietStart;
	private int zQuietStop;
	private int zRequestTimes;


	public int getzAUserId() {
		return zAUserId;
	}

	public void setzAUserId(int zAUserId) {
		this.zAUserId = zAUserId;
	}

	public int getzBUserId() {
		return zBUserId;
	}

	public void setzBUserId(int zBUserId) {
		this.zBUserId = zBUserId;
	}

	public int getzRequestTimes() {
		return zRequestTimes;
	}

	public void setzRequestTimes(int zRequestTimes) {
		this.zRequestTimes = zRequestTimes;
	}

	public int getzRelation() {
		return zRelation;
	}

	public void setzRelation(int zRelation) {
		this.zRelation = zRelation;
	}

	public int getzStatus() {
		return zStatus;
	}

	public void setzStatus(int zStatus) {
		this.zStatus = zStatus;
	}

	public String getzNote() {
		return zNote;
	}

	public void setzNote(String zNote) {
		this.zNote = zNote;
	}

	public int getzSentFrequent() {
		return zSentFrequent;
	}

	public void setzSentFrequent(int zSentFrequent) {
		this.zSentFrequent = zSentFrequent;
	}

	public int getzQuietStart() {
		return zQuietStart;
	}

	public void setzQuietStart(int zQuietStart) {
		this.zQuietStart = zQuietStart;
	}

	public int getzQuietStop() {
		return zQuietStop;
	}

	public void setzQuietStop(int zQuietStop) {
		this.zQuietStop = zQuietStop;
	}

}
