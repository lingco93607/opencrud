package com.z4.zhazha.forum.dao.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.z4.zhazha.forum.dao.impl.DaoSupport;
import com.z4.zhazha.forum.pojo.Question;

@Repository
@Qualifier("questionjpa")
public class QuestionDaoJpaImpl extends DaoSupport<Question>{
	
	

	public QuestionDaoJpaImpl() {
		super();
		// TODO Auto-generated constructor stub
	}


	

}
