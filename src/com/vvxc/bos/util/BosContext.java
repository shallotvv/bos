package com.vvxc.bos.util;

import org.apache.struts2.ServletActionContext;

import com.vvxc.bos.bean.User;

public class BosContext {

	public static User getCurrentUser() {
		User User=(User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
		return User;
		
	}
}
