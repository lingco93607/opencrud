package com.z4.zhazha.forum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.z4.zhazha.forum.dao.Dao;
import com.z4.zhazha.forum.pojo.ZRelation;
import com.z4.zhazha.forum.service.impl.ServiceSupport;


@Service
@Qualifier("zrelationservice")
public class ZRelationServiceImpl extends ServiceSupport<ZRelation> {
	
	@Autowired
	@Qualifier("zrelationjpa")
	Dao<ZRelation> dao;

	@Override
	public Dao<ZRelation> getDao() {
		// TODO Auto-generated method stub
		return this.dao;
	}

	

}
