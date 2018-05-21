package com.taotaox.manager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotaox.common.bo.EUDataGridResult;
import com.taotaox.common.exception.BizException;
import com.taotaox.manager.dao.TbContentMapper;
import com.taotaox.manager.entity.TbContent;
import com.taotaox.manager.entity.TbContentExample;
import com.taotaox.manager.entity.TbContentExample.Criteria;

/**
 * Created by yachao on 18/1/21.
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper contentMapper;

    @Override
    public boolean insertContent(TbContent content) {
    	if (content != null) {
    		contentMapper.insert(content);
    	}
    	return true;
    }

	@Override
	public EUDataGridResult listContent(long categoryId, Integer page, Integer rows) throws BizException {
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		
		PageHelper.startPage(page, rows);
		List<TbContent> list = contentMapper.selectByExample(example);
		PageInfo<TbContent> pageInfo = new PageInfo<>(list);
		EUDataGridResult result = new EUDataGridResult(pageInfo.getTotal(), list);
		return result;
	}
}
