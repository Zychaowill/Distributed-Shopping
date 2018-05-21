package com.taotaox.rest.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotaox.common.exception.BizException;
import com.taotaox.manager.dao.TbItemCatMapper;
import com.taotaox.manager.entity.TbItemCat;
import com.taotaox.manager.entity.TbItemCatExample;
import com.taotaox.manager.entity.TbItemCatExample.Criteria;
import com.taotaox.rest.bo.ItemCat;
import com.taotaox.rest.bo.ItemCatWrapper;

@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper itemCatMapper;
	
	@Override
	public ItemCatWrapper findAllCategory() throws BizException {
		ItemCatWrapper wrapper = new ItemCatWrapper();
		List<?> data = listItemCat(0L);
		wrapper.setData(data);
		return wrapper;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<?> listItemCat(long parentId) {
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		List resultList = new ArrayList();
		if (CollectionUtils.isNotEmpty(list)) {
			for (TbItemCat tbItemCat : list) {
				if (tbItemCat.getIsParent()) {
					ItemCat itemCat = new ItemCat();
					itemCat.setUrl("/category/" + tbItemCat.getId() + ".html");
					itemCat.setName(tbItemCat.getName());
					itemCat.setItem(listItemCat(tbItemCat.getId()));
					resultList.add(itemCat);
				} else {
					String url = "/item/" + tbItemCat.getId() + ".html|" + tbItemCat.getName();
					resultList.add(url);
				}
			}
		}
		return resultList;
	}
}
