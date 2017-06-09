package com.vvxc.bos.web.action;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vvxc.bos.bean.Noticebill;
import com.vvxc.bos.service.INoticeBillService;
import com.vvxc.bos.util.BosContext;
import com.vvxc.bos.web.action.base.BaseAction;
import com.vvxc.crm.domain.Customer;

@Controller
@Scope("prototype")
public class NoticebillAction extends BaseAction<Noticebill>{
	
	
	private String decidedzone_id;
	
	public String getDecidedzone_id() {
		return decidedzone_id;
	}

	public void setDecidedzone_id(String decidedzone_id) {
		this.decidedzone_id = decidedzone_id;
	}

	public String  findCustomerByTelephone() throws IOException {
		Customer customer = customerService.findCustomerByPhonenumber(getModel().getTelephone());
		
		writeObject2Json(customer, new String[]{});
		return NONE;
		
	}
	
	public String  add() {
		getModel().setUser(BosContext.getCurrentUser());
		
		System.out.print(getModel().getUser().getUsername());
		noticeBillService.save(getModel(),decidedzone_id);
		return "list";
		
	}
}
