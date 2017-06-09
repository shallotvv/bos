package com.vvxc.bos.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.catalina.connector.Response;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vvxc.bos.bean.Staff;
import com.vvxc.bos.service.IStaffService;
import com.vvxc.bos.util.PageBean;
import com.vvxc.bos.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff>{
	
	private String  q; //模糊查询条件
	private String ids;
	
	public String getQ() {
		return q;
	}


	public void setQ(String q) {
		this.q = q;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}


	public String  add() {
		try {
			staffService.save(getModel());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "list";
		
	}
	
	public String pageQuery() throws IOException {
	    pageBean=staffService.pageQuery(pageBean);

	    this.writePageBean2Json(new String[]{"currentPage","detachedCriteria","pageSize","decidedzones"});
		
		return NONE;
		
	}
	

	@RequiresPermissions(value="stff")
	public String  delete() {
		staffService.deleteBatch(ids);
		
		return "list";
		
		
	}

	public String  edit() {
		Staff staff=staffService.findById(getModel().getId());
		
		staff.setName(getModel().getName());
		staff.setHaspda(getModel().getHaspda());
		staff.setTelephone(getModel().getTelephone());
		staff.setStation(getModel().getStation());
		staff.setStandard(getModel().getStandard());
		
		staffService.update(staff);
		
		return "list";
		
		
	}
	
	public String listajax() throws IOException{
		List<Staff> staffs=null;
		
		if (StringUtils.isNotBlank(q)) {
			staffs=staffService.findListNotDeleteByQ(q);
		}else{
			staffs=staffService.findListNotDelete();
		}
		writeList2Json(staffs, new String[]{"decidedzones"});
		
		return NONE;
	}
	
}
