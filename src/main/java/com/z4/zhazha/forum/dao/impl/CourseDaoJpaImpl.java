package com.z4.zhazha.forum.dao.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import com.z4.zhazha.forum.dao.impl.DaoSupport;
import com.z4.zhazha.forum.pojo.Course;

@Repository
@Qualifier("coursejpa")
public class CourseDaoJpaImpl extends DaoSupport<Course>{
	
	

	public CourseDaoJpaImpl() {
		super();
		// TODO Auto-generated constructor stub
	}


	

}
