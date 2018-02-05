package com.z4.zhazha.forum.pojo;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author xintt
 * @date   2017.09.28
 * 
 * 帖子的每一条评论
 */

@Entity
public class Review {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "review_seq")
	@SequenceGenerator(name="review_seq", sequenceName="seq_review", allocationSize=1)
	private int reviewID;
	private String text;
	private Date reviewDate;
	private int authorID;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="articleID")
	//@JsonBackReference
	@JsonIgnore
	private Article article;
	
	@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER, mappedBy="review")
	@OrderBy("time")
	private List<CReview> creviews;
	
	public int getReviewID() {
		return reviewID;
	}
	public void setReviewID(int reviewID) {
		this.reviewID = reviewID;
	}
	public Date getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}
	public int getAuthorID() {
		return authorID;
	}
	public void setAuthorID(int authorID) {
		this.authorID = authorID;
	}
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<CReview> getCreviews() {
		return creviews;
	}
	public void setCreviews(List<CReview> creviews) {
		this.creviews = creviews;
	}
	
	public void addCReview(CReview creview) {
		if (!this.creviews.contains(creview)) {
			this.creviews.add(creview);
			creview.setReview(this);
		}
	}
	
	public void deleteCReview(CReview creview) {
		if (this.creviews.contains(creview)) {
			this.creviews.remove(creview);
			creview.setReview(null);
		}
	}
	
	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Review)) {
			return false;
		}
		if (object == this) {
			return true;
		}
		Review review = (Review) object;
		boolean condition1 = review.article.equals(article);
		boolean condition2 = (review.authorID == authorID);
		boolean condition3 = review.creviews.equals(creviews);
		boolean condition4 = (review.reviewDate.equals(reviewDate));
		boolean condition5 = (review.reviewID == reviewID);
		boolean condition6 = review.text.equals(text);
		if (condition1 && condition2 && condition3 && condition4 && condition5 && condition6) {
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(reviewID,text,reviewDate,authorID,article,creviews);
	}

}
