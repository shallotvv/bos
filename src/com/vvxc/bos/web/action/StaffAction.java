package com.vvxc.bos.web.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

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
		
		JSONObject jsonObject=JSONObject.fromObject(pageBean);
		String jsonString=jsonObject.toString();
			
		ServletActionContext.getResponse().getWriter().print(jsonString);
		
		return NONE;
		
	}
	
}
