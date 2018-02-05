package com.z4.zhazha.forum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.z4.zhazha.forum.dao.Dao;
import com.z4.zhazha.forum.pojo.Review;

@Service
@Qualifier("reviewservice")
public class ReviewService extends ServiceSupport<Review> {
	@Autowired
	@Qualifier("reviewjpa")
	Dao<Review> daoreview;

	@Override
	public Dao<Review> getDao() {
		// TODO Auto-generated method stub
		return this.daoreview;
	}

}
