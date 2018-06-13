package com.z4.zhazha.forum.dao.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import com.z4.zhazha.forum.dao.impl.DaoSupport;
import com.z4.zhazha.forum.pojo.Book;

@Repository
@Qualifier("bookjpa")
public class BookDaoJpaImpl extends DaoSupport<Book>{
	
	

	public BookDaoJpaImpl() {
		super();
		// TODO Auto-generated constructor stub
	}


	

}
