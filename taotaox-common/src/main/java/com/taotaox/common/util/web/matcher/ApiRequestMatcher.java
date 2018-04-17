package com.taotaox.common.util.web.matcher;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public class ApiRequestMatcher extends ApiMatcher {
	static {
		pattern = "/public/api/**";
		apiRequestMatcher = new AntPathRequestMatcher(pattern);
	}
}