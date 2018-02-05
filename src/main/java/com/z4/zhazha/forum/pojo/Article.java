package com.z4.zhazha.forum.pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * @author xintt
 * @date   2017.09.28
 * 
 * 用于存储用户发布的帖子
 */
@Entity
public class Article {
	//文章ID
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="article_seq")
	@SequenceGenerator(name="article_seq",sequenceName="seq_article",allocationSize=1)
	private int articleID;  
	
	//作者ID
	private int authorID; 
	
	//文章类型，0代表短文，1代表长文；
	private int type;
	
	//文章状态，0表示被管理员删除，1-5标示热度，热度依次增大，与阅读数和点赞数有关
	private int status;
	
	//文章标题
	private String title;
	
	//发布日期
	private Date publishDate;
	
	//文章正文内容，html标签
	@Basic(fetch=FetchType.LAZY)
	@Column(columnDefinition="TEXT")
	private String text;
	
	//阅读过该文章的人数
	private int readNum;
	
	//该文章获得的点赞数
	private int voteNum;
	
	//封面图片链接
	private String firstImgUrl;
	
	//该文章的评论
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER, mappedBy="article")
	@OrderBy("reviewDate")
	//@JsonManagedReference
	private Set<Review> reviews = new HashSet<Review>();
	
	public Article() {
		super();
	}
	
	public Article(int articleID, int authorID) {
		this.articleID = articleID;
		this.authorID = authorID;
	}

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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getReadNum() {
		return readNum;
	}

	public void setReadNum(int readNum) {
		this.readNum = readNum;
	}

	public int getVoteNum() {
		return voteNum;
	}

	public void setVoteNum(int voteNum) {
		this.voteNum = voteNum;
	}

	public Set<Review> getReviews() {
		return reviews;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}
	
	public void addReview(Review review) {
		if (!this.reviews.contains(review)) {
			this.reviews.add(review);
			review.setArticle(this);
		}
	}
	
	public void deleteReview(Review review) {
		if (this.reviews.contains(review)) {
			review.setArticle(null);
			this.reviews.remove(review);
		}
	}

	public String getFirstImgUrl() {
		return firstImgUrl;
	}

	public void setFirstImgUrl(String firstImgUrl) {
		this.firstImgUrl = firstImgUrl;
	}

}
