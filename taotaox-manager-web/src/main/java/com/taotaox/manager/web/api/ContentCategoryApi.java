package com.taotaox.manager.web.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taotaox.common.bo.EUTreeNode;
import com.taotaox.common.bo.JsonEntity;
import com.taotaox.common.exception.BizException;
import com.taotaox.common.utils.web.ResponseHelper;
import com.taotaox.manager.service.ContentCategoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value = "Taotaox Api")
@RestController
@RequestMapping("/public/api/content/category")
public class ContentCategoryApi {

	@Autowired
	private ContentCategoryService contentCategoryService;

	@ApiOperation(value = "Get content category by parent id", notes = "", tags = "Content Category", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParam(paramType = "query", name = "parentId", value = "parent id", dataType = "long", example = "536563", required = false)
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public JsonEntity<List<EUTreeNode>> listContentCategory(@RequestParam(defaultValue = "0", required = false) long parentId)
			throws BizException {
		List<EUTreeNode> list = contentCategoryService.listContentCategory(parentId);
		return ResponseHelper.of(list);
	}
	
	@ApiOperation(value = "Post content category", notes = "", tags = "Content Category", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "form", name = "parentId", value = "parent id", dataType = "Long", required = true),
		@ApiImplicitParam(paramType = "form", name = "name", value = "name", dataType = "String", required = true)
	})
	@RequestMapping(value = "/action/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public JsonEntity<Boolean> insertContentCategory(@RequestParam(value = "parentId") long parentId,
			@RequestParam(value = "name") String name) {
		Boolean result = contentCategoryService.insertContentCategory(parentId, name);
		return ResponseHelper.of(result);
	}
}
