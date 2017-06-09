package com.vvxc.bos.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import com.vvxc.bos.bean.Role;
import com.vvxc.bos.dao.IRoleDao;
import com.vvxc.bos.dao.base.impl.BaseDaoImpl;
import com.vvxc.bos.util.PageBean;

@Repository
public class RoleDaoImpl extends BaseDaoImpl<Role> implements IRoleDao {


}
