package com.taotaox.manager.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.taotaox.common.bo.EUDataGridResult;
import com.taotaox.common.exception.BizException;
import com.taotaox.manager.service.ContentService;

import io.swagger.annotations.Api;

@Api(value = "Taotaox Api")
@RestController
@RequestMapping("/public/api/content")
public class ContentApi {

	@Autowired
	private ContentService contentService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public EUDataGridResult listContent(Long categoryId, Integer page, Integer rows) throws BizException {
		return contentService.listContent(categoryId, page, rows);
	}
}
