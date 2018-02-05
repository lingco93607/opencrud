package com.z4.zhazha.forum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.z4.zhazha.forum.dao.Dao;
import com.z4.zhazha.forum.pojo.ZMessage;

@Service
@Qualifier("zmessageservice")
public class ZMessageServiceImpl extends ServiceSupport<ZMessage> {
	@Autowired
	@Qualifier("zmessagejpa")
	Dao<ZMessage> zmessagedao;

	@Override
	public Dao<ZMessage> getDao() {
		// TODO Auto-generated method stub
		return this.zmessagedao;
	}

}
