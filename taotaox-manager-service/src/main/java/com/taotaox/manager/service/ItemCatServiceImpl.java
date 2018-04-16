package com.taotaox.manager.service;

import com.taotaox.common.bo.EUTreeNode;
import com.taotaox.common.exception.BizException;
import com.taotaox.common.util.web.ObjectUtils;
import com.taotaox.manager.dao.TbItemCatMapper;
import com.taotaox.manager.entity.TbItemCat;
import com.taotaox.manager.entity.TbItemCatExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by yachao on 18/1/21.
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper itemCatMapper;

    @Override
    public List<EUTreeNode> getItemCatList(Long parentId) throws BizException {
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);

        List<TbItemCat> list = itemCatMapper.selectByExample(example);
        List<EUTreeNode> resultList = new ArrayList<>();

        if (!ObjectUtils.isEmpty(list)) {
            return list.parallelStream().map(category -> {
                EUTreeNode node = new EUTreeNode();
                node.setId(category.getId());
                node.setText(category.getName());
                node.setState(category.getIsParent() ? "closed" : "open");
                return node;
            }).collect(Collectors.toList());
        }
        return resultList;
    }
}
