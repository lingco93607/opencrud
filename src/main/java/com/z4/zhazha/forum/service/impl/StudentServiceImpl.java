package com.z4.zhazha.forum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.z4.zhazha.forum.service.impl.ServiceSupport;
import com.z4.zhazha.forum.dao.Dao;
import com.z4.zhazha.forum.pojo.Student;


@Service
@Qualifier("studentservice")
public class StudentServiceImpl extends ServiceSupport<Student> {
	
	@Autowired
	@Qualifier("studentjpa")
	Dao<Student> dao;

	@Override
	public Dao<Student> getDao() {
		// TODO Auto-generated method stub
		return this.dao;
	}

	

}
