package com.taotaox.portal.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotaox.common.bo.JsonEntity;
import com.taotaox.common.exception.BizException;
import com.taotaox.common.util.web.rpc.RpcInvoker;
import com.taotaox.common.util.web.utils.JsonUtils;
import com.taotaox.manager.entity.TbItemDesc;
import com.taotaox.manager.entity.TbItemParamItem;
import com.taotaox.portal.bo.ItemInfo;

@Service
public class ItemServiceImpl implements ItemService {

	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${ITEM_INFO_URL}")
	private String ITEM_INFO_URL;
	@Value("${ITEM_DESC_URL}")
	private String ITEM_DESC_URL;
	@Value("${ITEM_PARAM_URL}")
	private String ITEM_PARAM_URL;
	
	@Override
	public ItemInfo getItemById(long itemId) throws BizException {
		try {
			String jsonData = RpcInvoker.get(REST_BASE_URL + ITEM_INFO_URL + itemId).build().request();
			if (StringUtils.isNoneBlank(jsonData)) {
				@SuppressWarnings("unchecked")
				JsonEntity<ItemInfo> json = JsonUtils.jsonToBean(jsonData, JsonEntity.class);
				if (json.normal()) {
					ItemInfo item = json.getData();
					return item;
				}
			}
		} catch (Exception e) {
			throw new BizException(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public String getItemDescById(long itemId) throws BizException {
		try {
			String jsonData = RpcInvoker.get(REST_BASE_URL + ITEM_DESC_URL + itemId).build().request();
			if (StringUtils.isNoneBlank(jsonData)) {
				@SuppressWarnings("unchecked")
				JsonEntity<TbItemDesc> json = JsonUtils.jsonToBean(jsonData, JsonEntity.class);
				if (json.normal()) {
					String result = Optional.ofNullable(json.getData()).orElseGet(TbItemDesc::new).getItemDesc();
					return result;
				}
			}
		} catch (Exception e) {
			throw new BizException(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public String getItemParam(long itemId) {
		try {
			String jsonData = RpcInvoker.get(REST_BASE_URL + ITEM_PARAM_URL + itemId).build().request();
			if (StringUtils.isNoneBlank(jsonData)) {
				@SuppressWarnings("unchecked")
				JsonEntity<TbItemParamItem> json = JsonUtils.jsonToBean(jsonData, JsonEntity.class);
				if (json.normal()) {
					TbItemParamItem itemParamItem = json.getData();
					String paramData = itemParamItem.getParamData();
					
					List<Map<String,Object>> list = JsonUtils.jsonToMapList(paramData);
					StringBuffer buffer = new StringBuffer();
					buffer.append("<table cellpadding=\"1\" cellspacing=\"1\" width=\"100%\" border=\"0\" >\n");
					for (Map<String, Object> map : list) {
						buffer.append("<tr>\n");
						buffer.append("  <th colspan=\"2\">").append(map.get("group")).append("</th>\n");
						buffer.append("</tr>\n");
						
						@SuppressWarnings("unchecked")
						List<Map<String, Object>> innerList = (List<Map<String, Object>>) map.get("params");
						for (Map<String, Object> innerMap : innerList) {
							buffer.append("<tr>\n");
							buffer.append("  <th>").append(innerMap.get("k")).append("</th>\n");
							buffer.append("  <th>").append(innerMap.get("v")).append("</th>\n");
							buffer.append("</tr>\n");
						}
					}
					buffer.append("</table>");
					return buffer.toString();
				}
			}
		} catch (Exception e) {
			throw new BizException(e.getMessage(), e);
		}
		return null;
	}

}
