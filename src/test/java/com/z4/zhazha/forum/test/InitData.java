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
import com.z4.zhazha.forum.pojo.ZRelation;
import com.z4.zhazha.forum.pojo.ZUser;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@FixMethodOrder
public class InitData {
	@Autowired
	@Qualifier("zuserjpa")
	Dao<ZUser> dUser;
	
	@Autowired
	@Qualifier("zrelationjpa")
	Dao<ZRelation> dRelation;

	
	public int addHero(String name, String statement) {
		
		ZUser a = new ZUser();
		a.setzUserName(name);
		a.setzSex(1);
		a.setzActiveType(0);
		a.setzCreateTime(new Date());
		a.setzLastActiveTime(new Date());
		a.setzLastLat(110.54);
		a.setzLastLon(40.25);
		a.setzLastLocation("北京");
		a.setzNickname("lingco");
		a.setzPicPath("");
		a.setzStatement(statement);		
		dUser.save(a);
		System.out.println(a.getzUserId());
		return a.getzUserId();
	}
	
	public void addRelation(int auserid, int buserid){
		ZRelation r = new ZRelation();
		r.setzAUserId(auserid);
		r.setzBUserId(buserid);
		r.setzNote("同事");
		r.setzRelation(0);
		r.setzStatus(0);
		r.setzRequestTimes(10);
		dRelation.save(r);
	}
	
	
	@Test
	public void test(){
		int usera = addHero("xintt","公欲大便兮厕所无纸");
		int userb = addHero("lingco","红烧肉兮烧茄子");
		addRelation(usera, userb);
	}
	
	

}
