package com.taotaox.common.util.web;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Paths;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.util.Assert;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SpringContextHelper {
	private static SpringContextHelper instance = new SpringContextHelper();
	
	private static volatile Boolean inKub;
	
	public static SpringContextHelper instance() {
		return started();
	}

	/**
	 * Alias for {@link #started()}
	 * Get SpringContextHelper singleton instance <strong>after</strong> Spring ApplicationContext initialized.
	 * 
	 * @deprecated in favor of {@link #started()}
	 * @see SpringContextHelper#started()
	 * @return SpringContextHelper
	 */
	@Deprecated
	public static SpringContextHelper afterSpringFullyStarted() {
		return started();
	}
	
	/**
	 * Get SpringContextHelper singleton instance <strong>after</strong> Spring ApplicationContext initialized.
	 * 
	 * @return SpringContextHelper
	 */
	public static SpringContextHelper started() {
		log.info("wait initialized ApplicationContext.");
		SpringContextProvider.assertApplicationContextInitialized();
		return instance;
	}
	
	public ApplicationContext getApplicationContext() {
		return SpringContextProvider.context();
	}
	
	public String getProperty(String key) {
		return doGetProperty(key);
	}

	public String getRequiredProperty(String key) {
		Assert.hasText(key, "key required");
		String value = getProperty(key);
		Assert.hasText(value, "property not configured: " + key);
		return value;
	}
	
	public String getProperty(String key, String defaultValue) {
		return doGetProperty(key, defaultValue);
	}
	
	public String getPropertyOfSources(String key, String propertySourceName) {
		return (String) getPropertyOfSources(propertySourceName).getProperty(key);
	}
	
	public String getPropertyOfSources(String key, String defaultValue, String propertySourceName) {
		Assert.hasText(key, "key required");
		String value = (String) getPropertyOfSources(propertySourceName).getProperty(key);
		return value == null ? defaultValue : value;
	}
	
	private PropertySource<?> getPropertyOfSources(String propertySourceName) {
		Assert.hasText(propertySourceName, "propertySourceName required");
		MutablePropertySources mps = ((ConfigurableEnvironment) getApplicationContext().getEnvironment())
				.getPropertySources();
		PropertySource<?> propertySource = mps.get(propertySourceName);
		if (propertySource == null) {
			propertySource = new PropertiesPropertySource(propertySourceName, new Properties());
		}
		return propertySource;
	}
	
	
	private String doGetProperty(String key) {
		return doGetProperty(key, null);
	}
	
	private String doGetProperty(String key, String defaultValue) {
		if (key == null) {
			return null;
		}
		return getApplicationContext().getEnvironment().getProperty(key, defaultValue);
	}

	public String getHostName() {
		return getHostName(getApplicationContext().getEnvironment());
	}
	
	public static String getHostName(Environment environment) {
		try {
			return environment.getProperty("HOSTNAME", InetAddress.getLocalHost().getHostName());
		} catch (UnknownHostException e) {
			return System.getenv("HOSTNAME");
		}
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getBean(String name) {
		return (T) getApplicationContext().getBean(name);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getBeanSilently(String name) {
		try {
			return (T) getApplicationContext().getBean(name);
		} catch (Exception ex) {
			return null;
		}
	}
	
	public <T> T getBean(Class<T> clazz) throws BeansException {
		return getApplicationContext().getBean(clazz);
	}
	
	public <T> T getBeanSilently(Class<T> clazz) {
		try {
			return getApplicationContext().getBean(clazz);
		} catch (Exception ex) {
			return null;
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> T getBeanByNameAndType(String name, Class type) {
		Object bean = getBeanSilently(name);
		return type.isInstance(bean) ? (T) bean : null;
	}
	
	public static boolean inKubernetes() {
		if (inKub == null) {
			inKub = Paths.get("/var/run/secrets/kubernetes.io/serviceaccount/token").toFile().exists()
					&& Paths.get("/var/run/secrets/kubernetes.io/serviceaccount/ca.crt").toFile().exists();
		}
		return inKub.booleanValue();
	}
	
	public static boolean notInKubernetes() {
		return !inKubernetes();
	}
}
