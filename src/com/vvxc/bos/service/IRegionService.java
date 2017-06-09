package com.vvxc.bos.service;

import java.util.List;

import com.vvxc.bos.bean.Region;
import com.vvxc.bos.util.PageBean;

public interface IRegionService {

	void saveBatch(List<Region> regions);

	PageBean pageQuery(PageBean pageBean);

	List<Region> findAll();

	List<Region> findByQ(String q);
}
