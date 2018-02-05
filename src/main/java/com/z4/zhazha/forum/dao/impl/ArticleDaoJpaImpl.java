package com.z4.zhazha.forum.dao.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.z4.zhazha.forum.pojo.Article;

@Repository
@Qualifier("articlejpa")
public class ArticleDaoJpaImpl extends DaoSupport<Article> {

	public ArticleDaoJpaImpl(){
		super();
	}
}
