package com.taotaox.rest.service;

import java.util.List;

import com.taotaox.manager.entity.TbContent;

public interface ContentService {
	
	List<TbContent> getContentList(long contentCid);
}
