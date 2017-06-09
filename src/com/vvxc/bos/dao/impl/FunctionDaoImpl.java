package com.vvxc.bos.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import com.vvxc.bos.bean.Function;
import com.vvxc.bos.dao.IFunctionDao;
import com.vvxc.bos.dao.base.impl.BaseDaoImpl;
import com.vvxc.bos.util.PageBean;

@Repository
public class FunctionDaoImpl extends BaseDaoImpl<Function> implements IFunctionDao {

	@Override
	public List<Function> findByUserid(String id) {
		// TODO Auto-generated method stub
		
		String hql="SELECT DISTINCT f FROM Function f LEFT OUTER JOIN f.roles  r LEFT OUTER JOIN r.users u WHERE  u.id=?";
		
		return (List<Function>) getHibernateTemplate().find(hql,id);
	}

	@Override
	public List<Function> findAllMenu() {
		// TODO Auto-generated method stub
		String hql="FROM Function f Where f.generatemenu = '1' ORDER BY f.zindex DESC";
		return (List<Function>) this.getHibernateTemplate().find(hql);
	}

	@Override
	public List<Function> findMenuById(String string) {
		// TODO Auto-generated method stub
		
		String hql="SELECT DISTINCT f FROM Function f LEFT OUTER JOIN f.roles  r LEFT OUTER JOIN r.users u WHERE  u.id=?"
				+ "AND f.generatemenu = '1' ORDER BY f.zindex DESC";
		return (List<Function>) this.getHibernateTemplate().find(hql);
	}


}
