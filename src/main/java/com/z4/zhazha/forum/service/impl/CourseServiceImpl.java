package com.z4.zhazha.forum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.z4.zhazha.forum.service.impl.ServiceSupport;
import com.z4.zhazha.forum.dao.Dao;
import com.z4.zhazha.forum.pojo.Course;


@Service
@Qualifier("courseservice")
public class CourseServiceImpl extends ServiceSupport<Course> {
	
	@Autowired
	@Qualifier("coursejpa")
	Dao<Course> dao;

	@Override
	public Dao<Course> getDao() {
		// TODO Auto-generated method stub
		return this.dao;
	}

	

}
