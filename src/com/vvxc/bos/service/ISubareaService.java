package com.vvxc.bos.service;

import java.util.List;

import com.vvxc.bos.bean.Subarea;
import com.vvxc.bos.util.PageBean;
import com.vvxc.bos.web.action.SubareaAction;

public interface ISubareaService {

	void save(Subarea subarea);

	PageBean pageQuery(PageBean pageBean);

	List<Subarea> findAll();

	List<Subarea> findListNotAssociation();

}
