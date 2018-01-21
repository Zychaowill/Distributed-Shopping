package com.taotaox.manager.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotaox.common.bo.EUDataGridResult;
import com.taotaox.common.utils.web.ObjectUtil;
import com.taotaox.manager.dao.TbItemCatMapper;
import com.taotaox.manager.dao.TbItemParamMapper;
import com.taotaox.manager.entity.TbItemCat;
import com.taotaox.manager.entity.TbItemParam;
import com.taotaox.manager.entity.TbItemParamExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by yachao on 18/1/21.
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {

    @Autowired
    private TbItemParamMapper itemParamMapper;
    @Autowired
    private TbItemCatMapper itemCatMapper;

    @Override
    public EUDataGridResult getItemParamList(Integer page, Integer rows) {
        PageHelper.startPage(page, rows);

        TbItemParamExample example = new TbItemParamExample();
        List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
        if (!ObjectUtil.isEmpty(list)) {
            list = list.parallelStream().map(tbItemParam -> {
                TbItemCat itemCat = itemCatMapper.selectByPrimaryKey(tbItemParam.getItemCatId());
                tbItemParam.setItemCatName(itemCat.getName());
                return tbItemParam;
            }).collect(Collectors.toList());
        }

        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);

        PageInfo<TbItemParam> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public TbItemParam getItemParamByCid(long cid) {
        TbItemParamExample example = new TbItemParamExample();
        TbItemParamExample.Criteria criteria = example.createCriteria();
        criteria.andItemCatIdEqualTo(cid);

        List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);

        if (!ObjectUtil.isEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public boolean insertItemParam(TbItemParam itemParam) {
        itemParam.setCreated(new Date());
        itemParam.setUpdated(new Date());

        int result = itemParamMapper.insert(itemParam);
        return result > 0 ? true : false;
    }

    @Override
    public boolean deleteItemParamByIds(String ids) {
        Boolean result = false;
        if (!ObjectUtil.isEmpty(ids)) {
            Arrays.stream(ids.split(",")).map(Long::parseLong).forEach(id -> {
                itemParamMapper.deleteByPrimaryKey(id);
            });
            result = true;
        }
        return result;
    }
}
