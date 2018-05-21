package com.taotaox.rest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages = { "com.taotaox.manager.dao" })
@ComponentScan(basePackages = { "com.taotaox.rest" })
public class TaotaoxRestApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(TaotaoxRestApplication.class, args);
	}
}
