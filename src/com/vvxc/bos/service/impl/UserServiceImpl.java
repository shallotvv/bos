package com.vvxc.bos.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.vvxc.bos.bean.Role;
import com.vvxc.bos.bean.User;
import com.vvxc.bos.dao.IUserDao;
import com.vvxc.bos.service.IUserService;
import com.vvxc.bos.util.MD5Utils;
import com.vvxc.bos.util.PageBean;

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

	@Override
	public void pageQuery(PageBean pageBean) {
		// TODO Auto-generated method stub
		userDao.pageQuery(pageBean);
	}

	@Override
	public void save(User model, String[] roleIds) {
		// TODO Auto-generated method stub
		
		userDao.save(model);
		
		String pw=model.getPassword();
		model.setPassword(MD5Utils.md5(pw));

		if (roleIds!=null) {
			for (String string : roleIds) {
				
				Role role = new Role(string);
				model.getRoles().add(role);
			}
		}
	}

}
