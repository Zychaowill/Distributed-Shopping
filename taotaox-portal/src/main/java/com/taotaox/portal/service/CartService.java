package com.taotaox.portal.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taotaox.common.bo.JsonEntity;
import com.taotaox.portal.bo.CartItem;

public interface CartService {

	JsonEntity<?> insertCartItem(long itemId, int num, HttpServletRequest request, HttpServletResponse response);
	
	List<CartItem> listCatItem(HttpServletRequest request, HttpServletResponse response);
	
	JsonEntity<?> deleteCartItem(long itemId, HttpServletRequest request, HttpServletResponse response);
}
