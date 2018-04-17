package com.taotaox.rest.service;

import com.taotaox.common.bo.JsonEntity;

public interface RedisService {

	JsonEntity<?> syncContent(long contentCid);
}
