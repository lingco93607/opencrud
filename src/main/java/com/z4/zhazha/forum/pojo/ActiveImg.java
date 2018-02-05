package com.z4.zhazha.forum.pojo;

import java.util.Date;

//用于返回滚动图片
public class ActiveImg {
	//文章ID
	private int articleID;  
	
	//文章标题
	private String title;
	
	//文章类型，0代表短文，1代表长文；
	private int type;
	
	//封面图片链接
	private String firstImgUrl;
		
	//发布日期
	private Date publishDate;

	public int getArticleID() {
		return articleID;
	}

	public void setArticleID(int articleID) {
		this.articleID = articleID;
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

	public String getFirstImgUrl() {
		return firstImgUrl;
	}

	public void setFirstImgUrl(String firstImgUrl) {
		this.firstImgUrl = firstImgUrl;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	
	
	
}
