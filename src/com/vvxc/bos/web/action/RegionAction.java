package com.vvxc.bos.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vvxc.bos.bean.Region;
import com.vvxc.bos.service.IRegionService;
import com.vvxc.bos.web.action.base.BaseAction;


@Controller
@Scope("prototype")
public class RegionAction extends BaseAction<Region>{
	private File myFile;
	
	@Autowired
	private IRegionService regionService;
	
	
	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}


	public String importXls() throws FileNotFoundException, IOException {
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
//
//			public Region(String id, String province, String city, String district,
//					String postcode, String shortcode, String citycode, Set bcSubareas) 
			
			Region region=new Region(id,province,city,district,postcode,null,null,null);
			regions.add(region);
		}
		
		regionService.saveBatch(regions);
		return "list";
		
	}

}
