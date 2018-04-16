package com.taotaox.manager.web.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.taotaox.common.util.web.ResponseHelper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ApiExceptionAspect {
	
	@ExceptionHandler
	public Object handler(HttpServletRequest request, HttpServletResponse response, Exception e) {
		log.info("Find an error of Restful Http request.");
		
		
		return ResponseHelper.withMessage(e.getMessage());
	}
}
