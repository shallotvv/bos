package com.vvxc.bos.web.interceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.vvxc.bos.bean.User;

public class BosLoginIntercepter  extends MethodFilterInterceptor{

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		User user=(User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
		if (user!=null) {

			return invocation.invoke();
		}

		return "login";
	}

}
