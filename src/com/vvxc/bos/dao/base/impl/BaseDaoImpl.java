package com.vvxc.bos.dao.base.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.vvxc.bos.bean.Region;
import com.vvxc.bos.dao.base.IBaseDao;
import com.vvxc.bos.util.PageBean;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T>{
	private Class<T> entityClass;
	
	@Resource
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	
	public  BaseDaoImpl() {
		ParameterizedType type= (ParameterizedType) this.getClass().getGenericSuperclass();
		Type[] actualTypeArguments = type.getActualTypeArguments();
		entityClass=(Class<T>) actualTypeArguments[0];
	}

	@Override
	public void save(T entity) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(entity);
	}

	@Override
	public void delete(T entity) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(entity);
	}

	@Override
	public void update(T entity) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(entity);
		
	}

	@Override
	public T findById(Serializable entity) {
		// TODO Auto-generated method stub
		
		return getHibernateTemplate().get(entityClass, entity);
	}

	@Override
	public List<T> findAll() {
		// TODO Auto-generated method stub
		String hqlString="FROM "+entityClass.getSimpleName();
		
		return (List<T>) getHibernateTemplate().find(hqlString);
	}

	@Override
	public void excuteUpdate(String queryName, Object... objects) {
		// TODO Auto-generated method stub
		Session session=getSession();
		Query query=session.getNamedQuery(queryName);
		for (int i = 0; i < objects.length; i++) {
			query.setParameter(i, objects[i]);
		}
		
		query.executeUpdate();
		
		
	}

	public PageBean pageQuery(PageBean pageBean) {
		// TODO Auto-generated method stub
		int currentPage = pageBean.getCurrentPage();
		int pageSize = pageBean.getPageSize();
		
		DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();
		
		detachedCriteria.setProjection(Projections.rowCount());
		
		List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
		pageBean.setTotal(list.get(0).intValue());
		
		detachedCriteria.setProjection(null);
		detachedCriteria.setResultTransformer(DetachedCriteria.ROOT_ENTITY);
		
		int firstResult=(currentPage-1)*pageSize;
		int maxResult=pageSize;
		List rows = this.getHibernateTemplate().findByCriteria(detachedCriteria, firstResult, maxResult);

		pageBean.setRows(rows);
		
		return pageBean;
		
	}

	public void saveOrUpdate(T entity) {
		// TODO Auto-generated method stub
		getHibernateTemplate().saveOrUpdate(entity);
	}

}
