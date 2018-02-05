package com.z4.zhazha.forum.dao.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.z4.zhazha.forum.pojo.RelationUserArticle;

@Repository
@Qualifier("relationuajpa")
public class RelationUADaoJpaImpl extends DaoSupport<RelationUserArticle> {
	public RelationUADaoJpaImpl() {
		super();
	}
}
