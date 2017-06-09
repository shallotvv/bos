package com.vvxc.bos.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.vvxc.bos.bean.Staff;
import com.vvxc.bos.dao.IStaffDao;
import com.vvxc.bos.dao.base.impl.BaseDaoImpl;
import com.vvxc.bos.util.PageBean;

@Repository
public class StaffDaoImpl extends BaseDaoImpl<Staff> implements IStaffDao{

	@Override
	public List<Staff> findByQ(String q) {
		// TODO Auto-generated method stub
		String hqlString="FROM Staff WHERE name like ?";
		
		return (List<Staff>) getHibernateTemplate().find(hqlString,"%"+q+"%");
	}

	@Override
	public List<Staff> findListNotDeleteByQ(String q) {
		// TODO Auto-generated method stub
		String hqlString="FROM Staff WHERE deltag =0 and name like ? ";
		return (List<Staff>)getHibernateTemplate().find(hqlString,"%"+q+"%");
	}

	@Override
	public List<Staff> findListNotDelete() {
		// TODO Auto-generated method stub
		String hqlString="FROM Staff WHERE deltag =0 ";
		return (List<Staff>) getHibernateTemplate().find(hqlString);
	}

}
