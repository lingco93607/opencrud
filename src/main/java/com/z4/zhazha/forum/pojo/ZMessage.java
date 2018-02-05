package com.z4.zhazha.forum.pojo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 * 后台使用的论坛新消息类，包括新评论、赞和@我的
 * @author xintt
 * @date 2017.11.06
 */
@Entity
public class ZMessage {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "zmessage_seq")
	@SequenceGenerator(name="zmessage_seq", sequenceName="seq_zmessage", allocationSize=1)
	private int id;
	private int userID;
	private int writerID;
	private int targetID;
	private int targetType;
	private int type;
	private String content;
	private boolean isRead;
	private Date postTime;
	
	public ZMessage() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getWriterID() {
		return writerID;
	}

	public void setWriterID(int writerID) {
		this.writerID = writerID;
	}

	public int getTargetID() {
		return targetID;
	}

	public void setTargetID(int targetID) {
		this.targetID = targetID;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isRead() {
		return isRead;
	}

	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

	public Date getPostTime() {
		return postTime;
	}

	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}

	public int getTargetType() {
		return targetType;
	}

	public void setTargetType(int targetType) {
		this.targetType = targetType;
	}

}