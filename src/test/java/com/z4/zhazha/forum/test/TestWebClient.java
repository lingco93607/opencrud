package com.z4.zhazha.forum.test;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;

public class TestWebClient {
//	public static void main(String[] args) {
//		testFight();
//
//
//	}

//	private static void testFight() {
//		Client client = Client.create();
//		WebResource webResource = client
//				.resource("http://localhost:8080/z4jl/rs/fight/solofightwithmonster?hero_id=50&monster_id=50");
//
//		ClientResponse	response = webResource.accept(MediaType.APPLICATION_XML).get(
//				ClientResponse.class);
//		BattleSingle bs = response.getEntity(new GenericType<BattleSingle>() {
//		});
//		
//		
//
//		if (response.getStatus() == 200) {
//			System.out.println(bs.getAttacker().getName()+"开始攻击"+bs.getDefender().getName());
//			System.out.println(bs.getAttacker().getName()+"的攻击力是"+bs.getAttacker().getBasicProperty().getAttack());
//			System.out.println(bs.getDefender().getName()+"的攻击力是"+bs.getDefender().getBasicProperty().getAttack());
//			System.out.println(bs.getAttacker().getName()+"的生命是"+bs.getAttacker().getHp());
//			System.out.println(bs.getDefender().getName()+"生命是"+bs.getDefender().getHp());
//			System.out.println("########################################################");
//
//			for (BattleStep s:bs.getBattleSteps())
//			{
//				System.out.println(s.getNr());
//				System.out.println(s.getAttackerName()+"对"+s.getDefenderName()+"进行了攻击，造成了"+s.getDamageOfAttacker()+"伤害");
//			}
//			System.out.println(bs.getErrMessage());
//				
//		} else {
//			System.out.println("GET failed");
//		}
//
//	}
	@Test
	public void testPost(){
		Client client = Client.create();
		// Define the URL for testing the example application
		WebResource webResource = client
				.resource("http://localhost:8080/jl/rest/hero/addhero");

		// Test the POST method
		Hero h = new Hero();
		h.setName("狗调皮");

		h.setAura(5000);
//		h.setLat((double) 10);
//		h.setLon((double) 20);
//		Skill s = new Skill();
//		s.setAction("sssss");
//		List<Skill> l = new ArrayList<Skill>();
//		l.add(s);

		ClientResponse response = webResource.type(MediaType.APPLICATION_XML)
				.post(ClientResponse.class, h);

		if (response.getStatus() == 200) {
			System.out.println("POST succeeded");
		} else {
			System.out.println("POST failed");
		}
		
	}
	
	@Test
	public void testGet() {
		Client client = Client.create();
		// Define the URL for testing the example application
		WebResource webResource = client
				.resource("http://localhost:8080/jl/rest/hero/getheros?startpos=00&endpos=20");

		// Test the GET method using content negotiation
		ClientResponse response = webResource.accept(MediaType.APPLICATION_XML).get(
				ClientResponse.class);
		List<Hero> entity = response.getEntity(new GenericType<List<Hero>>() {
		});

		if (response.getStatus() == 200) {
			System.out.println("get  succeeded " + entity.get(0).getName());
		} else {
			System.out.println("GET failed");
		}

	}

}
