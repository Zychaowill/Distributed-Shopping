package com.taotaox.common.util.web;

import java.util.Objects;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class SpringContextProvider implements ApplicationContextAware {

	private static volatile ApplicationContext context;

	public SpringContextProvider() {
		log.info("Initializing Spring Context Provider......");
	}
	
	static ApplicationContext context() {
		if (context == null) {
			context = WebApplicationContextUtils.getWebApplicationContext(WebUtil.getCurrentRequest().getServletContext());
		}
		return context;
	}
	
	static void assertApplicationContextInitialized() {
		Objects.requireNonNull(context(), "ApplicationContext is not initialized. ClassLoader: " + SpringContextProvider.class.getClassLoader());
	}

	@Override
	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		if (ctx != null) {
			context = ctx;
		}
	}

}
