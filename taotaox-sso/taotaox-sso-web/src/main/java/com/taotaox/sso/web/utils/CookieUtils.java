package com.taotaox.sso.web.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Objects;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.dubbo.common.utils.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CookieUtils {

	public static String getCookieValue(HttpServletRequest request, String cookieName) {
		return getCookieValue(request, cookieName, false);
	}

	public static String getCookieValue(HttpServletRequest request, String cookieName, boolean isDecode) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null || cookieName == null) {
			return null;
		}
		
		String cookieValue = null;
		try {
			for (Cookie cookie : cookies) {
				if (Objects.equals(cookieName, cookie.getName())) {
					if (isDecode) {
						cookieValue = URLDecoder.decode(cookie.getValue(), "UTF-8");
					} else {
						cookieValue = cookie.getValue();
					}
					break;
				}
			}
		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
		}
		return cookieValue;
	}
	
	public static String getCookieValue(HttpServletRequest request, String cookieName, String encodeString) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null || cookieName == null) {
			return null;
		}
		
		String cookieValue = null;
		try {
			for (Cookie cookie : cookies) {
				if (Objects.equals(cookieName, cookie.getName())) {
					cookieValue = URLDecoder.decode(cookie.getValue(), encodeString);
					break;
				}
			}
		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
		}
		return cookieValue;
	}
	
	public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue) {
		setCookie(request, response, cookieName, cookieValue, -1);
	}
	
	public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, int cookieMaxage) {
        setCookie(request, response, cookieName, cookieValue, cookieMaxage, false);
    }
	
	public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, boolean isEncode) {
		setCookie(request, response, cookieName, cookieValue, -1, isEncode);
	}
	
	public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, int cookieMaxAge, boolean isEncode) {
		execute(request, response, cookieName, cookieValue, cookieMaxAge, isEncode);
	}
	
	public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, int cookieMaxAge, String encodeString) {
		execute(request, response, cookieName, cookieValue, cookieMaxAge, encodeString);
	}
	
	public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String cookieName) {
		execute(request, response, cookieName, "", -1, false);
	}
	
	private static final void execute(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, int cookieMaxAge, boolean isEncode) {
		try {
			if (cookieValue == null) {
				cookieValue = "";
			} else if (isEncode) {
				cookieValue = URLEncoder.encode(cookieValue, "utf-8");
			}
			Cookie cookie = new Cookie(cookieName, cookieValue);
			if (cookieMaxAge > 0) {
				cookie.setMaxAge(cookieMaxAge);
			}
			if (request != null) {
				String domain = getDomain(request);
				
				log.info("domain is {}.", domain);
				
				if (!Objects.equals("localhost", domain)) {
					cookie.setDomain(domain);
				}
			}
			cookie.setPath("/");
			response.addCookie(cookie);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private static final void execute(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, int cookieMaxAge, String encodeString) {
		try {
			if (cookieValue == null) {
				cookieValue = "";
			} else {
				cookieValue = URLEncoder.encode(cookieValue, encodeString);
			}
			Cookie cookie = new Cookie(cookieName, cookieValue);
			if (cookieMaxAge > 0) {
				cookie.setMaxAge(cookieMaxAge);
			}
			if (request != null) {
				String domain = getDomain(request);
				
				log.info("domain is {}.", domain);
				
				if (!Objects.equals("localhost", domain)) {
					cookie.setDomain(domain);
				}
			}
			cookie.setPath("/");
			response.addCookie(cookie);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private static final String getDomain(HttpServletRequest request) {
		String domain = null;
		String serverName = request.getRequestURL().toString();
		if (StringUtils.isBlank(serverName)) {
			domain = "";
		} else {
			serverName = serverName.toLowerCase().substring(7);
			final int end = serverName.indexOf("/");
			serverName = serverName.substring(0, end);
			final String[] domains = serverName.split("\\.");
			int len = domains.length;
			if (len > 3) {
				domain = "." + domains[len - 3] + "." + domains[len - 2] + domains[len - 1];
			} else if (len <= 3 && len > 1) {
				domain = "." + domains[len - 2] + "." + domains[len - 1];
			} else {
				domain = serverName;
			}
		}
		
		if (domain != null && domain.indexOf(":") > 0) {
			String[] ary = domain.split("\\:");
			domain = ary[0];
		}
		return domain;
	}
}
