package com.taotaox.manager.service;

import com.taotaox.common.bo.EUDataGridResult;
import com.taotaox.common.exception.BizException;
import com.taotaox.manager.entity.TbContent;

/**
 * Created by yachao on 18/1/21.
 */
public interface ContentService {

    boolean insertContent(TbContent content);
    
    EUDataGridResult listContent(long categoryId, Integer page, Integer rows) throws BizException;
}
