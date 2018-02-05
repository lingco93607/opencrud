package com.z4.zhazha.forum.pojo;

import java.util.Date;


public class HotArticleInfo {

	private int articleID;  
	
	//作者ID
	private int authorID; 
	
	//文章标题
	private String title;
		
	//文章类型，0代表短文，1代表长文；
	private int type;
	
	//发布日期
	private Date publishDate;
	
	//文章状态，0表示被管理员删除，1-5标示热度，热度依次增大，与阅读数和点赞数有关
	private int status;
	
	//发帖人昵称
	String zNickname;

	public int getArticleID() {
		return articleID;
	}

	public void setArticleID(int articleID) {
		this.articleID = articleID;
	}

	public int getAuthorID() {
		return authorID;
	}

	public void setAuthorID(int authorID) {
		this.authorID = authorID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getzNickname() {
		return zNickname;
	}

	public void setzNickname(String zNickname) {
		this.zNickname = zNickname;
	}
	
	
	
	
}
