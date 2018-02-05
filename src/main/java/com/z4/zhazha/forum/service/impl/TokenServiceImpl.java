package com.z4.zhazha.forum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.z4.zhazha.forum.dao.Dao;
import com.z4.zhazha.forum.pojo.Token;

@Service
@Qualifier("tokenservice")
public class TokenServiceImpl extends ServiceSupport<Token> {
	@Autowired
	@Qualifier("tokenjpa")
	Dao<Token> daoToken;

	@Override
	public Dao<Token> getDao() {
		// TODO Auto-generated method stub
		return this.daoToken;
	}

}
