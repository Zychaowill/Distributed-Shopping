package com.taotaox.common.utils.web;

import com.taotaox.common.bo.JsonEntity;

/**
 * Created by yachao on 18/1/20.
 */
public class ResponseHelper {

    public static <T> JsonEntity<T> createInstance(T object) {
        JsonEntity<T> response = new JsonEntity<>(object);
        response.setRequestId(null);
        return response;
    }

    public static <T> JsonEntity<T> of(T object) {
        return createInstance(object);
    }

    public static <T> JsonEntity<T> ofNothing() {
        return createInstance(null);
    }

    public static <T> JsonEntity<T> withMessage(String message) {
        return ofNothing().setMessage(message);
    }
}
