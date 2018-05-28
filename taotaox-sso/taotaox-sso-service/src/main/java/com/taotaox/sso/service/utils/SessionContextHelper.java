package com.taotaox.sso.service.utils;

import java.util.UUID;

import com.google.common.base.Joiner;

public class SessionContextHelper {
	
	private static final String SESSION_PER = "SESSION";
	private static final int sessionExpire = 1800;
	
	public static String token() {
		return UUID.randomUUID().toString();
	}
	
	public static String key() {
		return key(token());
	}
	
	public static String key(String token) {
		return Joiner.on(":").join(SESSION_PER, token);
	}
	
	public static int expire() {
		return sessionExpire;
	}
}
