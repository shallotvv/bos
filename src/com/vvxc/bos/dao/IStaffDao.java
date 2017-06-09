package com.vvxc.bos.dao;

import java.util.List;

import com.vvxc.bos.bean.Staff;
import com.vvxc.bos.dao.base.IBaseDao;
import com.vvxc.bos.util.PageBean;

public interface IStaffDao extends IBaseDao<Staff> {

	List<Staff> findByQ(String q);

	List<Staff> findListNotDeleteByQ(String q);

	List<Staff> findListNotDelete();


}
