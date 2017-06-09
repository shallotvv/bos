package com.vvxc.bos.service;

import com.vvxc.bos.bean.Decidedzone;
import com.vvxc.bos.util.PageBean;

public interface IDecidezoneService {

	void save(Decidedzone model, String[] subareaId);

	PageBean pageQuery(PageBean pageBean);

}
