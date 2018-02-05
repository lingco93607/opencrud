package com.z4.zhazha.forum.pojo;

import java.util.Date;
import java.util.List;

import com.z4.zhazha.forum.annotation.Autocode;

@Autocode
public class Question {
	// 问题ID
	private int questionID;

	// 作者ID
	private int authorID;

	// 问题状态，0表示被管理员删除，-1表示尚未有人回答，1-5标示热度，热度依次增大，与阅读数和回答数有关
	private int status;

	// 发布日期
	private Date publishDate;

	// 问题内容
	private String text;

	// 阅读过该问题的人数
	private int readNum;

	//该问题的答案列表
	private List<String> answers;

	public int getQuestionID() {
		return questionID;
	}

	public void setQuestionID(int questionID) {
		this.questionID = questionID;
	}

	public int getAuthorID() {
		return authorID;
	}

	public void setAuthorID(int authorID) {
		this.authorID = authorID;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public List<String> getAnswers() {
		return answers;
	}

	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}

}
