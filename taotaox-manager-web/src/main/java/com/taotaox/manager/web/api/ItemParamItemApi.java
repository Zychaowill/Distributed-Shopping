package com.taotaox.manager.web.api;

import com.taotaox.common.exception.BizException;
import com.taotaox.manager.service.ItemParamItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by yachao on 18/1/21.
 */
@Api("Taotaox Api")
@Controller
@RequestMapping("/public/api")
public class ItemParamItemApi {

    @Autowired
    private ItemParamItemService itemParamItemService;

    @ApiOperation(value = "Show item param info", notes = "Get item param by item id", tags = "Item Param Item", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParam(paramType = "path", name = "itemId", dataType = "long", required = true)
    @RequestMapping(value = "/item/{itemId}/param/action/show", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String showItemParam(@PathVariable Long itemId, Model model) throws BizException {
        String itemParam = itemParamItemService.getItemParamByItemId(itemId);
        model.addAttribute("itemParam", itemParam);
        return "item";
    }
}
