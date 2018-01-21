package com.taotaox.manager.service;

import com.taotaox.common.bo.EUDataGridResult;
import com.taotaox.manager.entity.TbItemParam;

/**
 * Created by yachao on 18/1/21.
 */
public interface ItemParamService {

    EUDataGridResult getItemParamList(Integer page, Integer rows);

    TbItemParam getItemParamByCid(long cid);

    boolean insertItemParam(TbItemParam itemParam);

    boolean deleteItemParamByIds(String ids);
}
