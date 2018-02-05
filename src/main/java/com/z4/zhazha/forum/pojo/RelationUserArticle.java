package com.z4.zhazha.forum.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
/**
 * 
 * @author xintt
 * @date 2017.10.30
 * 存储用户与帖子间的关系，
 * 是否已阅读，是否已点赞
 */
@Entity
public class RelationUserArticle {
	/**
	 * userID和articleID的联合主键类
	 */
	UserArticleUnionID uaID = new UserArticleUnionID();
	private boolean isRead;
	private boolean isVote;
	
	@Id
	public UserArticleUnionID getUaID() {
		return uaID;
	}
	public void setUaID(UserArticleUnionID uaID) {
		this.uaID = uaID;
	}
	public boolean isRead() {
		return isRead;
	}
	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}
	public boolean isVote() {
		return isVote;
	}
	public void setVote(boolean isVote) {
		this.isVote = isVote;
	}

}
