package com.taotaox.manager.web.api;

import com.taotaox.common.bo.EUTreeNode;
import com.taotaox.common.exception.BizException;
import com.taotaox.manager.service.ItemCatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by yachao on 18/1/21.
 */
@Api(value = "Taotaox Api")
@RestController
@RequestMapping("/public/api")
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    @ApiOperation(value = "Get item category list", notes = "data structure is List<Map<String, Object>>", tags = "Item Cat", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParam(paramType = "query", name = "id", dataType = "long", required = false, defaultValue = "0")
    @RequestMapping(value = "/item/cat/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<EUTreeNode> getItemCatList(@RequestParam(value = "id", defaultValue = "0") Long parentId) throws BizException {
        List<EUTreeNode> list = itemCatService.getItemCatList(parentId);
        return list;
    }
}
