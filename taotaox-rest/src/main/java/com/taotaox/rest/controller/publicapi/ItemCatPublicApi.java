package com.taotaox.rest.controller.publicapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.taotaox.common.exception.BizException;
import com.taotaox.common.utils.web.JsonUtils;
import com.taotaox.rest.bo.ItemCatWrapper;
import com.taotaox.rest.service.ItemCatService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Taotaox Rest", tags = "Item Cat")
@RestController
@RequestMapping("/public/api/itemcat")
public class ItemCatPublicApi {

	@Autowired
	private ItemCatService itemCatService;

	@ApiOperation(value = "Callback method", notes = "Resolve over-domain problem.")
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String listItemCat(String callback) throws BizException {
		ItemCatWrapper wrapper = itemCatService.findAllCategory();
		String jsonData = JsonUtils.beanToJson(wrapper);
		String callee = callback + "(" + jsonData + ")";
		return callee;
	}
}
