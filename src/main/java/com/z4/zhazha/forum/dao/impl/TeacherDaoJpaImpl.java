package com.z4.zhazha.forum.dao.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import com.z4.zhazha.forum.dao.impl.DaoSupport;
import com.z4.zhazha.forum.pojo.Teacher;

@Repository
@Qualifier("teacherjpa")
public class TeacherDaoJpaImpl extends DaoSupport<Teacher>{
	
	

	public TeacherDaoJpaImpl() {
		super();
		// TODO Auto-generated constructor stub
	}


	

}
