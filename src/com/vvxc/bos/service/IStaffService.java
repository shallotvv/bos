package com.vvxc.bos.service;

import com.vvxc.bos.bean.Staff;
import com.vvxc.bos.util.PageBean;


public interface IStaffService {
	void save(Staff staff);

	PageBean pageQuery(PageBean pageBean);
}
