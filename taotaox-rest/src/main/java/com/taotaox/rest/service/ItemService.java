package com.taotaox.rest.service;

import com.taotaox.manager.entity.TbItem;
import com.taotaox.manager.entity.TbItemDesc;
import com.taotaox.manager.entity.TbItemParamItem;

public interface ItemService {
	
	TbItem getItemBaseInfo(long itemId);
	
	TbItemDesc getItemDesc(long itemId);
	
	TbItemParamItem getItemParam(long itemId);
}
