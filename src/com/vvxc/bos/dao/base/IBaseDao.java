package com.vvxc.bos.dao.base;

import java.io.Serializable;
import java.util.List;

import com.vvxc.bos.bean.Region;
import com.vvxc.bos.util.PageBean;

public interface IBaseDao<T> {
	void save(T entity);
	void delete(T entity);
	void update(T entity);
	void saveOrUpdate(T entity);
	T findById(Serializable entity);
	List<T> findAll();

	PageBean pageQuery(PageBean pageBean);
	void excuteUpdate(String queryName,Object ...objects);
}

