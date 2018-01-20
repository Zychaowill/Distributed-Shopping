package com.taotaox.manager.service;

import com.taotaox.common.bo.EUDataGridResult;
import com.taotaox.common.exception.BizException;
import com.taotaox.manager.entity.TbItem;

/**
 * Created by yachao on 18/1/21.
 */
public interface ItemService {

    TbItem getItemById(long itemId);

    EUDataGridResult getItemList(int page, int rows);

    Boolean createItem(TbItem item, String desc, String itemParam) throws BizException;
}
