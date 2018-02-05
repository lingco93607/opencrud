package com.z4.zhazha.forum.dao.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.z4.zhazha.forum.dao.impl.DaoSupport;
import com.z4.zhazha.forum.pojo.ZGroup;

@Repository
@Qualifier("zgroupjpa")
public class ZGroupDaoJpaImpl extends DaoSupport<ZGroup>{
	
	

	public ZGroupDaoJpaImpl() {
		super();
		// TODO Auto-generated constructor stub
	}


	

}
