package com.z4.zhazha.forum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.z4.zhazha.forum.dao.Dao;
import com.z4.zhazha.forum.pojo.Question;
import com.z4.zhazha.forum.service.impl.ServiceSupport;


@Service
@Qualifier("questionservice")
public class QuestionServiceImpl extends ServiceSupport<Question> {
	
	@Autowired
	@Qualifier("questionjpa")
	Dao<Question> dao;

	@Override
	public Dao<Question> getDao() {
		// TODO Auto-generated method stub
		return this.dao;
	}

	

}
