package com.taotaox.rest.controller.publicapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taotaox.common.bo.JsonEntity;
import com.taotaox.rest.service.RedisService;

@RestController
@RequestMapping("/cache/sync")
public class RedisApi {

	@Autowired
	private RedisService redisService;
	
	@RequestMapping(value = "/content/{contentCid}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public JsonEntity<?> syncContentCache(@PathVariable Long contentCid) {
		return redisService.syncContent(contentCid);
	}
}
