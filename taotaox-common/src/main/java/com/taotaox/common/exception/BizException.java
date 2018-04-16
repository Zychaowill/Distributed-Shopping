package com.taotaox.common.exception;

/**
 * Created by yachao on 18/1/21.
 */
public class BizException extends RuntimeException {

	private static final long serialVersionUID = -922066731594183026L;

	public BizException() {
    }

    public BizException(String message) {
        super(message);
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizException(Throwable cause) {
        super(cause);
    }
}
