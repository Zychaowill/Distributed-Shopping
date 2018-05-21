package com.taotaox.manager.service;

import com.taotaox.common.bo.EUTreeNode;

import java.util.List;

/**
 * Created by yachao on 18/1/21.
 */
public interface ContentCategoryService {

    List<EUTreeNode> listContentCategory(long parentId);

    boolean insertContentCategory(long parentId, String name);
}
