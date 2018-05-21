package com.taotaox.rest.service;

import com.taotaox.common.exception.BizException;
import com.taotaox.rest.bo.ItemCatWrapper;

public interface ItemCatService {
	
	ItemCatWrapper findAllCategory() throws BizException;
}
