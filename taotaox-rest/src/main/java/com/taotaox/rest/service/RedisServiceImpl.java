package com.taotaox.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotaox.common.bo.JsonEntity;
import com.taotaox.common.util.web.ResponseHelper;
import com.taotaox.common.util.web.utils.ExceptionUtil;
import com.taotaox.rest.dao.JedisClient;

@Service
public class RedisServiceImpl implements RedisService {

	@Autowired
	private JedisClient jedisClient;
	
	@Value("${INDEX_CONTENT_REDIS_KEY}")
	private String INDEX_CONTENT_REDIS_KEY;
	
	@Override
	public JsonEntity<?> syncContent(long contentCid) {
		try {
			jedisClient.hdel(INDEX_CONTENT_REDIS_KEY, String.valueOf(contentCid));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseHelper.errorMessage(500, ExceptionUtil.getStackTrace(e));
		}
		return ResponseHelper.ofNothing();
	}

}
