package com.vvxc.bos.shiro;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.vvxc.bos.bean.Function;
import com.vvxc.bos.bean.User;
import com.vvxc.bos.dao.IFunctionDao;
import com.vvxc.bos.dao.IUserDao;


public class BosRealm  extends AuthorizingRealm{
	
	@Autowired
	private IUserDao userDao;
	
	@Autowired 
	private IFunctionDao functionDao;
	
	//»œ÷§
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		UsernamePasswordToken upToken=(UsernamePasswordToken) token;
		String username=upToken.getUsername();
		
		User user=userDao.findByUsername(username);
		
		if (user!=null) {
			String password=user.getPassword();
			
			
			SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(user, password,this.getClass().getSimpleName());
			return info;
		}else{
			return null;
			
		}
	}
	
	
	// ⁄»®
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		// TODO Auto-generated method stub
		
		SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
		User user = (User) principal.getPrimaryPrincipal();
		List<Function> functions=null; 
		if (user.getId().equals("1")) {
			functions= functionDao.findAll();
		}else{
			functions=functionDao.findByUserid(user.getId());
		}


		if (functions!=null&&functions.size()>0) {
			for (Function function : functions) {
				info.addStringPermission(function.getCode());
			}
		}
		return info;
	}

	

}
