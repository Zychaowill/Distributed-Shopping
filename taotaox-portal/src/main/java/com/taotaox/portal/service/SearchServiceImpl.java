package com.taotaox.portal.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotaox.common.bo.JsonEntity;
import com.taotaox.common.exception.BizException;
import com.taotaox.common.util.web.rpc.RpcInvoker;
import com.taotaox.common.util.web.utils.JsonUtils;
import com.taotaox.portal.bo.SearchResult;

@Service
public class SearchServiceImpl implements SearchService {

	@Value("${SEARCH_BASE_URL}")
	private String SEARCH_BASE_URL;
	
	@Override
	public SearchResult search(String queryString, int page) throws BizException {
		try {
			String jsonData = RpcInvoker.post(SEARCH_BASE_URL)
					.append("q", queryString).append("page", String.valueOf(page))
					.build().request();
				@SuppressWarnings("unchecked")
				JsonEntity<SearchResult> json = JsonUtils.jsonToBean(jsonData, JsonEntity.class);
				if (json.normal()) {
					SearchResult result = json.getData();
					return result;
				}
		} catch (Exception e) {
			throw new BizException(e.getMessage(), e);
		}
		return null;
	}

}
