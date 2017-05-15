package com.vvxc.bos.web.action.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
	private T model;
	@Override
	public T getModel() {
		// TODO Auto-generated method stub
		return model;
	}

	public BaseAction() {
		// TODO Auto-generated constructor stub
		ParameterizedType parameterizedType=(ParameterizedType) this.getClass().getGenericSuperclass();
		Type[] type=parameterizedType.getActualTypeArguments();
		Class<T> entityClass=(Class<T>) type[0];
		try {
			model=entityClass.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
