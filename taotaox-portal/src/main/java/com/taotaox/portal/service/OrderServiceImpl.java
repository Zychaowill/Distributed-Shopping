package com.taotaox.portal.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotaox.common.bo.JsonEntity;
import com.taotaox.common.util.web.rpc.RpcInvoker;
import com.taotaox.common.util.web.utils.JsonUtils;
import com.taotaox.portal.bo.Order;

@Service
public class OrderServiceImpl implements OrderService {

	@Value("${ORDER_BASE_URL}")
	private String ORDER_BASE_URL;
	@Value("${ORDER_CREATE_URL}")
	private String ORDER_CREATE_URL;
	
	@Override
	public String createOrder(Order order) {
		String jsonData = RpcInvoker.post(ORDER_BASE_URL + ORDER_CREATE_URL)
				.append(JsonUtils.beanToJson(order)).build().request();
		@SuppressWarnings("unchecked")
		JsonEntity<Object> json = JsonUtils.jsonToBean(jsonData, JsonEntity.class);
		if (json.normal()) {
			Object objectId = json.getData();
			return String.valueOf(Optional.ofNullable(objectId).orElse(""));
		}
		return null;
	}

}
