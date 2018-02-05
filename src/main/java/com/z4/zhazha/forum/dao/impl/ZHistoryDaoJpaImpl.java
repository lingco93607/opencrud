package com.z4.zhazha.forum.dao.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.z4.zhazha.forum.dao.impl.DaoSupport;
import com.z4.zhazha.forum.pojo.ZHistory;

@Repository
@Qualifier("zhistoryjpa")
public class ZHistoryDaoJpaImpl extends DaoSupport<ZHistory>{
	
	

	public ZHistoryDaoJpaImpl() {
		super();
		// TODO Auto-generated constructor stub
	}


	

}
