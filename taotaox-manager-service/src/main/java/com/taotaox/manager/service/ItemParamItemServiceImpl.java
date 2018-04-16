package com.taotaox.manager.service;

import com.taotaox.common.util.web.JsonUtils;
import com.taotaox.common.util.web.ObjectUtils;
import com.taotaox.manager.dao.TbItemParamItemMapper;
import com.taotaox.manager.entity.TbItemParamItem;
import com.taotaox.manager.entity.TbItemParamItemExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by yachao on 18/1/21.
 */
@Service
public class ItemParamItemServiceImpl implements ItemParamItemService {

    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;

    @Override
    public String getItemParamByItemId(Long itemId) {
        TbItemParamItemExample example = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemId);

        List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
        if (ObjectUtils.isEmpty(list)) {
            return "";
        }

        TbItemParamItem itemParamItem = list.get(0);
        String paramData = itemParamItem.getParamData();
        List<Map<String, Object>> data = JsonUtils.jsonToMapListWithSilence(paramData);
        StringBuffer buffer = new StringBuffer();
        buffer.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"1\" class=\"Ptable\">\n");
        buffer.append("    <tbody>\n");
        for (Map<String, Object> map : data) {
            buffer.append("       <tr>\n");
            buffer.append("          <th class=\"tdTitle\" colspan=\"2\">" + map.get("group") + "</th>\n");
            buffer.append("       </tr>\n");
            List<Map<String, Object>> subList = (List<Map<String, Object>>) map.get("params");
            for (Map<String, Object> subMap: subList) {
                buffer.append("       <tr>\n");
                buffer.append("       <td class=\"tdTitle\">" + subMap.get("k") + "</td>\n");
                buffer.append("       <td>" + subMap.get("v") + "</td>\n");
                buffer.append("       </tr>\n");
            }
        }
        buffer.append("    </tbody>\n");
        buffer.append("</table>");
        return buffer.toString();
    }
}
