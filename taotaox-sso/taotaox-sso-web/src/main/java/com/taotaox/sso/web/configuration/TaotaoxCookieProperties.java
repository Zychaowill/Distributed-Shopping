package com.taotaox.sso.web.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties("server.session.cookie")
public class TaotaoxCookieProperties {
	private String domain = "localhost";
	private boolean httpOnly = true;
	private String name = "taotaox-sso";
	private String path = "/";
}
