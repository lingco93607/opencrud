package com.z4.zhazha.forum.pojo;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author xintt
 * @date 2017.10.26
 * 帖子评论的子评论类
 */

@Entity
public class CReview {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "creview_seq")
	@SequenceGenerator(name="creview_seq", sequenceName="seq_creview", allocationSize=1)
	private int id;
	private String text;
	private Date time;
	private int authorID;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="reviewID")
	@JsonIgnore
	private Review review;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Review getReview() {
		return review;
	}

	public void setReview(Review review) {
		this.review = review;
	}

	public int getAuthorID() {
		return authorID;
	}

	public void setAuthorID(int authorID) {
		this.authorID = authorID;
	}

}
