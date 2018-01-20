package com.taotaox.manager.web.api;

import com.taotaox.common.bo.JsonEntity;
import com.taotaox.common.utils.web.ResponseHelper;
import com.taotaox.manager.entity.TbItem;
import com.taotaox.manager.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yachao on 18/1/21.
 */
@RestController
@RequestMapping("/public/api")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/item/{itemId}")
    public JsonEntity<TbItem> getItemById(@PathVariable long itemId) {
        TbItem item = itemService.getItemById(itemId);
        return ResponseHelper.of(item);
    }
}
