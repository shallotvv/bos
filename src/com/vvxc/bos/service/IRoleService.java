package com.vvxc.bos.service;

import java.util.List;

import com.vvxc.bos.bean.Role;
import com.vvxc.bos.util.PageBean;

public interface IRoleService {
	void save(Role role,String ids);

	void pageQuery(PageBean pageBean);

	List<Role> findAll();

}
