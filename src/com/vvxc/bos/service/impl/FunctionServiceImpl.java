package com.vvxc.bos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vvxc.bos.bean.Function;
import com.vvxc.bos.dao.IFunctionDao;
import com.vvxc.bos.service.IFunctionService;
import com.vvxc.bos.util.PageBean;

@Service
@Transactional
public class FunctionServiceImpl implements IFunctionService {

	@Autowired
	private IFunctionDao functionDao;

	@Override
	public void pageQuery(PageBean pageBean) {
		// TODO Auto-generated method stub
		functionDao.pageQuery(pageBean);
		
	}

	@Override
	public List<Function> findAll() {
		// TODO Auto-generated method stub
		
		return functionDao.findAll();
	}

	@Override
	public void save(Function model) {
		// TODO Auto-generated method stub
		if (model.getFunction()!=null&&model.getFunction().getId().equals("")) {
			model.setFunction(null);
		}
		functionDao.save(model);
	}

	@Override
	public List<Function> findAllMenu() {
		// TODO Auto-generated method stub
		
		return functionDao.findAllMenu();
	}

	@Override
	public List<Function> findMenuById(String string) {
		// TODO Auto-generated method stub
		return functionDao.findMenuById(string);
	}

}
