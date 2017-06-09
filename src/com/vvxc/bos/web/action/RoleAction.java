package com.vvxc.bos.web.action;

import java.io.IOException;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vvxc.bos.bean.Role;
import com.vvxc.bos.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role>{
	private String ids;

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
	
	public String add() {
		roleService.save(getModel(), ids);
		
		return "list";
		
	}
	
	public String  pageQuery() throws IOException {
		roleService.pageQuery(pageBean);
		writePageBean2Json(new String[]{"functions","users"}); 
		
		return NONE;
		
	}
	
	public String listAjax() throws IOException {
		List<Role> roles=roleService.findAll();
		writeList2Json(roles, new String[]{"functions","users"});
		
		return NONE;
		
	}

}
