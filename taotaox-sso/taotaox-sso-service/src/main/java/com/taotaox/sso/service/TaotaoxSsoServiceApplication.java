package com.taotaox.sso.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages = { "com.taotaox.manager.dao" })
@ComponentScan(basePackages = { "com.taotaox.sso.service", "com.taotaox.common" })
public class TaotaoxSsoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaotaoxSsoServiceApplication.class, args);
	}
}
