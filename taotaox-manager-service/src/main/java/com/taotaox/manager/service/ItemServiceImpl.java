package com.taotaox.manager.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotaox.common.bo.EUDataGridResult;
import com.taotaox.common.exception.BizException;
import com.taotaox.common.util.web.utils.IdUtils;
import com.taotaox.common.util.web.utils.ObjectUtils;
import com.taotaox.manager.entity.TbItem;
import com.taotaox.manager.entity.TbItemDesc;
import com.taotaox.manager.entity.TbItemExample;
import com.taotaox.manager.dao.TbItemDescMapper;
import com.taotaox.manager.dao.TbItemMapper;
import com.taotaox.manager.dao.TbItemParamItemMapper;
import com.taotaox.manager.entity.TbItemParamItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by yachao on 18/1/21.
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper itemMapper;
    @Autowired
    private TbItemDescMapper itemDescMapper;
    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;

    @Override
    public TbItem getItemById(long itemId) {
        TbItemExample example = new TbItemExample();
        TbItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(itemId);
        List<TbItem> list = itemMapper.selectByExample(example);
        if (!ObjectUtils.isEmpty(list)) {
            TbItem item = list.get(0);
            return item;
        }
        return null;
    }

    @Override
    public EUDataGridResult getItemList(int page, int rows) {
        TbItemExample example = new TbItemExample();

        PageHelper.startPage(page, rows);
        List<TbItem> list = itemMapper.selectByExample(example);

        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);

        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    @Transactional
    public Boolean createItem(TbItem item, String desc, String itemParam) throws BizException {
        Long itemId = IdUtils.getItemId();
        item.setId(itemId);

        item.setStatus((byte) 1);
        item.setCreated(new Date());
        item.setUpdated(new Date());

        itemMapper.insert(item);
        Boolean result = Boolean.TRUE;
        result &= insertItemDesc(itemId, desc);
        if (!result) {
            throw new BizException("Failure of item creation.");
        }

        result &= insertItemParamItem(itemId, itemParam);
        if (!result) {
            throw new BizException("Failure of item creation.");
        }
        return result;
    }

    private Boolean insertItemDesc(Long itemId, String desc) {
        Boolean result = Boolean.TRUE;
        TbItemDesc itemDesc = new TbItemDesc(itemId, new Date(), new Date(), desc);
        itemDescMapper.insert(itemDesc);
        return result;
    }

    private Boolean insertItemParamItem(Long itemId, String itemParam) {
        Boolean result = Boolean.TRUE;

        TbItemParamItem itemParamItem = new TbItemParamItem();
        itemParamItem.setItemId(itemId);
        itemParamItem.setCreated(new Date());
        itemParamItem.setUpdated(new Date());
        itemParamItem.setParamData(itemParam);

        itemParamItemMapper.insert(itemParamItem);
        return result;
    }
}
