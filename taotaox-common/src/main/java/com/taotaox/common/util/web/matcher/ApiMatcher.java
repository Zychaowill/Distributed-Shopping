package com.taotaox.common.util.web.matcher;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

abstract class ApiMatcher {
	protected volatile static String pattern = "/**";
	protected volatile static RequestMatcher apiRequestMatcher = new AntPathRequestMatcher(pattern);
	
	public static boolean matches(HttpServletRequest request) {
		return apiRequestMatcher.matches(request);
	}
	
	public static RequestMatcher getMatcher() {
		return apiRequestMatcher;
	}
	
	public static String pattern() {
		return pattern;
	}
}
