package com.taotaox.portal.service;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.taotaox.common.bo.JsonEntity;
import com.taotaox.common.constant.PropertyConstants;
import com.taotaox.common.util.web.CookieUtils;
import com.taotaox.common.util.web.ResponseHelper;
import com.taotaox.common.util.web.rpc.RpcInvoker;
import com.taotaox.common.util.web.utils.JsonUtils;
import com.taotaox.manager.entity.TbItem;
import com.taotaox.portal.bo.CartItem;

@Service
public class CartServiceImpl implements CartService {

	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${ITEM_INFO_URL}")
	private String ITEM_INFO_URL;
	
	@Override
	public JsonEntity<?> insertCartItem(long itemId, int num, HttpServletRequest request,
			HttpServletResponse response) {
		CartItem cartItem = null;
		List<CartItem> cartItems = getCartItems(request);
		
		for (CartItem item : cartItems) {
			if (Objects.equals(itemId, item.getId())) {
				item.setNum(item.getNum() + num);
				cartItem = item;
				break;
			}
		}
		
		if (cartItem == null) {
			cartItem = new CartItem();
			String jsonData = RpcInvoker.get(REST_BASE_URL + ITEM_INFO_URL + itemId).build().request();
			@SuppressWarnings("unchecked")
			JsonEntity<TbItem> json = JsonUtils.jsonToBean(jsonData, JsonEntity.class);
			if (json.normal()) {
				TbItem item = json.getData();
				cartItem.setId(item.getId());
				cartItem.setTitle(item.getTitle());
				cartItem.setImage(item.getImage());
				cartItem.setNum(item.getNum());
				cartItem.setPrice(item.getPrice());
			}
			cartItems.add(cartItem);
		}
		CookieUtils.setCookie(request, response, PropertyConstants.KEY_TAOTAOX_CART, JsonUtils.beanToJson(cartItems), true);
		return ResponseHelper.ofNothing();
	}

	@Override
	public List<CartItem> listCatItem(HttpServletRequest request, HttpServletResponse response) {
		return getCartItems(request);
	}

	@Override
	public JsonEntity<?> deleteCartItem(long itemId, HttpServletRequest request, HttpServletResponse response) {
		List<CartItem> cartItems = getCartItems(request);
		
		CartItem cartItem = null;
		for (Iterator<CartItem> iterator = cartItems.iterator(); iterator.hasNext();) {
			cartItem = iterator.next();
			if (Objects.equals(itemId, cartItem.getId())) {
				iterator.remove();
				break;
			}
		}
		CookieUtils.setCookie(request, response, PropertyConstants.KEY_TAOTAOX_CART, JsonUtils.beanToJson(cartItems), true);
		return null;
	}

	private List<CartItem> getCartItems(HttpServletRequest request) {
		String cartJson = CookieUtils.getCookieValue(request, PropertyConstants.KEY_TAOTAOX_CART, true);
		if (cartJson != null) {
			try {
				List<CartItem> list = JsonUtils.jsonToBeanList(cartJson, CartItem.class);
				return list;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return Lists.newArrayList();
	}
}
