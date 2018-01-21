package com.taotaox.manager.service;

import com.taotaox.manager.dao.TbContentMapper;
import com.taotaox.manager.entity.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yachao on 18/1/21.
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper contentMapper;

    @Override
    public boolean insertContent(TbContent content) {
        return false;
    }
}
