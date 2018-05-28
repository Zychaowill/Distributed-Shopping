package com.taotaox.common.exception;

/**
 * Created by yachao on 18/1/21.
 */
public enum ErrorMsg {
    EVERYTHING_IS_OK(200, "Everything is OK!"),
    FILE_UPLOAD_SUCCESS(10000, "Uploading files is successful! URL is {}."),
    FILE_UPLOAD_FAIL(100001, "Uploading files is not successful!"),
    FILE_UPLOAD_ERROR(100002, "Uploading files generates an error!"),
    ERROR_USERNAME_OR_PASSWORD(100003, "Username or Password is error!");


    private Integer status;
    private String message;

    ErrorMsg(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
