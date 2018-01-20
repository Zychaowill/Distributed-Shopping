package com.taotaox.manager.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.taotaox.manager.dao")
@ComponentScan(value = { "com.taotaox.manager.service", "com.taotaox.manager.web"})
public class TaotaoxManagerWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaotaoxManagerWebApplication.class, args);
	}
}
