package com.vvxc.bos.service;

import java.util.List;

import com.vvxc.bos.bean.Staff;
import com.vvxc.bos.util.PageBean;


public interface IStaffService {
	void save(Staff staff);

	PageBean pageQuery(PageBean pageBean);

	void deleteBatch(String ids);

	Staff findById(String id);

	void update(Staff staff);

	List<Staff> findByQ(String q);

	List<Staff> findAll();

	List<Staff> findListNotDeleteByQ(String q);

	List<Staff> findListNotDelete();
}
