package com.vvxc.bos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.vvxc.bos.bean.User;
import com.vvxc.bos.dao.IUserDao;
import com.vvxc.bos.service.IUserService;
import com.vvxc.bos.util.MD5Utils;

@Service
@Transactional
public class UserServiceImpl implements IUserService{
	@Autowired
	private IUserDao userDao;
	
	@Override
	public User login(User model) {
		// TODO Auto-generated method stub
		String username=model.getUsername();
		String pw=model.getPassword();
		pw=MD5Utils.md5(pw);
		
		return userDao.findByUsernameAndPw(username,pw);
	}

	@Override
	public void updatePw(String password,String id) {
		// TODO Auto-generated method stub
		userDao.excuteUpdate("updatePassword", password,id);
	}

}
