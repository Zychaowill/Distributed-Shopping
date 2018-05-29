package com.taotaox.sso.service.provider;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import com.alibaba.dubbo.config.annotation.Service;
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

@Service(
		version = "1.0.0",
		application = "${dubbo.application.id}",
		protocol = "${dubbo.protocol.id}",
		registry = "${dubbo.registry.id}"
)
@Component
public class UserLoginServiceImpl implements UserLoginService {

	@Autowired
	private TbUserMapper userMapper;
	
	@Autowired
	private JedisClient jedisClient;
	
	@Override
	public JsonEntity<String> login(String username, String password) {
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		criteria.andPasswordEqualTo(DigestUtils.md5DigestAsHex(password.getBytes()));
		
		List<TbUser> list = userMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(list)) {
			return ResponseHelper.withMessage(ErrorMsg.ERROR_USERNAME_OR_PASSWORD);
		}
		
		String token = tokenWithoutPassword(list.get(0));
		return ResponseHelper.of(token);
	}
	
	private String tokenWithoutPassword(TbUser user) {
		String token = SessionContextHelper.token();
		user.setPassword(null);
		jedisClient.set(SessionContextHelper.key(token), JsonUtils.beanToJson(user), SessionContextHelper.expire());
		return token;
	}

}
