package com.taotaox.common.util.web;

import java.util.Enumeration;
import java.util.Locale;
import java.util.function.Predicate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.util.StringUtils;
import org.springframework.web.util.WebUtils;

import com.taotaox.common.constant.PropertyConstants;
import com.taotaox.common.util.web.matcher.ApiRequestMatcher;
import com.taotaox.common.util.web.matcher.PublicApiRequestMatcher;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WebUtil {
	public static final String TAOTAOX_SSO_COOKIE_NAME = "taotaox-sso";
	public static final String LOGINID = "loginid";
	public static final String TICKET = "ticket";
	public static final String PUBLIC_KEY_START = "-----BEGIN PUBLIC KEY-----\n";
	public static final String PUBLIC_KEY_END = "\n-----END PUBLIC KEY-----";
	private static Locale defaultLocale = null;
	private static Locale currencyLocale = null;
	private static String i18nDataRootPath;
	
	public static HttpServletRequest getCurrentRequest() {
		return RequestResponseHolder.getRequest();
	}
	
	public static HttpServletResponse getCurrentResponse() {
		return RequestResponseHolder.getResponse();
	}
	
	public static void addCookie(HttpServletResponse response, String path, String key, String value) {
		Cookie cookie = new Cookie(key, value);
		cookie.setMaxAge(-1);
		if (path == null) {
			path = "/";
		}
		cookie.setPath(path);
		response.addCookie(cookie);
	}
	
	public static void killCookie(HttpServletResponse response, String path, String key) {
		Cookie cookie = new Cookie(key, "");
		cookie.setMaxAge(0);
		if (path == null) {
			path = "/";
		}
		cookie.setPath(path);
		response.addCookie(cookie);
	}
	
	public static String getCookie(HttpServletRequest request, String key) {
		if (key == null) {
			return null;
		}
		String value = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (key.equals(cookies[i].getName())) {
					value = cookies[i].getValue();
					break;
				}
			}
		}
		return value;
	}
	
	public static Cookie getSSOCookie(HttpServletRequest request) {
		return WebUtils.getCookie(request, TAOTAOX_SSO_COOKIE_NAME);
	}
	
	public static boolean requireAuthentication(HttpServletRequest request) {
		Authentication authentication = null;
		
		return false;
	}
	
	public static void removeCookies(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (ArrayUtils.nonEmpty(cookies)) {
			for (int i = 0; i < cookies.length; i++) {
				cookies[i].setMaxAge(0);
			}
		}
	}
	
	public static void removeAttrs(HttpServletRequest request, Predicate<String> attrNameSelector) {
		Enumeration<String> attributeNames = request.getSession().getAttributeNames();
		while (attributeNames.hasMoreElements()) {
			String attrName = attributeNames.nextElement();
			if (attrNameSelector.test(attrName)) {
				request.getSession().removeAttribute(attrName);
			}
		}
	}
	
	public static String getSearchEngine() {
		return SpringContextHelper.started().getProperty(PropertyConstants.KEY_TAOTAOX_SEARCHENGINE);
	}
	
	public static boolean isTicketRequest(HttpServletRequest request) {
		return StringUtils.hasText(request.getParameter(LOGINID)) && StringUtils.hasText(request.getParameter(TICKET));
	}
	
	public static String cleanJwtTokenKey(String jwtTokenKey) {
		if (jwtTokenKey.contains(PUBLIC_KEY_START) && jwtTokenKey.contains(PUBLIC_KEY_END)) {
			jwtTokenKey = jwtTokenKey.substring(PUBLIC_KEY_START.length(), jwtTokenKey.length() - PUBLIC_KEY_END.length());
		}
		jwtTokenKey = jwtTokenKey.trim();
		return jwtTokenKey;
	}
	
	public static boolean isAjax(HttpServletRequest request) {
		if (isApiPath(request) || isPublicApiPath(request)) {
			return true;
		} else {
			if (request.getServletPath().endsWith(".html")) {
				return false;
			} else if (request.getServletPath().endsWith("/")) {
				return false;
			} else {
				return true;
			}
		}
	}
	
	public static boolean isApiPath(HttpServletRequest request) {
		return ApiRequestMatcher.matches(request);
	}
	
	public static boolean isPublicApiPath(HttpServletRequest request) {
		return PublicApiRequestMatcher.matches(request);
	}
}
