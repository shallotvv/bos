package com.vvxc.bos.dao;

import java.util.List;

import com.vvxc.bos.bean.Function;
import com.vvxc.bos.dao.base.IBaseDao;

public interface IFunctionDao extends IBaseDao<Function>{

	List<Function> findByUserid(String id);

	List<Function> findAllMenu();

	List<Function> findMenuById(String string);

}
