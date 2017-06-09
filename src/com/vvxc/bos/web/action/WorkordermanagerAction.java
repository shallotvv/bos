package com.vvxc.bos.web.action;

import java.io.IOException;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vvxc.bos.bean.Workbill;
import com.vvxc.bos.bean.Workordermanage;
import com.vvxc.bos.util.BosContext;
import com.vvxc.bos.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class WorkordermanagerAction extends BaseAction<Workordermanage>{

	public String  add() throws IOException {
		String flag="1";
		try{
			workordermanagerService.save(getModel());
		}catch(Exception e){
			e.printStackTrace();
			flag="0";
		}
		ServletActionContext.getResponse().getWriter().print(flag);
		return NONE;
	}
	
}
