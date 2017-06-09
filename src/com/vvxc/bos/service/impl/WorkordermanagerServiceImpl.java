package com.vvxc.bos.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vvxc.bos.bean.Workordermanage;
import com.vvxc.bos.dao.IWorkordermanagerDao;
import com.vvxc.bos.service.IWorkordermanagerService;
@Service
@Transactional
public class WorkordermanagerServiceImpl implements IWorkordermanagerService{
	@Autowired
	private IWorkordermanagerDao workordermanagerDao;
	@Override
	public void save(Workordermanage model) {
		// TODO Auto-generated method stub
		model.setUpdatetime(new Date());
		workordermanagerDao.save(model);
		
	}

}
