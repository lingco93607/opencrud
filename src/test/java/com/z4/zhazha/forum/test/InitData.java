package com.z4.zhazha.forum.test;

import java.util.Date;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.z4.zhazha.forum.dao.Dao;
import com.z4.zhazha.forum.pojo.Teacher;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@FixMethodOrder
public class InitData {
	@Autowired
	@Qualifier("teacherjpa")
	Dao<Teacher> d1;

	public void addTeacher(){
		Teacher r = new Teacher();
		r.setTid(22);
		r.setTname("iiiiii gou");
		r.setSex(0);
		r.setMajor("math");

		d1.save(r);
	}
	
	
	@Test
	public void test(){

		addTeacher();
	}

}
