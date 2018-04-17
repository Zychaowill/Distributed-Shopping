package com.taotaox.common.util.web.session;

import java.util.HashMap;
import java.util.Map;

import com.taotaox.common.bo.UserInfo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SystemSessionContext implements SessionContext {

	private static final long serialVersionUID = 1320603935581547423L;
	private UserInfo userInfo;
	private Map<String, Boolean> spyRight = new HashMap<>();
	private String currentRole;
}
