package com.gxuwz.fx.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {
    
	/**
	 * 管理员登录
	 * @param admin
	 * @param password
	 * @return
	 */
	public boolean adminlogin(String admin,String password);
	
}
