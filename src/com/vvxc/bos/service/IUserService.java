package com.vvxc.bos.service;

import org.springframework.ui.Model;

import com.vvxc.bos.bean.User;

public interface IUserService {
	User login(User model);

	void updatePw(String password,String id);
}
