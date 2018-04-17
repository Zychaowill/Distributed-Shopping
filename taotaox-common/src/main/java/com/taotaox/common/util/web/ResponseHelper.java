package com.taotaox.common.util.web;

import com.taotaox.common.bo.JsonEntity;
import com.taotaox.common.exception.ErrorMsg;
import com.taotaox.common.util.web.utils.WebUtil;

/**
 * Created by yachao on 18/1/20.
 */
public class ResponseHelper {

    public static <T> JsonEntity<T> createInstance(T object) {
        JsonEntity<T> response = new JsonEntity<>(object);
        response.setRequestId(WebUtil.getRequestId());
        return response;
    }

    public static <T> JsonEntity<T> of(T object) {
        return createInstance(object);
    }

    public static <T> JsonEntity<T> ofNothing() {
        return createInstance(null);
    }

    @SuppressWarnings("unchecked")
	public static <T> JsonEntity<T> withMessage(String message) {
        return ofNothing().setMessage(message);
    }

    @SuppressWarnings("unchecked")
	public static <T> JsonEntity<T> errorMessage(Integer code, String message) {
    	return ofNothing().setStatus(code).setMessage(message);
    }
    
    @SuppressWarnings("unchecked")
	public static <T> JsonEntity<T> withMessage(ErrorMsg errorMsg) {
        return withMessage(errorMsg.getMessage()).setStatus(errorMsg.getStatus());
    }
}
