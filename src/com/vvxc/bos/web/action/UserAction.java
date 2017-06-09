package com.vvxc.bos.web.action;

import java.io.IOException;
import java.util.List;





import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.vvxc.bos.bean.User;
import com.vvxc.bos.service.IUserService;
import com.vvxc.bos.util.MD5Utils;
import com.vvxc.bos.web.action.base.BaseAction;
import com.vvxc.crm.domain.Customer;
import com.vvxc.crm.service.CustomerService;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User>{
	private String checkcode;
	
	private String[] roleIds;
	
	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
	}

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

	public String login() {
		
		String key=(String) ServletActionContext.getRequest().getSession().getAttribute("key");
		
		if (StringUtils.isNotBlank(checkcode)) {
			if (checkcode.equals(key)) {
				Subject subject=SecurityUtils.getSubject();
				String password=MD5Utils.md5(getModel().getPassword());
				
				AuthenticationToken token=new UsernamePasswordToken(getModel().getUsername(), password);
				try {
					subject.login(token);
				} catch (Exception e) {
					this.addActionError("’À∫≈ªÚ’ﬂ√‹¬Î¥ÌŒÛ");
					return "login";
				}
				User user=(User) subject.getPrincipal();
				ServletActionContext.getRequest().getSession().setAttribute("loginUser", user);
				return "home";
			}
			this.addActionError("—È÷§¬Î¥ÌŒÛ");
			return "login";
		}
		this.addActionError("«Î ‰»Î—È÷§¬Î");
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
	
	public String  pageQuery() throws IOException {
		userService.pageQuery(pageBean);
		writePageBean2Json(new String[]{"roles","noticebills"});
		
		return NONE;
		
	}
	
	public String add() {
		userService.save(getModel(),roleIds);
		
		return "list";
		
	}

}
