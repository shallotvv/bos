package com.vvxc.bos.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.org.apache.regexp.internal.recompile;
import com.vvxc.bos.bean.Subarea;
import com.vvxc.bos.dao.ISubareaDao;
import com.vvxc.bos.service.ISubareaService;
import com.vvxc.bos.util.PageBean;
import com.vvxc.bos.web.action.SubareaAction;


@Service
@Transactional
public class SubareaServiceImpl implements ISubareaService {
	
	@Resource
	private ISubareaDao subareaDao;

	@Override
	public void save(Subarea subarea) {
		// TODO Auto-generated method stub
		subareaDao.save(subarea);
		
	}

	@Override
	public PageBean pageQuery(PageBean pageBean) {
		// TODO Auto-generated method stub
		return subareaDao.pageQuery(pageBean);
		
	}

	@Override
	public List<Subarea> findAll() {
		// TODO Auto-generated method stub
		
		
		return subareaDao.findAll();
	}

	@Override
	public List findListNotAssociation() {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(Subarea.class);
		detachedCriteria.add(Restrictions.isNull("decidedzone"));
		
		return subareaDao.findByCriteria(detachedCriteria);
		
	}


}
