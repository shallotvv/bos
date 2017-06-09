package com.vvxc.bos.web.action.base;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.vvxc.bos.bean.Region;
import com.vvxc.bos.bean.Staff;
import com.vvxc.bos.service.IDecidezoneService;
import com.vvxc.bos.service.IFunctionService;
import com.vvxc.bos.service.INoticeBillService;
import com.vvxc.bos.service.IRegionService;
import com.vvxc.bos.service.IRoleService;
import com.vvxc.bos.service.IStaffService;
import com.vvxc.bos.service.ISubareaService;
import com.vvxc.bos.service.IUserService;
import com.vvxc.bos.service.IWorkordermanagerService;
import com.vvxc.bos.util.PageBean;
import com.vvxc.crm.service.CustomerService;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

	@Autowired
	protected IDecidezoneService decidezoneService;
	@Autowired
	protected IRegionService regionService;
	@Autowired
	protected IStaffService staffService;
	@Autowired
	protected ISubareaService subareaService;
	@Autowired
	protected IUserService userService;
	@Autowired
	protected CustomerService customerService;
	@Autowired
	protected INoticeBillService noticeBillService;
	@Autowired
	protected IWorkordermanagerService workordermanagerService;
	@Autowired
	protected IFunctionService functionService;
	@Autowired
	protected IRoleService roleService;
	
	protected PageBean pageBean=new PageBean();
	
	public void setPage(int page) {
		pageBean.setCurrentPage(page);
	}


	public void setRows(int rows) {
		pageBean.setPageSize(rows);
		
	}

	private T model;
	@Override
	public T getModel() {
		// TODO Auto-generated method stub
		return model;
	}

	public BaseAction() {
		// TODO Auto-generated constructor stub
		ParameterizedType parameterizedType=(ParameterizedType) this.getClass().getGenericSuperclass();
		Type[] type=parameterizedType.getActualTypeArguments();
		Class<T> entityClass=(Class<T>) type[0];
		

		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(entityClass);
		pageBean.setDetachedCriteria(detachedCriteria);
		
		
		try {
			model=entityClass.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void writePageBean2Json(String[] excludeString) throws IOException {

		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.setExcludes(excludeString);
		JSONObject jsonObject=JSONObject.fromObject(pageBean,jsonConfig);
		String jsonString=jsonObject.toString();
			
		ServletActionContext.getResponse().getWriter().print(jsonString);
	}

	public void writeObject2Json(Object object,String[] excludeString) throws IOException {

		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.setExcludes(excludeString);
		JSONObject jsonObject=JSONObject.fromObject(object,jsonConfig);
		String jsonString=jsonObject.toString();
			
		ServletActionContext.getResponse().getWriter().print(jsonString);
	}
	


	public void writeList2Json(List regions, String[] excludeStrings) throws IOException {
		// TODO Auto-generated method stub

		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.setExcludes(excludeStrings);
		JSONArray jsonArray=JSONArray.fromObject(regions,jsonConfig);
		String jsonString=jsonArray.toString();
			
		ServletActionContext.getResponse().getWriter().print(jsonString);
	}
}
