package com.taotaox.common.configuration;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisPool;

@Configuration
public class JedisPoolConfiguration {

	@Autowired
	private JedisPoolProperties jedisPoolProperties;
	
	@Bean
	public JedisPool jedisPool() {
		JedisPool jedisPool = new JedisPool(jedisPoolConfig(), 
				jedisPoolProperties.getHost(), 
				jedisPoolProperties.getPort(), 
				jedisPoolProperties.getTimeout(), 
				jedisPoolProperties.getPassword(), 
				jedisPoolProperties.getDatabase());
		return jedisPool;
	}
	
/*	@Bean
	public JedisCluster jedisCluster() {
		JedisCluster jedisCluster = null; 
				
		return null;
	}*/
	
	@Bean
	public GenericObjectPoolConfig jedisPoolConfig() {
		GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
		poolConfig.setMaxIdle(300);
		poolConfig.setMaxTotal(60000);
		poolConfig.setTestOnBorrow(true);
		return poolConfig;
	}
}
