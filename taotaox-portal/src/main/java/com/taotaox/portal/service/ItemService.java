package com.taotaox.portal.service;

import com.taotaox.portal.bo.ItemInfo;

public interface ItemService {
	
	ItemInfo getItemById(long itemId);
	
	String getItemDescById(long itemId);
	
	String getItemParam(long itemId);
}
