package com.taotaox.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotaox.manager.dao.TbContentMapper;
import com.taotaox.manager.entity.TbContent;
import com.taotaox.manager.entity.TbContentExample;
import com.taotaox.manager.entity.TbContentExample.Criteria;

@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper contentMapper;
	
	@Override
	public List<TbContent> getContentList(long contentCid) {
		List<TbContent> list = getContentsFromDB(contentCid);
		return list;
	}

	private List<TbContent> getContentsFromDB(long contentCid) {
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(contentCid);
		List<TbContent> list = contentMapper.selectByExample(example);
		return list;
	}
}
