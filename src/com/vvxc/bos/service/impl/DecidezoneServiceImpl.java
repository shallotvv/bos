package com.vvxc.bos.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vvxc.bos.bean.Decidedzone;
import com.vvxc.bos.bean.Subarea;
import com.vvxc.bos.dao.IDecidezoneDao;
import com.vvxc.bos.dao.ISubareaDao;
import com.vvxc.bos.service.IDecidezoneService;
import com.vvxc.bos.util.PageBean;

@Transactional
@Service
public class DecidezoneServiceImpl  implements IDecidezoneService{
	
	@Resource
	private ISubareaDao subareaDao;
	
	
	@Resource
	private IDecidezoneDao decidezoneDao;
	@Override
	public void save(Decidedzone model, String[] subareaId) {
		// TODO Auto-generated method stub
		decidezoneDao.save(model);
		for (String subareaid : subareaId) {
			Subarea subarea = subareaDao.findById(subareaid);
			subarea.setDecidedzone(model);
		}
		
	}
	@Override
	public PageBean pageQuery(PageBean pageBean) {
		// TODO Auto-generated method stub
		return decidezoneDao.pageQuery(pageBean);
	}

}
