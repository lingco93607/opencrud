package com.z4.zhazha.forum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.z4.zhazha.forum.service.impl.ServiceSupport;
import com.z4.zhazha.forum.dao.Dao;
import com.z4.zhazha.forum.pojo.Teacher;


@Service
@Qualifier("teacherservice")
public class TeacherServiceImpl extends ServiceSupport<Teacher> {
	
	@Autowired
	@Qualifier("teacherjpa")
	Dao<Teacher> dao;

	@Override
	public Dao<Teacher> getDao() {
		// TODO Auto-generated method stub
		return this.dao;
	}

	

}
