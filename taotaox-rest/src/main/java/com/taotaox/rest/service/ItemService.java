package com.taotaox.rest.service;

import com.taotaox.common.bo.JsonEntity;
import com.taotaox.manager.entity.TbItem;
import com.taotaox.manager.entity.TbItemDesc;
import com.taotaox.manager.entity.TbItemParam;

public interface ItemService {
	
	JsonEntity<TbItem> getItemBaseInfo(long itemId);
	
	JsonEntity<TbItemDesc> getItemDesc(long itemId);
	
	JsonEntity<TbItemParam> getItemParam(long itemId);
}
