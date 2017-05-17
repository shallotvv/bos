package com.vvxc.bos.service;

import java.util.List;

import com.vvxc.bos.bean.Region;

public interface IRegionService {

	void saveBatch(List<Region> regions);
}
