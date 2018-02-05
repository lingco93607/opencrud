package com.z4.zhazha.forum.dao.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.z4.zhazha.forum.dao.impl.DaoSupport;
import com.z4.zhazha.forum.pojo.Review;

@Repository
@Qualifier("reviewjpa")
public class ReviewDaoJpaImpl extends DaoSupport<Review>{
	
	

	public ReviewDaoJpaImpl() {
		super();
		// TODO Auto-generated constructor stub
	}


	

}
