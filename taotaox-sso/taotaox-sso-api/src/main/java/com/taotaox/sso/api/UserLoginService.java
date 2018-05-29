package com.taotaox.sso.api;

import com.taotaox.common.bo.JsonEntity;

public interface UserLoginService {
	
	JsonEntity<String> login(String username, String password);
}
