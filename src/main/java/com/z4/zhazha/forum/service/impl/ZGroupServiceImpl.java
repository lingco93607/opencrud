package com.z4.zhazha.forum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.z4.zhazha.forum.dao.Dao;
import com.z4.zhazha.forum.pojo.ZGroup;
import com.z4.zhazha.forum.service.impl.ServiceSupport;


@Service
@Qualifier("zgroupservice")
public class ZGroupServiceImpl extends ServiceSupport<ZGroup> {
	
	@Autowired
	@Qualifier("zgroupjpa")
	Dao<ZGroup> dao;

	@Override
	public Dao<ZGroup> getDao() {
		// TODO Auto-generated method stub
		return this.dao;
	}

	

}
