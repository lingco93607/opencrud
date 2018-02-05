package com.z4.zhazha.forum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.z4.zhazha.forum.dao.Dao;
import com.z4.zhazha.forum.pojo.ZUserGroup;
import com.z4.zhazha.forum.service.impl.ServiceSupport;


@Service
@Qualifier("zusergroupservice")
public class ZUserGroupServiceImpl extends ServiceSupport<ZUserGroup> {
	
	@Autowired
	@Qualifier("zusergroupjpa")
	Dao<ZUserGroup> dao;

	@Override
	public Dao<ZUserGroup> getDao() {
		// TODO Auto-generated method stub
		return this.dao;
	}

	

}
