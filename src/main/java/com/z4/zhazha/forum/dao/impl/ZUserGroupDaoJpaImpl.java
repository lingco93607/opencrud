package com.z4.zhazha.forum.dao.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.z4.zhazha.forum.dao.impl.DaoSupport;
import com.z4.zhazha.forum.pojo.ZUserGroup;

@Repository
@Qualifier("zusergroupjpa")
public class ZUserGroupDaoJpaImpl extends DaoSupport<ZUserGroup>{
	
	

	public ZUserGroupDaoJpaImpl() {
		super();
		// TODO Auto-generated constructor stub
	}


	

}
