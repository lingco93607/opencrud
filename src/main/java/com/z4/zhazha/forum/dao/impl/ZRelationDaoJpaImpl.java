package com.z4.zhazha.forum.dao.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.z4.zhazha.forum.dao.impl.DaoSupport;
import com.z4.zhazha.forum.pojo.ZRelation;

@Repository
@Qualifier("zrelationjpa")
public class ZRelationDaoJpaImpl extends DaoSupport<ZRelation>{
	
	

	public ZRelationDaoJpaImpl() {
		super();
		// TODO Auto-generated constructor stub
	}


	

}
