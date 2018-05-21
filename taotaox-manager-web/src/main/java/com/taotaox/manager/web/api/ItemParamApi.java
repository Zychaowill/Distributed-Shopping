package com.taotaox.manager.web.api;

import com.taotaox.common.bo.EUDataGridResult;
import com.taotaox.common.bo.JsonEntity;
import com.taotaox.common.exception.BizException;
import com.taotaox.common.utils.web.ResponseHelper;
import com.taotaox.manager.entity.TbItemParam;
import com.taotaox.manager.service.ItemParamService;
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
public class ItemParamApi {

    private static final String BASE_PATH = "/item/param";

    @Autowired
    private ItemParamService itemParamService;

    @ApiOperation(value = "Get item param list", notes = "Get item param list with paging", tags = "Item Param", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "page", dataType = "int", required = true),
            @ApiImplicitParam(paramType = "query", name = "rows", dataType = "int", required = true)
    })
    @RequestMapping(value = BASE_PATH + "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public EUDataGridResult getItemParamList(@RequestParam(name = "page") Integer page, @RequestParam(name = "rows") Integer rows) throws BizException {
        EUDataGridResult result = itemParamService.getItemParamList(page, rows);
        return result;
    }

    @ApiOperation(value = "Get item param", notes = "Get item param by item category id", tags = "Item Param", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParam(paramType = "path", name = "itemCatId", dataType = "long", required = true)
    @RequestMapping(value = BASE_PATH + "/itemcatid/{itemCatId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JsonEntity<TbItemParam> getItemParamByCid(@PathVariable Long itemCatId) throws BizException {
        TbItemParam itemParam = itemParamService.getItemParamByCid(itemCatId);
        return ResponseHelper.of(itemParam);
    }

    @ApiOperation(value = "Create an item param", tags = "Item Param", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = BASE_PATH + "/action/save/{cid}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JsonEntity<Boolean> insertItemParam(@PathVariable Long cid, String paramData) throws BizException {
        TbItemParam itemParam = new TbItemParam();
        itemParam.setItemCatId(cid);
        itemParam.setParamData(paramData);

        Boolean result = itemParamService.insertItemParam(itemParam);
        return ResponseHelper.of(result);
    }

    @ApiOperation(value = "Delete item param", tags = "Item Param", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = BASE_PATH + "/action/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JsonEntity<Boolean> deleteItemParam(String ids) throws BizException {
        Boolean result = itemParamService.deleteItemParamByIds(ids);
        return ResponseHelper.of(result);
    }
}
