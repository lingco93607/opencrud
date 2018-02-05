package com.z4.zhazha.forum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.z4.zhazha.forum.dao.Dao;
import com.z4.zhazha.forum.pojo.Article;

@Service
@Qualifier("articleservice")
public class ArticleServiceImpl extends ServiceSupport<Article> {

	@Autowired
	@Qualifier("articlejpa")
	Dao<Article> daoarticle;
	
	@Override
	public Dao<Article> getDao() {
		// TODO Auto-generated method stub
		return this.daoarticle;
	}

}

