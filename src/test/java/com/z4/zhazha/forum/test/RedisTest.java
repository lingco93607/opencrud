package com.z4.zhazha.forum.test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import redis.clients.jedis.Jedis;

public class RedisTest {

	public static void main(String[] args) {		
		Jedis jedis = new Jedis("localhost");
		System.out.println("redis:" + jedis.ping());
		jedis.set("foo", "redis foo");
		System.out.println("redis.foo:" + jedis.get("foo"));
		
		testString(jedis);
		
		testMap(jedis);
		
		testList(jedis);
		
		testSet(jedis);
		jedis.close();
	}

	private static void testSet(Jedis jedis) {
		System.out.println("\n now testSet:");
		jedis.sadd("user", "xintt");
		jedis.sadd("user", "xintt1");
		jedis.sadd("user", "xintt2");
		jedis.sadd("user", "xintt3");
		
		jedis.srem("user", "xintt");
		System.out.println(jedis.smembers("user"));
		System.out.println(jedis.sismember("user", "xintt"));
		System.out.println(jedis.srandmember("user"));
		System.out.println(jedis.scard("user"));
	}

	private static void testList(Jedis jedis) {
		System.out.println("\n now testList:");
		jedis.del("java framework");
		System.out.println(jedis.lrange("java framework", 0, -1));
		
		jedis.lpush("java framework", "spring");
		jedis.lpush("java framework", "struts");
		jedis.lpush("java framework", "hibernate");
		System.out.println(jedis.lrange("java framework", 0, -1));
		
		jedis.del("java framework");
		jedis.rpush("java framework", "spring");
		jedis.rpush("java framework", "struts");
		jedis.rpush("java framework", "hibernate");
		System.out.println(jedis.lrange("java framework", 0, -1));
	}

	private static void testMap(Jedis jedis) {
		System.out.println("\n now testMap:");
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "xintt");
		map.put("age", "23");
		map.put("qq", "0000");
		jedis.hmset("user", map);
		
		List<String> rsmap = jedis.hmget("user", "name", "age", "qq");
		System.out.println(rsmap);
		
		jedis.hdel("user", "age");
		System.out.println(jedis.hmget("user", "age"));
		System.out.println(jedis.hlen("user"));
		System.out.println(jedis.exists("user"));
		System.out.println(jedis.hkeys("user"));
		System.out.println(jedis.hvals("user"));
		
		Iterator<String> iter = jedis.hkeys("user").iterator();
		while (iter.hasNext()) {
			String key = iter.next();
			System.out.println(key + ":" + jedis.hmget("user", key));
		}
		
	}

	private static void testString(Jedis jedis) {
		System.out.println("\n now testString:");
		
		jedis.set("name", "xintt");
		System.out.println(jedis.get("name"));
		
		jedis.append("name", " is my lover");
		System.out.println(jedis.get("name"));
		
		jedis.del("name");
		System.out.println(jedis.get("name"));
		
		jedis.mset("name", "xintt", "age", "23", "qq", "0000");
		jedis.incr("age");
		System.out.println(jedis.get("name") + "-" + jedis.get("age") + "-" +jedis.get("qq"));
	}

}
