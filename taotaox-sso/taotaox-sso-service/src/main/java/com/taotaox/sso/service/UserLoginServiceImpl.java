package com.taotaox.sso.service;

import java.util.List;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.taotaox.common.bo.JsonEntity;
import com.taotaox.common.exception.ErrorMsg;
import com.taotaox.common.utils.redis.JedisClient;
import com.taotaox.common.utils.web.JsonUtils;
import com.taotaox.common.utils.web.ResponseHelper;
import com.taotaox.manager.dao.TbUserMapper;
import com.taotaox.manager.entity.TbUser;
import com.taotaox.manager.entity.TbUserExample;
import com.taotaox.manager.entity.TbUserExample.Criteria;
import com.taotaox.sso.api.UserLoginService;
import com.taotaox.sso.service.utils.SessionContextHelper;

@Service
public class UserLoginServiceImpl implements UserLoginService {

	@Autowired
	private TbUserMapper userMapper;
	
	@Autowired
	private JedisClient jedisClient;
	
	@Override
	public JsonEntity<?> login(String username, String password) {
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		criteria.andPasswordEqualTo(DigestUtils.md5DigestAsHex(password.getBytes()));
		
		List<TbUser> list = userMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(list)) {
			ResponseHelper.withMessage(ErrorMsg.ERROR_USERNAME_OR_PASSWORD);
		}
		
		String token = tokenWithoutPassword(list.get(0));
		return ResponseHelper.of(token);
	}
	
	private String tokenWithoutPassword(TbUser user) {
		String token = UUID.randomUUID().toString();
		user.setPassword(null);
		jedisClient.set(SessionContextHelper.key(token), JsonUtils.beanToJson(user), SessionContextHelper.expire());
		return token;
	}

}
