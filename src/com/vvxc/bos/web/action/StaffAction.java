package com.vvxc.bos.web.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.catalina.connector.Response;
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
	@Autowired
	private IStaffService staffService;
	
	private int page;
	private int rows; 
	
	private String ids;

	public void setIds(String ids) {
		this.ids = ids;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setRows(int rows) {
		this.rows = rows;
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
		PageBean pageBean=new PageBean();
		pageBean.setCurrentPage(page);
		pageBean.setPageSize(rows);
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(Staff.class);
		
		pageBean.setDetachedCriteria(detachedCriteria);
		
	    pageBean=staffService.pageQuery(pageBean);
		
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.setExcludes(new String[]{"currentPage","detachedCriteria","pageSize"});
		JSONObject jsonObject=JSONObject.fromObject(pageBean,jsonConfig);
		String jsonString=jsonObject.toString();
			
		ServletActionContext.getResponse().getWriter().print(jsonString);
		
		return NONE;
		
	}
	

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
	
}
