package com.taotaox.common.util.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Objects;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class CookieUtils {

	public static String getCookieValue(HttpServletRequest request, String cookieName) {
		return getCookieValue(request, cookieName, false);
	}

	public static String getCookieValue(HttpServletRequest request, String cookieName, boolean isDecoder) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null || cookieName == null) {
			return null;
		}
		String retValue = null;
		try {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals(cookieName)) {
					if (isDecoder) {
						retValue = URLDecoder.decode(cookies[i].getValue(), "UTF-8");
					} else {
						retValue = cookies[i].getValue();
					}
				}
			}
		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
		}
		return retValue;
	}

	public static String getCookieValue(HttpServletRequest request, String cookieName, String encodeString) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null || cookieName == null) {
			return null;
		}
		String retValue = null;
		try {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals(cookieName)) {
					retValue = URLDecoder.decode(cookies[i].getValue(), encodeString);
					break;
				}
			}
		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
		}
		return retValue;
	}

	public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
			String cookieValue) {
		setCookie(request, response, cookieName, cookieValue, -1);
	}
	
	public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
			String cookieValue, int cookieMaxAge) {
		setCookie(request, response, cookieName, cookieValue, cookieMaxAge, false);
	}
	
	public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
			String cookieValue, boolean isEncode) {
		setCookie(request, response, cookieName, cookieValue, -1, isEncode);
	}
	
	public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
			String cookieValue, int cookieMaxAge, boolean isEncode) {
		executeSetCookie(request, response, cookieName, cookieValue, cookieMaxAge, isEncode);
	}
	
	public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
			String cookieValue, int cookieMaxAge, String encodingString) {
		executeSetCookie(request, response, cookieName, cookieValue, cookieMaxAge, encodingString);
	}
	
	public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String cookieName) {
		executeSetCookie(request, response, cookieName, "", -1, false);
	}
	
	private static final void executeSetCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
			String cookieValue, int cookieMaxAge, boolean isEncode) {
		try {
			if (cookieValue == null) {
				cookieValue = "";
			} else if (isEncode) {
				cookieValue = URLEncoder.encode(cookieValue, "UTF-8");
			}
			
			Cookie cookie = new Cookie(cookieName, cookieValue);
			if (cookieMaxAge > 0) {
				cookie.setMaxAge(cookieMaxAge);
			}
			if (Objects.nonNull(request)) {
				String domainName = getDomainName(request);
				if (!Objects.equals("localhost", domainName)) {
					cookie.setDomain(domainName);
				}
			}
			cookie.setPath("/");
			response.addCookie(cookie);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private static final void executeSetCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
			String cookieValue, int cookieMaxAge, String encodingString) {
		try {
			if (cookieValue == null) {
				cookieValue = "";
			} else {
				cookieValue = URLEncoder.encode(cookieValue, encodingString);
			}
			
			Cookie cookie = new Cookie(cookieName, cookieValue);
			if (cookieMaxAge > 0) {
				cookie.setMaxAge(cookieMaxAge);
			}
			if (Objects.nonNull(request)) {
				String domainName = getDomainName(request);
				if (!Objects.equals("localhost", domainName)) {
					cookie.setDomain(domainName);
				}
			}
			cookie.setPath("/");
			response.addCookie(cookie);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private static final String getDomainName(HttpServletRequest request) {
		String domainName = null;
		
		String serverName = request.getRequestURL().toString();
		if (serverName == null || "".equals(serverName)) {
			domainName = "";
		} else {
			serverName = serverName.toLowerCase();
			serverName = serverName.substring(7);
			final int end = serverName.indexOf("/");
			serverName = serverName.substring(0, end);
			final String[] domains = serverName.split("\\.");
			int len = domains.length;
			if (len > 3) {
				// www.xxx.com.cn
				domainName = "." + domains[len - 3] + "." + domains[len - 2] + "." + domains[len - 1];
			} else if (len > 1 && len <= 3) {
				// www.com or xxx.cn
				domainName = "." + domains[len - 2] + "." + domains[len - 1];
			} else {
				domainName = serverName;
			}
		}
		
		if (domainName != null && domainName.indexOf(":") > 0) {
			String[] array = domainName.split("\\:");
			domainName = array[0];
		}
		return domainName;
	}
}
