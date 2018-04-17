package com.taotaox.rest.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotaox.manager.dao.TbItemDescMapper;
import com.taotaox.manager.dao.TbItemMapper;
import com.taotaox.manager.dao.TbItemParamItemMapper;
import com.taotaox.manager.entity.TbItem;
import com.taotaox.manager.entity.TbItemDesc;
import com.taotaox.manager.entity.TbItemParamItem;
import com.taotaox.manager.entity.TbItemParamItemExample;
import com.taotaox.manager.entity.TbItemParamItemExample.Criteria;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbItemDescMapper itemDescMapper;
	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;

	@Override
	public TbItem getItemBaseInfo(long itemId) {
		TbItem item = itemMapper.selectByPrimaryKey(itemId);
		return item;
	}

	@Override
	public TbItemDesc getItemDesc(long itemId) {
		TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);
		return itemDesc;
	}

	@Override
	public TbItemParamItem getItemParam(long itemId) {
		TbItemParamItemExample example = new TbItemParamItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
		TbItemParamItem itemParamItem = null;
		if (CollectionUtils.isNotEmpty(list)) {
			itemParamItem = list.get(0);
		}
		return itemParamItem;
	}
}
