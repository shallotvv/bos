package com.vvxc.bos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vvxc.bos.bean.Region;
import com.vvxc.bos.dao.IRegionDao;
import com.vvxc.bos.service.IRegionService;

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

}
