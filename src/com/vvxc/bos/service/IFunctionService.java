package com.vvxc.bos.service;

import java.util.List;

import com.vvxc.bos.bean.Function;
import com.vvxc.bos.util.PageBean;

public interface IFunctionService {

	void pageQuery(PageBean pageBean);

	List<Function> findAll();

	void save(Function model);

	List<Function> findAllMenu();

	List<Function> findMenuById(String string);

}
