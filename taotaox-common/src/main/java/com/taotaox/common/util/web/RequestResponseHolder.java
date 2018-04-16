package com.taotaox.common.util.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Created by yachaoz on 18/4/16 
 */
public class RequestResponseHolder {

	private static final ThreadLocal<HttpRequestResponseHolder> threadLocal = new ThreadLocal<>();
	
	public static void createInstance(HttpServletRequest request, HttpServletResponse response) {
		threadLocal.set(new HttpRequestResponseHolder(request, response));
	}
	
	public static void release() {
		threadLocal.remove();
	}
	
	public static HttpServletRequest getRequest() {
		HttpServletRequest request = get().getRequest();
		if (request == null) {
			ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
			if (servletRequestAttributes != null) {
				request = servletRequestAttributes.getRequest();
			}
		}
		return request;
	}
	
	public static HttpServletResponse getResponse() {
		return get().getResponse();
	}
	
	private static HttpRequestResponseHolder get() {
		if (threadLocal.get() == null) {
			threadLocal.set(new HttpRequestResponseHolder(null, null));
		}
		return threadLocal.get();
	}
	
	public static void setRequest(HttpServletRequest request) {
		get().setRequest(request);
	}
	
	public static void setResponse(HttpServletResponse response) {
		get().setResponse(response);
	}
}
