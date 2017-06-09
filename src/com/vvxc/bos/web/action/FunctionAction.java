package com.vvxc.bos.web.action;

import java.io.IOException;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vvxc.bos.bean.Function;
import com.vvxc.bos.bean.User;
import com.vvxc.bos.util.BosContext;
import com.vvxc.bos.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class FunctionAction extends BaseAction<Function> {

	public String  pageQuery() throws IOException {
		pageBean.setCurrentPage(Integer.valueOf(getModel().getPage()));
		
		functionService.pageQuery(pageBean);
		
		writePageBean2Json(new String[]{"functions","function","roles"});
		return NONE;
		
	}
	
	
	public String listAjax() throws IOException{
		List<Function> functions=functionService.findAll();
		writeList2Json(functions, new String[]{"functions","function","roles"});
		
		return NONE;
		
	}
	
	public String  add() {
		functionService.save(getModel());
		
		return "list";
		
	}
	
	public String  findMenu() throws IOException {
		User user=BosContext.getCurrentUser();
		List<Function> functions;
		if (user.getId().equals("1")) {
			functions=functionService.findAllMenu();
		}else{
			functions=functionService.findMenuById(user.getId());
		}
		
		writeList2Json(functions, new String[]{"functions","function","roles"});
		return NONE;
		
	}
}
