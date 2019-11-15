package com.gxuwz.fx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gxuwz.fx.mapper.AdminMapper;
import com.gxuwz.fx.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired AdminMapper am;
	
	/**
	 * 管理员登录
	 * @param admin
	 * @param password
	 * @return
	 */
	@Override
	public boolean adminlogin(String admin,String password) {
		return am.adminlogin(admin, password);		
	}
	
	

}
