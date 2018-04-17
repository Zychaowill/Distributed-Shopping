package com.taotaox.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.taotaox.manager.dao.TbItemCatMapper;
import com.taotaox.manager.entity.TbItemCat;
import com.taotaox.manager.entity.TbItemCatExample;
import com.taotaox.manager.entity.TbItemCatExample.Criteria;
import com.taotaox.rest.bo.CatNode;
import com.taotaox.rest.bo.CatResult;

@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper itemCatMapper;
	
	@Override
	public CatResult getCategoryList() {
		CatResult result = new CatResult();
		result.setData(getCategories(0L));
		return result;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<?> getCategories(long parentId) {
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		List nodes = Lists.newArrayList();
		
		int count = 0;
		for (TbItemCat tbItemCat : list) {
			if (tbItemCat.getParent()) {
				CatNode catNode = new CatNode();
				if (parentId == 0) {
					catNode.setName("<a href='products/" + tbItemCat.getId() + ".html'>" + tbItemCat.getName() + "</a>");
				} else {
					catNode.setName(tbItemCat.getName());
				}
				catNode.setUrl("/products/" + tbItemCat.getId() + ".html");
				catNode.setItem(getCategories(tbItemCat.getId()));
				nodes.add(catNode);
				count++;
				if (parentId == 0 && count > 14) {
					break;
				}
			} else {
				nodes.add("/products/" + tbItemCat.getId() + ".html|" + tbItemCat.getName());
			}
		}
		return nodes;
	}

}
