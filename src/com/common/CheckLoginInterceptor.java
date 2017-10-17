package com.common;

import java.util.Map;
import com.system.bean.*;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class CheckLoginInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Map<String, Object> session = invocation.getInvocationContext()
				.getSession();
		SUserBean user = (SUserBean) session.get("user");
		if (user != null) {
			((UserAware)invocation.getAction()).setUser(user);
			return invocation.invoke();
		} else {
			return "session_error";
		}
	}

}
