package com.z4.zhazha.forum.pojo;

import java.io.Serializable;

import javax.persistence.Embeddable;
/**
 * 
 * @author xintt
 * @date 2017.10.30
 * RelationUserArticle类的联合主键类
 *
 */
@Embeddable
public class UserArticleUnionID implements Serializable{
	
	private int userID;
	private int articleID;
	
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getArticleID() {
		return articleID;
	}
	public void setArticleID(int articleID) {
		this.articleID = articleID;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof UserArticleUnionID) {
			UserArticleUnionID ua = (UserArticleUnionID) obj;
			if ((this.articleID == ua.articleID) && (this.userID == ua.userID)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}

}
