package com.taotaox.manager.web.api;

import com.taotaox.common.bo.EUDataGridResult;
import com.taotaox.common.bo.JsonEntity;
import com.taotaox.common.utils.web.ResponseHelper;
import com.taotaox.manager.entity.TbItem;
import com.taotaox.manager.service.ItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by yachao on 18/1/21.
 */
@Api(value = "Taotaox Api")
@RestController
@RequestMapping("/public/api")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @ApiOperation(value = "Get item by item id", notes = "", tags = "Item", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParam(paramType = "path", name = "itemId", value = "item id", dataType = "long", example = "536563", required = true)
    @RequestMapping(value = "/item/{itemId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JsonEntity<TbItem> getItemById(@PathVariable long itemId) {
        TbItem item = itemService.getItemById(itemId);
        return ResponseHelper.of(item);
    }

    @ApiOperation(value = "Get all item infos", notes = "Return item list and total number", tags = "Item", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "page", value = "page no", dataType = "int", example = "0", required = true),
            @ApiImplicitParam(paramType = "query", name = "rows", value = "how many need to get", dataType = "int", example = "20", required = true)
    })
    @RequestMapping(value = "/item/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JsonEntity<EUDataGridResult> getItemList(@RequestParam(name = "page") Integer page, @RequestParam(name = "rows") Integer rows) {
        EUDataGridResult result = itemService.getItemList(page, rows);
        return ResponseHelper.of(result);
    }
}
