package com.taotaox.manager.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@ApiModel
@NoArgsConstructor
@AllArgsConstructor
public class TbItemDesc {
    private Long itemId;
    @ApiModelProperty(name = "created", dataType = "date")
    private Date created;
    @ApiModelProperty(name = "updated", dataType = "date")
    private Date updated;
    @ApiModelProperty(name = "itemDesc", dataType = "string")
    private String itemDesc;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc == null ? null : itemDesc.trim();
    }
}