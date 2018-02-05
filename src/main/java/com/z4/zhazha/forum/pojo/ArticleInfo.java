package com.z4.zhazha.forum.pojo;

public class ArticleInfo {
	int articleAuthorId;	
	String zNickname;
	String portrait;
	Article article;  
	boolean isVote;
	boolean isRead;

	public String getzNickname() {
		return zNickname;
	}

	public void setzNickname(String zNickname) {
		this.zNickname = zNickname;
	}
	
	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article1) {
		article = article1;
	}

	public int getArticleAuthorId() {
		return articleAuthorId;
	}

	public void setArticleAuthorId(int articleAuthorId) {
		this.articleAuthorId = articleAuthorId;
	}

	public String getPortrait() {
		return portrait;
	}

	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}

	public boolean isVote() {
		return isVote;
	}

	public void setVote(boolean isVote) {
		this.isVote = isVote;
	}

	public boolean isRead() {
		return isRead;
	}

	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}
}
