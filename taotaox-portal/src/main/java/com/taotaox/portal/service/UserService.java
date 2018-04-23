package com.taotaox.portal.service;

import com.taotaox.manager.entity.TbUser;

public interface UserService {

	TbUser getUserByToken(String token);
}
