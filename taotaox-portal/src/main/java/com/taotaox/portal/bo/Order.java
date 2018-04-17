package com.taotaox.portal.bo;

import java.util.List;

import com.taotaox.manager.entity.TbOrder;
import com.taotaox.manager.entity.TbOrderItem;
import com.taotaox.manager.entity.TbOrderShipping;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Order extends TbOrder {
	
	private List<TbOrderItem> orderItems;
	private TbOrderShipping orderShipping;
}
