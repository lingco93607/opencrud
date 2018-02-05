package com.z4.zhazha.forum.pojo;

import java.util.Date;

/**
 * android端的消息类
 * @author xintt，zhangwc
 * @date 2017.11.06
 */
public class AMessage {
	private int id;
	private String writerPortrait;
	private String writerName;
	private String content;
	private String time;
	private String msgImage;
	private String msgText;
	private int msgType;
	
	public String getWriterPortrait() {
		return writerPortrait;
	}
	public void setWriterPortrait(String writerPortrait) {
		this.writerPortrait = writerPortrait;
	}
	public String getWriterName() {
		return writerName;
	}
	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getMsgImage() {
		return msgImage;
	}
	public void setMsgImage(String msgImage) {
		this.msgImage = msgImage;
	}
	public String getMsgText() {
		return msgText;
	}
	public void setMsgText(String msgText) {
		this.msgText = msgText;
	}
	public int getMsgType() {
		return msgType;
	}
	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
