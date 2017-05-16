package com.vvxc.bos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vvxc.bos.bean.Staff;
import com.vvxc.bos.dao.IStaffDao;
import com.vvxc.bos.service.IStaffService;
import com.vvxc.bos.util.PageBean;
@Service
@Transactional
public class StaffServiceImpl implements IStaffService {

	@Autowired
	private IStaffDao staffDao;

	@Override
	public void save(Staff staff) {
		// TODO Auto-generated method stub
		staffDao.save(staff);
	}

	@Override
	public PageBean pageQuery(PageBean pageBean) {
		// TODO Auto-generated method stub
		return staffDao.pageQuery(pageBean);
	}

	@Override
	public void deleteBatch(String ids) {
		// TODO Auto-generated method stub
		String[] split = ids.split(",");
		
		for (String string : split) {
			staffDao.excuteUpdate("staff.delete", string);
		}
	}

	@Override
	public Staff findById(String id) {
		// TODO Auto-generated method stub
		Staff staff = staffDao.findById(id);
		
		return staff;
	}

	@Override
	public void update(Staff staff) {
		// TODO Auto-generated method stub
		staffDao.update(staff);
	}
	
	
	
}
