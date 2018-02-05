package com.z4.zhazha.forum.dao.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.z4.zhazha.forum.pojo.Token;

@Service
@Qualifier("tokenjpa")
public class TokenDaoJpaImpl extends DaoSupport<Token> {
	public TokenDaoJpaImpl() {
		super();
	}

}
