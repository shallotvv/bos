package com.vvxc.bos.web.action;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.vvxc.bos.bean.User;
import com.vvxc.bos.service.IUserService;
import com.vvxc.bos.util.MD5Utils;
import com.vvxc.bos.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User>{
	@Autowired
	IUserService userService;
	
	private String checkcode;
	
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

	public String login() {
		
		String key=(String) ServletActionContext.getRequest().getSession().getAttribute("key");
		
		if (StringUtils.isNotBlank(checkcode)) {
			if (checkcode.equals(key)) {
				User user=userService.login(getModel());
				if (user!=null) {
					ServletActionContext.getRequest().getSession().setAttribute("loginUser", user); 
					this.clearActionErrors();
					return "home";
				}
				
				this.addActionError("用户名或密码错误");
				return "login";
			}
			this.addActionError("验证码错误");
		}
		return "login";
	}
	
	public String editPassword() throws IOException {
		User user=(User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");

		String flag="1";
		if (user!=null) {
			String password=getModel().getPassword();
			password=MD5Utils.md5(password);
			try {

				userService.updatePw(password,user.getId());
			} catch (Exception e) {
				// TODO: handle exception
				flag="0";
			}
		}
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(flag);

		
		return NONE;
		
	}
	
	public String logout(){
		
		ServletActionContext.getRequest().getSession().invalidate();
		return "login";
	}

}
