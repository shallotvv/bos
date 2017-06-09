package com.vvxc.bos.dao;

import java.util.List;

import com.vvxc.bos.bean.Region;
import com.vvxc.bos.dao.base.IBaseDao;

public interface IRegionDao extends IBaseDao<Region>{

	List<Region> findByQ(String q);

	
}
