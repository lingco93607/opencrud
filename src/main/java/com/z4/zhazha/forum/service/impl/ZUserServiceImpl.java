package com.z4.zhazha.forum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.z4.zhazha.forum.dao.Dao;
import com.z4.zhazha.forum.pojo.ZUser;
import com.z4.zhazha.forum.service.impl.ServiceSupport;


@Service
@Qualifier("zuserservice")
public class ZUserServiceImpl extends ServiceSupport<ZUser> {
	
	@Autowired
	@Qualifier("zuserjpa")
	Dao<ZUser> dao;

	@Override
	public Dao<ZUser> getDao() {
		// TODO Auto-generated method stub
		return this.dao;
	}

	

}
