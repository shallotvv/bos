package com.vvxc.bos.dao;

import com.vvxc.bos.bean.User;
import com.vvxc.bos.dao.base.IBaseDao;

public interface IUserDao extends IBaseDao<User> {

	User findByUsernameAndPw(String username, String pw);

}
