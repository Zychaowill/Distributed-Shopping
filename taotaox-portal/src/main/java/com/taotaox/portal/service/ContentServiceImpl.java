package com.taotaox.portal.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.taotaox.common.bo.JsonEntity;
import com.taotaox.common.util.web.rpc.RpcInvoker;
import com.taotaox.common.util.web.utils.JsonUtils;
import com.taotaox.manager.entity.TbContent;

@Service
public class ContentServiceImpl implements ContentService {

	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${REST_INDEX_AD_URL}")
	private String REST_INDEX_AD_URL;
	
	@Override
	public String listContentString() {
		String jsonData = RpcInvoker.get(REST_BASE_URL + REST_INDEX_AD_URL).build().request();
		@SuppressWarnings("unchecked")
		JsonEntity<List<TbContent>> json = JsonUtils.jsonToBean(jsonData, JsonEntity.class);
		
		List<TbContent> list = json.getData();
		List<Map<String, Object>> result = Lists.newArrayList();
		try {
			Map<String, Object> map = null;
			for (TbContent content : list) {
				map = new HashMap<>();
				map.put("src", content.getPic());
				map.put("height", 240);
				map.put("width", 670);
				map.put("srcB", content.getPic2());
				map.put("widthB", 550);
				map.put("heightB", 240);
				map.put("href", content.getUrl());
				map.put("alt", content.getSubTitle());
				result.add(map);
			}
			return JsonUtils.beanToJson(list);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
