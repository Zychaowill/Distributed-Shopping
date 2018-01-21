package com.taotaox.manager.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.taotaox.manager.dao")
@ComponentScan(value = { "com.taotaox.manager.service", "com.taotaox.manager.web"})
public class TaotaoxManagerWebApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(TaotaoxManagerWebApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(TaotaoxManagerWebApplication.class);
	}
}
