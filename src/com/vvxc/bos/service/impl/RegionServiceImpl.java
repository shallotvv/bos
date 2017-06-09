package com.vvxc.bos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vvxc.bos.bean.Region;
import com.vvxc.bos.dao.IRegionDao;
import com.vvxc.bos.service.IRegionService;
import com.vvxc.bos.util.PageBean;

@Transactional
@Service
public class RegionServiceImpl implements IRegionService{

	@Autowired
	private IRegionDao regionDao;
	@Override
	public void saveBatch(List<Region> regions) {
		// TODO Auto-generated method stub
		for (Region region : regions) {

			regionDao.saveOrUpdate(region);
		}
		
	}
	@Override
	public PageBean pageQuery(PageBean pageBean) {
		// TODO Auto-generated method stub
		return regionDao.pageQuery(pageBean);
	}
	@Override
	public List<Region> findAll() {
		// TODO Auto-generated method stub
		return regionDao.findAll();
		
	}
	@Override
	public List<Region> findByQ(String q) {
		// TODO Auto-generated method stub
		
		return regionDao.findByQ(q);
	}

}
