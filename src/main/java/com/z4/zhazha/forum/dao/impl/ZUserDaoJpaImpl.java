package com.z4.zhazha.forum.dao.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.z4.zhazha.forum.dao.impl.DaoSupport;
import com.z4.zhazha.forum.pojo.ZUser;

@Repository
@Qualifier("zuserjpa")
public class ZUserDaoJpaImpl extends DaoSupport<ZUser>{
	
	

	public ZUserDaoJpaImpl() {
		super();
		// TODO Auto-generated constructor stub
	}


	

}
