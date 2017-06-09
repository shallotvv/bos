package com.vvxc.bos.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vvxc.bos.bean.Function;
import com.vvxc.bos.bean.Role;
import com.vvxc.bos.dao.IRoleDao;
import com.vvxc.bos.service.IRoleService;
import com.vvxc.bos.util.PageBean;

@Service 
@Transactional
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private IRoleDao roleDao;
	
	
	@Override
	public void save(Role role, String ids) {
		// TODO Auto-generated method stub
			roleDao.save(role);
			
			if (StringUtils.isNotBlank(ids)) {

				String[] fids=ids.split(",");
				
				for (String fid : fids) {
					Function function = new Function();
					function.setId(fid);
					role.getFunctions().add(function);
				}
			}
	}


	@Override
	public void pageQuery(PageBean pageBean) {
		// TODO Auto-generated method stub
		roleDao.pageQuery(pageBean);
	}


	@Override
	public List<Role> findAll() {
		
		
		// TODO Auto-generated method stub
		return roleDao.findAll();
	}
	

}
