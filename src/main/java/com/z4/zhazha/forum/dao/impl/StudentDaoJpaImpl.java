package com.z4.zhazha.forum.dao.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import com.z4.zhazha.forum.dao.impl.DaoSupport;
import com.z4.zhazha.forum.pojo.Student;

@Repository
@Qualifier("studentjpa")
public class StudentDaoJpaImpl extends DaoSupport<Student>{
	
	

	public StudentDaoJpaImpl() {
		super();
		// TODO Auto-generated constructor stub
	}


	

}
