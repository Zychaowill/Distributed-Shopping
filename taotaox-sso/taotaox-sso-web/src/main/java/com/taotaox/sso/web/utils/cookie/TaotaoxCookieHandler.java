package com.taotaox.sso.web.utils.cookie;

import java.util.Objects;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taotaox.sso.web.configuration.TaotaoxCookieProperties;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TaotaoxCookieHandler {
	private TaotaoxCookieProperties taotaoxCookieProperties;
	private static final String LOGINID = "loginid";
	
	public TaotaoxCookieHandler(TaotaoxCookieProperties taotaoxCookieProperties) {
		super();
		this.taotaoxCookieProperties = taotaoxCookieProperties;
	}

	public void addLoginIdCookie(HttpServletResponse response, String authedName) {
		final Cookie cookie = new Cookie(LOGINID, authedName);
		cookie.setDomain(taotaoxCookieProperties.getDomain());
		cookie.setPath(taotaoxCookieProperties.getPath());
		cookie.setHttpOnly(true);
		cookie.setSecure(true);

		log.info("Add loginid {} in cookie>>>>>>", authedName);
		response.addCookie(cookie);
	}

	public void removeLoginIdCookie(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			return;
		}

		for (Cookie cookie : cookies) {
			if (Objects.equals(LOGINID, cookie.getName())) {
				cookie.setValue(null);
				cookie.setMaxAge(0);
				cookie.setPath("/");
				response.addCookie(cookie);
				break;
			}
		}
	}
}
