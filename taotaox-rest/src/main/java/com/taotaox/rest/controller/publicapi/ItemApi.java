package com.taotaox.rest.controller.publicapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taotaox.common.bo.JsonEntity;
import com.taotaox.common.util.web.ResponseHelper;
import com.taotaox.manager.entity.TbItem;
import com.taotaox.manager.entity.TbItemDesc;
import com.taotaox.manager.entity.TbItemParamItem;
import com.taotaox.rest.service.ItemService;

@RestController
@RequestMapping("/public/api/item")
public class ItemApi {

	@Autowired
	private ItemService itemService;
	
	@RequestMapping(value = "/info/{itemId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public JsonEntity<TbItem> getItemBaseInfo(@PathVariable Long itemId) {
		TbItem item = itemService.getItemBaseInfo(itemId);
		return ResponseHelper.of(item);
	}
	
	@RequestMapping(value = "/desc/{itemId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public JsonEntity<TbItemDesc> getItemDesc(@PathVariable Long itemId) {
		TbItemDesc itemDesc = itemService.getItemDesc(itemId);
		return ResponseHelper.of(itemDesc);
	}
	
	@RequestMapping(value = "/param/{itemId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public JsonEntity<TbItemParamItem> getItemParam(@PathVariable Long itemId) {
		TbItemParamItem itemParam = itemService.getItemParam(itemId);
		return ResponseHelper.of(itemParam);
	}
}
