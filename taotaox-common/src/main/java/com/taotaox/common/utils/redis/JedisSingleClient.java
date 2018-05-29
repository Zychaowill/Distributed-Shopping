package com.taotaox.common.utils.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Primary
@Component
public class JedisSingleClient implements JedisClient {

	@Autowired
	private JedisPool jedisPool;
	
	@Override
	public String get(String key) {
		Jedis jedis = jedis();
		String str = jedis.get(key);
		close(jedis);
		return str;
	}

	@Override
	public String set(String key, String value) {
		Jedis jedis = jedis();
		String str = jedis.set(key, value);
		close(jedis);
		return str;
	}

	@Override
	public String hget(String hkey, String key) {
		Jedis jedis = jedis();
		String str = jedis.hget(hkey, key);
		close(jedis);
		return str;
	}

	@Override
	public long hset(String hkey, String key, String value) {
		Jedis jedis = jedis();
		Long result = jedis.hset(hkey, key, value);
		close(jedis);
		return result;
	}

	@Override
	public long incr(String key) {
		Jedis jedis = jedis();
		Long result = jedis.incr(key);
		close(jedis);
		return result;
	}

	@Override
	public long expire(String key, int second) {
		Jedis jedis = jedis();
		Long result = jedis.expire(key, second);
		close(jedis);
		return result;
	}

	@Override
	public long ttl(String key) {
		Jedis jedis = jedis();
		Long result = jedis.ttl(key);
		close(jedis);
		return result;
	}

	@Override
	public long del(String key) {
		Jedis jedis = jedis();
		Long result = jedis.del(key);
		close(jedis);
		return result;
	}

	@Override
	public long hdel(String hkey, String key) {
		Jedis jedis = jedis();
		Long result = jedis.hdel(hkey, key);
		close(jedis);
		return result;
	}

	private Jedis jedis() {
		return jedisPool.getResource();
	}
	
	private void close(Jedis jedis) {
		jedis.close();
	}

	@Override
	public void set(String key, String value, int expire) {
		synchronized (this) {
			set(key, value);
			expire(key, expire);
		}		
	}
}
