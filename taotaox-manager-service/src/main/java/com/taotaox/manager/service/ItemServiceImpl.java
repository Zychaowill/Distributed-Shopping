package com.taotaox.manager.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotaox.common.bo.EUDataGridResult;
import com.taotaox.common.exception.BizException;
import com.taotaox.common.utils.web.ObjectUtil;
import com.taotaox.manager.entity.TbItem;
import com.taotaox.manager.entity.TbItemExample;
import com.taotaox.manager.dao.TbItemDescMapper;
import com.taotaox.manager.dao.TbItemMapper;
import com.taotaox.manager.dao.TbItemParamItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yachao on 18/1/21.
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper tbItemMapper;
    @Autowired
    private TbItemDescMapper itemDescMapper;
    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;

    @Override
    public TbItem getItemById(long itemId) {
        TbItemExample example = new TbItemExample();
        TbItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(itemId);
        List<TbItem> list = tbItemMapper.selectByExample(example);
        if (!ObjectUtil.isEmpty(list)) {
            TbItem item = list.get(0);
            return item;
        }
        return null;
    }

    @Override
    public EUDataGridResult getItemList(int page, int rows) {
        TbItemExample example = new TbItemExample();

        PageHelper.startPage(page, rows);
        List<TbItem> list = tbItemMapper.selectByExample(example);

        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);

        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public Boolean createItem(TbItem item, String desc, String itemParam) throws BizException {
        return null;
    }
}
