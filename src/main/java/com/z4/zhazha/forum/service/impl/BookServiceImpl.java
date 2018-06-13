package com.z4.zhazha.forum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.z4.zhazha.forum.service.impl.ServiceSupport;
import com.z4.zhazha.forum.dao.Dao;
import com.z4.zhazha.forum.pojo.Book;


@Service
@Qualifier("bookservice")
public class BookServiceImpl extends ServiceSupport<Book> {
	
	@Autowired
	@Qualifier("bookjpa")
	Dao<Book> dao;

	@Override
	public Dao<Book> getDao() {
		// TODO Auto-generated method stub
		return this.dao;
	}

	

}
