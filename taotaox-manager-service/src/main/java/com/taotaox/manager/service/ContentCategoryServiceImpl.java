package com.taotaox.manager.service;

import com.taotaox.common.bo.EUTreeNode;
import com.taotaox.manager.dao.TbContentCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yachao on 18/1/21.
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private TbContentCategoryMapper contentCategoryMapper;

    @Override
    public List<EUTreeNode> getCategoryList(long parentId) {
        return null;
    }

    @Override
    public boolean insertContentCategory(long parentId, String name) {
        return false;
    }
}
