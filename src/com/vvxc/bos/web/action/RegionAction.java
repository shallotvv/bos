package com.vvxc.bos.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vvxc.bos.bean.Region;
import com.vvxc.bos.service.IRegionService;
import com.vvxc.bos.util.PageBean;
import com.vvxc.bos.util.PinYin4jUtils;
import com.vvxc.bos.web.action.base.BaseAction;


@Controller
@Scope("prototype")
public class RegionAction extends BaseAction<Region>{
	private File myFile;
	
	private String q;//Ä£ºý²éÑ¯ÇøÓò

	public void setQ(String q) {
		this.q = q;
	}

	public String getQ() {
		return q;
	}

	
	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}


	public String importXls() throws FileNotFoundException, IOException {
		
		String flag="1";
		try{
			HSSFWorkbook hssfWorkbook=new HSSFWorkbook(new FileInputStream(myFile));
			HSSFSheet sheet=hssfWorkbook.getSheetAt(0);
			List<Region> regions=new ArrayList<Region>();
			
			for (Row row : sheet) {
				int rownum=row.getRowNum();
				if (rownum==0) {
					continue;
				}
				String id = row.getCell(0).getStringCellValue();
				String province = row.getCell(1).getStringCellValue();
				String city = row.getCell(2).getStringCellValue();
				String district = row.getCell(3).getStringCellValue();
				String postcode = row.getCell(4).getStringCellValue();

				city=city.substring(0,city.length()-1);
				String[] stringToPinyin = PinYin4jUtils.stringToPinyin(city);
				String citycode=StringUtils.join(stringToPinyin, "");
	//
//				public Region(String id, String province, String city, String district,
//						String postcode, String shortcode, String citycode, Set bcSubareas) 
				
				province=province.substring(0,province.length()-1);
				district=district.substring(0,district.length()-1);
				String[] temp=PinYin4jUtils.getHeadByString(city+province+district);
				String shortcode=StringUtils.join(temp, "");
				
				Region region=new Region(id,province,city,district,postcode,shortcode,citycode,null);
				regions.add(region);
			}
			
			regionService.saveBatch(regions);
		}catch(Exception e){
			flag="0";
		}
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(flag);
		return NONE;
		
	}
	
	public String pageQuery() throws IOException {
		
		pageBean=regionService.pageQuery(pageBean);
		
		this.writePageBean2Json(new String[]{"currentPage","pageSize","detachedCriteria","subareas"});
		
		return NONE;
		
	}

	
	public String listajax() throws IOException {
		List<Region> regions =null;
		if (StringUtils.isNotBlank(q)) {
			regions=regionService.findByQ(q);
		}else {
			regions = regionService.findAll();
		}
		
		String[] excludeStrings=new String[]{"subareas"};
		writeList2Json(regions,excludeStrings);
		return NONE;
		
	}


}
