package com.z4.zhazha.forum.service.impl;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.JedisPool;

public class ZJedisPool {
	private JedisPool jedisPool;
	public ZJedisPool(final GenericObjectPoolConfig poolConfig, final String host, final int port) {
		jedisPool = new JedisPool(poolConfig, host, port);
	}

}
