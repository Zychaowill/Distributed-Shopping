package com.taotaox.common.util.web.utils;

import java.util.Enumeration;
import java.util.function.Predicate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.MDC;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.util.WebUtils;

import com.taotaox.common.constant.PropertyConstants;
import com.taotaox.common.util.web.RequestResponseHolder;
import com.taotaox.common.util.web.SpringContextHelper;
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
		Authentication currentAuth = SecurityContextHolder.getContext().getAuthentication();
		if (currentAuth == null) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void removeCookies(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (ArrayUtil.nonEmpty(cookies)) {
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
	
	public static String getPrincipal() {
		return getPrincipal(getCurrentRequest());
	}

	public static String getPrincipal(HttpServletRequest request) {
		try {
			String loginId = MDC.get("login_trace_loginId");
			if (loginId == null) {
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				if (authentication == null && request != null) {
					SecurityContext securityContext = (SecurityContext) request.getSession()
							.getAttribute("SPRING_SECURITY_CONTEXT");
					authentication = (securityContext == null ? null : securityContext.getAuthentication());
				}
				if (authentication != null) {
					loginId = authentication.getName();
				}
			}
			if (loginId != null) {
				if ("anonymousUser".equalsIgnoreCase(loginId) || "anonymous".equalsIgnoreCase(loginId)) {
					loginId = null;
				}
			}
			return loginId;
		} catch (Exception ex) {
			return null;
		}
	}
	
	public static String getUserIp() {
		return getCurrentRequest().getRemoteAddr();
	}
	
	/**
	 * 
	 * @return Session id, or null of no session exists.
	 * @throws IllegalStateException
	 */
	public static String getSessionId() {
		if (getCurrentRequest() == null) {
			throw new IllegalStateException("current request is null");
		}
		return getSessionId(getCurrentRequest());
	}

	/**
	 * 
	 * @param request
	 * @return Session id, or null of no session exists.
	 */
	public static String getSessionId(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		return (session != null ? session.getId() : null);
	}
	
	public static String getRequestId() {
		return MDC.get("log_trace_reqId");
	}
	
	/**
	 * @deprecated at present, unused.
	 * @see
	 * @param path
	 * @return
	 */
	@Deprecated
	public static String toInfraServiceUrl(String path) {
		if (StringUtils.hasText(path)) {
			path = path.replaceAll("/infrastructure-service", "");
			if (SpringContextHelper.notInKubernetes()) {
				String localServiceTarget = SpringContextHelper.started().getProperty("infrastructure-service.feign.server");
				if (localServiceTarget.startsWith("//") || localServiceTarget.startsWith("https://")
						|| localServiceTarget.startsWith("http://")) {
					return localServiceTarget + "/infrastructure-service" + path;
				} else {
					return "//" + localServiceTarget + "/infrastructure-service" + path;
				}
			}
			return "/infrastructure-service" + path;
		}
		return path;
	}
	
	@Deprecated
	public static String getSingleUploadFileUrl(Integer registerId) {
		return toInfraServiceUrl("/public/api/file/registerId/" + registerId);
	}
	
	@Deprecated
	public static String getBatchUploadFileUrl(Integer registerId) {
		return toInfraServiceUrl("/public/api/file/registerId/" + registerId + "/files");
	}
	
	@Deprecated
	public static String getDownloadFileUrl(String field, String appName) {
		return toInfraServiceUrl("/public/api/file/" + field + "?appName=" + appName);
	}
}
