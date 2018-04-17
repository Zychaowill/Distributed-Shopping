package com.taotaox.common.exception;

import java.io.Serializable;
import java.util.Locale;
import java.util.Objects;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;

import com.taotaox.common.util.web.utils.ObjectUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BaseException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = 5057992876731494501L;

	private Integer responseStatus = HttpStatus.INTERNAL_SERVER_ERROR.value();
	private String friendlyMessage;
	private Object[] parameters = null;

	public BaseException() {
		super();
	}

	public BaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public BaseException(String message) {
		super(message);
	}

	public BaseException(Throwable cause) {
		super(cause);
	}

	public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BaseException(Object[] parameters) {
		super();
		this.parameters = parameters;
	}

	public BaseException(String message, Object[] parameters) {
		super(message);
		this.parameters = parameters;
	}
	
	public String getMessage(String format) {
		if (ObjectUtils.isEmpty(parameters)) {
			return format;
		}
		return String.format(format, parameters);
	}
	
	public String getMessage() {
		return (super.getMessage() == null? "" : super.getMessage()) + formatParameters();
	}
	
	public String getLocalizedMessage() {
		return getLocalizedMessage(LocaleContextHolder.getLocale());
	}
	
	public String getLocalizedMessage(Locale locale) {
		Objects.requireNonNull(locale);
		return getFromMessageSource(super.getMessage(), parameters, locale);
	}
	
	private String getFromMessageSource(String messageCode, Object[] params, Locale locale) {
		
		return null;
	}
	
	private String formatParameters() {
		StringBuffer buffer = new StringBuffer();
		return null;
	}
}
