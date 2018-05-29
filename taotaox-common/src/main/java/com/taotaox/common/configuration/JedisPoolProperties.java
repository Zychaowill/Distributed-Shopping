package com.taotaox.common.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class JedisPoolProperties {
	
	@Value("${redis.host}")
	private String host;
	
	@Value("${redis.port}")
	private int port;
	
	@Value("${redis.timeout}")
	private int timeout;
	
	@Value("#{'${redis.password}'!=''?'${redis.password}':null}")
	private String password;
	
	@Value("${redis.db.index}")
	private int database;

	@Override
	public String toString() {
		return "{\"host\":\"" + host + "\", \"port\":\"" + port + "\", \"timeout\":\"" + timeout + "\", \"password\":\""
				+ password + "\", \"database\":\"" + database + "\"}";
	}
}
