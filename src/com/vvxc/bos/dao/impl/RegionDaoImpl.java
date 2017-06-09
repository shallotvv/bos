package com.vvxc.bos.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.vvxc.bos.bean.Region;
import com.vvxc.bos.dao.IRegionDao;
import com.vvxc.bos.dao.base.impl.BaseDaoImpl;

@Repository
public class RegionDaoImpl extends BaseDaoImpl<Region>implements IRegionDao {

	@Override
	public List<Region> findByQ(String q) {
		// TODO Auto-generated method stub
		String hql="FROM Region WHERE province like ? or city like ? or district like ?";
		
		return (List<Region>) getHibernateTemplate().find(hql, "%"+q+"%","%"+q+"%","%"+q+"%");
	}

}
