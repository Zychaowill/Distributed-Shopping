package com.taotaox.portal.service;

import com.taotaox.portal.bo.SearchResult;

public interface SearchService {

	SearchResult search(String queryString, int page);
}
