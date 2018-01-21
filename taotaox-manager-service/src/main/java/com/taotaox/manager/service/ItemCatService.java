package com.taotaox.manager.service;

import com.taotaox.common.bo.EUTreeNode;
import com.taotaox.common.exception.BizException;
import com.taotaox.manager.entity.TbItemCat;

import java.util.List;

/**
 * Created by yachao on 18/1/21.
 */
public interface ItemCatService {

    List<EUTreeNode> getItemCatList(Long parentId) throws BizException;
}
