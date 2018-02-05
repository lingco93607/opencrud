package com.z4.zhazha.forum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.z4.zhazha.forum.dao.Dao;
import com.z4.zhazha.forum.pojo.ZHistory;
import com.z4.zhazha.forum.service.impl.ServiceSupport;


@Service
@Qualifier("zhistoryservice")
public class ZHistoryServiceImpl extends ServiceSupport<ZHistory> {
	
	@Autowired
	@Qualifier("zhistoryjpa")
	Dao<ZHistory> dao;

	@Override
	public Dao<ZHistory> getDao() {
		// TODO Auto-generated method stub
		return this.dao;
	}

	

}
