package com.taotaox.common.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by yachao on 18/1/20.
 */
@ApiModel
public class JsonEntity<T> implements Serializable {
    private static final long serialVersionUID = -1176732685636776472L;
    @ApiModelProperty(value = "API http response status. 200 is ok, 500 is failed.", example = "200", required = true)
    private int status = 200;
    @ApiModelProperty(value = "Human readable response message")
    private String message;
    @ApiModelProperty(value = "Unique request id", example = "5a124806f9f9e98924014e76", required = true)
    private String requestId;
    @ApiModelProperty(value = "Actual response data as JSON")
    T data;

    public JsonEntity() {
    }

    public JsonEntity(T data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public JsonEntity setStatus(int status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public JsonEntity setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getRequestId() {
        return requestId;
    }

    public JsonEntity setRequestId(String requestId) {
        this.requestId = requestId;
        return this;
    }

    public T getData() {
        return data;
    }

    public JsonEntity setData(T data) {
        this.data = data;
        return this;
    }

    @JsonIgnore
    public boolean normal() {
        return status == 200;
    }

    @Override
    public String toString() {
        return "JsonEntity{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", requestId='" + requestId + '\'' +
                ", data=" + data +
                '}';
    }
}
