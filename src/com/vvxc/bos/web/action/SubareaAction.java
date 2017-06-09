package com.vvxc.bos.web.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.engine.TypedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vvxc.bos.bean.Region;
import com.vvxc.bos.bean.Subarea;
import com.vvxc.bos.service.ISubareaService;
import com.vvxc.bos.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class SubareaAction extends BaseAction<Subarea> {
	
	public String add() {
		subareaService.save(getModel());
		
		return "list";
	}
	
	public String pageQuery() throws IOException {
		
		String addressKey=getModel().getAddresskey();
		DetachedCriteria detachedCriteria=pageBean.getDetachedCriteria();
		Region region=getModel().getRegion();
		
		if (StringUtils.isNotBlank(addressKey)) {
			detachedCriteria.add(Restrictions.like("addresskey","%"+addressKey+"%"));
			
		}
		
		if (region!=null) {
			detachedCriteria.createAlias("region", "r");
			
			String province=region.getProvince();
			String city=region.getCity();
			String district=region.getDistrict();
			if (StringUtils.isNotBlank(province)) {
				detachedCriteria.add(Restrictions.like("r.province", "%"+province+"%"));
			}

			if (StringUtils.isNotBlank(city)) {
				detachedCriteria.add(Restrictions.like("r.city", "%"+city+"%"));
			}

			if (StringUtils.isNotBlank(district)) {
				detachedCriteria.add(Restrictions.like("r.district", "%"+district+"%"));
			}
			
		}
		
		pageBean=subareaService.pageQuery(pageBean);
		writePageBean2Json(new String[]{"subareas","currentPage","detachedCriteria","pageSize","decidedzones"});
		return NONE;
	}
	
	public String  exportXls() throws IOException {
		
		List<Subarea> subareas = subareaService.findAll();
		HSSFWorkbook hssfWorkbook =new HSSFWorkbook();
		HSSFSheet hssfSheet=hssfWorkbook.createSheet();
		
		HSSFRow row=hssfSheet.createRow(0);
		row.createCell(0).setCellValue("分区编号");
		row.createCell(1).setCellValue("区域编号");
		row.createCell(2).setCellValue("关键字");
		row.createCell(3).setCellValue("省市区");
		
		for (Subarea subarea :subareas) {
			HSSFRow row2=hssfSheet.createRow(hssfSheet.getLastRowNum()+1);

			row2.createCell(0).setCellValue(subarea.getId());
			
			Region region=subarea.getRegion();
			row2.createCell(1).setCellValue(region.getId());
			row2.createCell(2).setCellValue(subarea.getAddresskey());
			row2.createCell(3).setCellValue(region.getProvince()+region.getCity()+region.getDistrict());
		}
		
		ServletOutputStream outputStream = ServletActionContext.getResponse().getOutputStream();
		String fileName="abc.xls";
		String type=ServletActionContext.getServletContext().getMimeType(fileName);
		ServletActionContext.getResponse().setContentType(type);
		ServletActionContext.getResponse().setHeader("content-disposition", "attachment;filename="+fileName);
		
		hssfWorkbook.write(outputStream);
		
		return NONE;
		
	}
	
	public String  listajax() throws IOException {
		
		List<Subarea> subareas = subareaService.findListNotAssociation();
		writeList2Json(subareas, new String[]{"subareas"});
		return NONE;
		
	}

}
