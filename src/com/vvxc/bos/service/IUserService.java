package com.vvxc.bos.service;

import org.springframework.ui.Model;

import com.vvxc.bos.bean.User;
import com.vvxc.bos.util.PageBean;

public interface IUserService {
	User login(User model);

	void updatePw(String password,String id);

	void pageQuery(PageBean pageBean);

	void save(User model, String[] roleIds);
}
