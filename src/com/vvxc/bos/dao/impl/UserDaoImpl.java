package com.vvxc.bos.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.vvxc.bos.bean.User;
import com.vvxc.bos.dao.IUserDao;
import com.vvxc.bos.dao.base.impl.BaseDaoImpl;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao {

	@Override
	public User findByUsernameAndPw(String username, String pw) {
		// TODO Auto-generated method stub
		
		String hql="FROM User u where u.username=? AND u.password=?";
		
		List<User> list=(List<User>) getHibernateTemplate().find(hql,username,pw);
		
		if (list!=null&&list.size()>0) {
			return list.get(0);
		}
		
		return null;
	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub

		String hql="FROM User u where u.username=? ";
		
		List<User> list=(List<User>) getHibernateTemplate().find(hql,username);
		
		if (list!=null&&list.size()>0) {
			return list.get(0);
		}
		
		return null;
		
	}

	
}
