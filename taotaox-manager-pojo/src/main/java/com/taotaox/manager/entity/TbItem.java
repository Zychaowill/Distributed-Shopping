package com.taotaox.manager.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel
public class TbItem {
    private Long id;
    @ApiModelProperty(name = "title", notes = "Title for goods", dataType = "string")
    private String title;
    @ApiModelProperty(name = "sellPoint", notes = "", dataType = "string")
    private String sellPoint;
    @ApiModelProperty(name = "price", notes = "", dataType = "long")
    private Long price;
    @ApiModelProperty(name = "num", notes = "", dataType = "int")
    private Integer num;
    @ApiModelProperty(name = "barcode", notes = "", dataType = "string")
    private String barcode;
    @ApiModelProperty(name = "image", notes = "", dataType = "string")
    private String image;

    private Long cid;
    @ApiModelProperty(name = "status", notes = "The status of goods. 1:normal, 2:down, 3:delete", dataType = "byte")
    private Byte status;
    @ApiModelProperty(name = "created", notes = "", dataType = "date")
    private Date created;
    @ApiModelProperty(name = "updated", notes = "", dataType = "date")
    private Date updated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getSellPoint() {
        return sellPoint;
    }

    public void setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint == null ? null : sellPoint.trim();
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode == null ? null : barcode.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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
}