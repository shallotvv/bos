package com.vvxc.bos.web.action;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vvxc.bos.bean.Decidedzone;
import com.vvxc.bos.service.IDecidezoneService; 
import com.vvxc.bos.web.action.base.BaseAction;
import com.vvxc.crm.domain.Customer;

@Controller
@Scope("prototype")
public class DecidezoneAction  extends BaseAction<Decidedzone>{
	private String[] subareaId;
	
	private Integer[] customerIds;
	
	public Integer[] getCustomerIds() {
		return customerIds;
	}

	public void setCustomerIds(Integer[] customerIds) {
		this.customerIds = customerIds;
	}

	public String[] getSubareaId() {
		return subareaId;
	}

	public void setSubareaId(String[] subareaId) {
		this.subareaId = subareaId;
	}

	public String  add() {
		decidezoneService.save(getModel(),subareaId);
		return "list";
		
	}
	
	public String pageQuery() throws IOException{
		decidezoneService.pageQuery(pageBean);
		writePageBean2Json(new String[]{"subareas","decidedzones","currentPage","pageSize","detachedCriteria"});
		return NONE;
		
	}
	
	
	public String  findnoassociationCustomers() throws IOException {
		List<Customer> customers = customerService.findnoassociationCustomers();
		writeList2Json(customers, new String[]{});
		return NONE;
	}

	public String  findhasassociationCustomers() throws IOException {
		List<Customer> customers = customerService.findhasassociationCustomers(getModel().getId());
		writeList2Json(customers, new String[]{});
		return NONE;
	}
	
	public String  assigncustomerstodecidedzone() {
		customerService.assignCustomersToDecidedZone(customerIds, getModel().getId());
		
		return "list";
		
	}
}
