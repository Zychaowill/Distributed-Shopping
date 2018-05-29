package com.taotaox.sso.service.utils;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;

import com.google.common.base.Joiner;

public final class SessionContextHelper {
	@Value("${server.session.prefix}")
	private static String SESSION_PREFIX = "SESSION";
	@Value("${server.session.expire}")
	private static int SESSION_EXPIRE = 1800;
	
	public static String token() {
		return UUID.randomUUID().toString();
	}
	
	public static String key() {
		return key(token());
	}
	
	public static String key(String token) {
		return Joiner.on(":").join(SESSION_PREFIX, token);
	}
	
	public static int expire() {
		return SESSION_EXPIRE;
	}
}
