package com.taotaox.common.util.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.taotaox.common.util.web.session.SessionContext;
import com.taotaox.common.util.web.utils.WebUtil;

public class SessionContextHelper {
	
	public static SessionContext getSessionContext() {
		HttpSession session = WebUtil.getCurrentRequest().getSession(false);
		if (session == null) {
			return null;
		}
		return (SessionContext) session.getAttribute(SessionContext.KEY);
	}
	
	public static SessionContext setSessionContext(HttpServletRequest request, SessionContext sessionContext) {
		request.getSession().setAttribute(SessionContext.KEY, sessionContext);
		return sessionContext;
	}
}
