package com.taotaox.manager.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotaox.common.bo.EUTreeNode;
import com.taotaox.manager.dao.TbContentCategoryMapper;
import com.taotaox.manager.entity.TbContentCategory;
import com.taotaox.manager.entity.TbContentCategoryExample;
import com.taotaox.manager.entity.TbContentCategoryExample.Criteria;

/**
 * Created by yachao on 18/1/21.
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private TbContentCategoryMapper contentCategoryMapper;

    @Override
    public List<EUTreeNode> listContentCategory(long parentId) {
    	TbContentCategoryExample example = new TbContentCategoryExample();
    	Criteria criteria = example.createCriteria();
    	criteria.andParentIdEqualTo(parentId);
    	
    	List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
    	List<EUTreeNode> resultList = new ArrayList<>();
    	for (TbContentCategory tbContentCategory : list) {
    		EUTreeNode node = new EUTreeNode();
    		node.setId(tbContentCategory.getId());
    		node.setText(tbContentCategory.getName());
    		if (tbContentCategory.getIsParent()) {
    			node.setState("closed");
    		} else {
    			node.setState("open");
    		}
    		resultList.add(node);
    	}
        return resultList;
    }

    @Override
    public boolean insertContentCategory(long parentId, String name) {
    	Date date = Date.from(Instant.now());
    	TbContentCategory node = new TbContentCategory();
    	node.setName(name);
    	node.setParentId(parentId);
    	node.setIsParent(false);
    	node.setCreated(date);
    	node.setUpdated(date);
    	node.setSortOrder(1);
    	node.setStatus(1);
    	
    	contentCategoryMapper.insert(node);
    	
    	TbContentCategory parentNode = contentCategoryMapper.selectByPrimaryKey(parentId);
    	if (!parentNode.getIsParent()) {
    		parentNode.setIsParent(true);
    		contentCategoryMapper.updateByPrimaryKey(parentNode);
    	}
        return true;
    }
}
