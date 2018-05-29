package com.taotaox.sso.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.taotaox.common.bo.JsonEntity;
import com.taotaox.common.utils.web.ResponseHelper;
import com.taotaox.sso.api.UserLoginService;
import com.taotaox.sso.web.utils.CookieUtils;

@RestController
@RequestMapping("/user")
public class UserApi {

	@Reference(
			version = "1.0.0",
			application = "${dubbo.application.id}",
			url = "dubbo://localhost:20880")
	private UserLoginService userLoginService;
	
	@Value("${server.token.key}")
	private String key;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public JsonEntity<String> login(@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password,
			HttpServletRequest request, HttpServletResponse response) {
		JsonEntity<String> jsonEntity = userLoginService.login(username, password);
		if (jsonEntity.normal()) {
			String token = jsonEntity.getData();
			CookieUtils.setCookie(request, response, key, token);
			return ResponseHelper.of(token);
		}
		return jsonEntity;
	}
}
