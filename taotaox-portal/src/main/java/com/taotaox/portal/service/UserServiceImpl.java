package com.taotaox.portal.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotaox.common.bo.JsonEntity;
import com.taotaox.common.exception.BizException;
import com.taotaox.common.util.web.rpc.RpcInvoker;
import com.taotaox.common.util.web.utils.JsonUtils;
import com.taotaox.manager.entity.TbUser;

@Service
public class UserServiceImpl implements UserService {

	@Value("${SSO_BASE_URL}")
	private String SSO_BASE_URL;
	@Value("${SSO_USER_TOKEN}")
	private String SSO_USER_TOKEN;
	@Value("${SSO_PAGE_LOGIN}")
	private String SSO_PAGE_LOGIN;
	@Value("${SSO_INTERCEPTOR}")
	private String SSO_INTERCEPTOR;
	@Value("${TAOTAOX_ADMIN}")
	private String TAOTAOX_ADMIN;
	
	@Override
	public TbUser getUserByToken(String token) throws BizException {
		try {
			String jsonData = RpcInvoker.get(SSO_BASE_URL + SSO_USER_TOKEN + token).build().request();
			@SuppressWarnings("unchecked")
			JsonEntity<TbUser> json = JsonUtils.jsonToBean(jsonData, JsonEntity.class);
			if (json.normal()) {
				TbUser user = json.getData();
				return user;
			}
		} catch (Exception e) {
			throw new BizException(e.getMessage(), e);
		}
		return null;
	}

}
