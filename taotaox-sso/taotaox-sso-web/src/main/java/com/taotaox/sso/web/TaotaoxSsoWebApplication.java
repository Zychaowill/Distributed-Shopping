package com.taotaox.sso.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.taotaox.sso.web.controller")
public class TaotaoxSsoWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaotaoxSsoWebApplication.class, args);
	}
}
