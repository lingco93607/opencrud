package com.z4.zhazha.forum.dao.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.z4.zhazha.forum.pojo.ZMessage;

@Repository
@Qualifier("zmessagejpa")
public class ZMessageDaoJpaImpl extends DaoSupport<ZMessage>{
	public ZMessageDaoJpaImpl() {
		super();
	}
	
}
