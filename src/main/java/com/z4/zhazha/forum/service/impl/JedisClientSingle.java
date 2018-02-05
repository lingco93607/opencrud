package com.z4.zhazha.forum.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.z4.zhazha.forum.pojo.Article;
import com.z4.zhazha.forum.pojo.ZUser;
import com.z4.zhazha.forum.service.JedisClient;
import com.z4.zhazha.forum.util.JsonUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by ${符柱成} on 2017/3/31.
 */
@Service
// @Qualifier("jedisClient")
public class JedisClientSingle implements JedisClient {

	// @Autowired
	private JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), "localhost");
	private int expiredTime = 60 * 60 * 24 * 2;// 设置过期时间为两天.

	@Override
	public String get(String key) {
		Jedis jedis = jedisPool.getResource();
		String string = jedis.get(key);
		// 解开注释即可体验过期策略
		// jedis.expire(key,5);
		jedis.close();
		return string;
	}

	@Override
	public String set(String key, String value) {
		Jedis jedis = jedisPool.getResource();
		String string = jedis.set(key, value);
		// 解开注释即可体验过期策略
		// jedis.expire(key,5);
		// System.out.println("key : "+key);
		// System.out.println("查看key的剩余生存时间："+jedis.ttl(key));
		jedis.close();
		return string;
	}

	@Override
	public String hget(String hkey, String key) {
		System.out.println("jedisPool   " + jedisPool);
		Jedis jedis = jedisPool.getResource();
		System.out.println("jedis   " + jedis);
		String string = jedis.hget(hkey, key);
		jedis.close();
		return string;
	}

	@Override
	public long hset(String hkey, String key, String value) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.hset(hkey, key, value);
		// 解开注释即可体验过期策略
		// jedis.expire(hkey,expiredTime);
		// System.out.println("key : "+key);
		// System.out.println("查看key001的剩余生存时间："+jedis.ttl(hkey));
		jedis.close();
		return result;
	}

	@Override
	public long incr(String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.incr(key);
		jedis.close();
		return result;
	}

	@Override
	public long expire(String key, int second) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.expire(key, second);
		jedis.close();
		return result;
	}

	@Override
	public long ttl(String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.ttl(key);
		jedis.close();
		return result;
	}

	@Override
	public long del(String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.del(key);
		jedis.close();
		return result;
	}

	@Override
	public long hdel(String hkey, String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.hdel(hkey, key);
		jedis.close();
		return result;
	}

	@Override
	public long zaddList(String key, List<Article> articles) {
		Jedis jedis = jedisPool.getResource();
		Long result = null;
		for (int i = 0; i < articles.size(); i++) {
			result = jedis.zadd(key, articles.get(i).getReadNum(), JsonUtils.objectToJson(articles.get(i)));
		}
		jedis.close();
		return result;
	}

	@Override
	public long zadd(String key, double score, ZUser user) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.zadd(key, score, JsonUtils.objectToJson(user));
		jedis.close();
		return result;
	}

	@Override
	public Set<String> zgetAll(String key, long start, long end) {
		Jedis jedis = jedisPool.getResource();
		Set<String> userSet = jedis.zrange(key, start, end);
		System.out.println("userSet    :" + userSet);
		jedis.close();
		return userSet;
	}

	// 拿最后一名的，所以start跟end必须标记最后一名的位置索引
	@Override
	public Set<String> getTopLast(String key, long start, long end) {
		Jedis jedis = jedisPool.getResource();
		Set<String> userSet = jedis.zrange(key, start, end);
		System.out.println("userSet    :" + userSet);
		jedis.close();
		return userSet;
	}

	@Override
	public List<String> getTop(String key, long top) {
		Jedis jedis = jedisPool.getResource();
		List<String> strs = jedis.lrange(key, 0, top);
		jedis.close();
		return strs;
	}

}