package com.taotaox.rest.publicapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taotaox.common.bo.JsonEntity;
import com.taotaox.common.util.web.ResponseHelper;
import com.taotaox.manager.entity.TbContent;
import com.taotaox.rest.service.ContentService;

@RestController
@RequestMapping("/content")
public class ContentApi {

	@Autowired
	private ContentService contentService;

	@RequestMapping("/list/{cid}")
	public JsonEntity<List<TbContent>> getContentList(@PathVariable Long cid) {
		List<TbContent> list = contentService.getContentList(cid);
		return ResponseHelper.of(list);
	}
}
