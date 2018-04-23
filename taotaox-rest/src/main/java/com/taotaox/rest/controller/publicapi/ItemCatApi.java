package com.taotaox.rest.controller.publicapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taotaox.common.exception.BizException;
import com.taotaox.common.util.web.utils.JsonUtils;
import com.taotaox.rest.bo.CatResult;
import com.taotaox.rest.service.ItemCatService;

@RestController
@RequestMapping("/public/api/itemcat")
public class ItemCatApi {
	
	@Autowired
	private ItemCatService itemCatService;
	
	@RequestMapping(value = "/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String getCategoryList(String callback) throws BizException {
		CatResult categories = itemCatService.getCategoryList();
		String jsonData = JsonUtils.beanToJson(categories);
		String result = callback + "(" + jsonData + ")";
		return result;
	}
	
/*	@RequestMapping("/itemcat/list")
	public Object getItemCatList(String callback) throws BizException {
		CatResult catResult = itemCatService.getCategoryList();
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(catResult);
		mappingJacksonValue.setJsonpFunction(callback);
		return mappingJacksonValue;
	}*/
}
