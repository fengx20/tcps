package com.gxuwz.fx.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gxuwz.fx.pojo.Admin;
import com.gxuwz.fx.service.AdminService;

@RestController
public class AdminController {

	@Autowired AdminService as;

	/**
	 * 管理员--登录
	 * @param admin
	 * @param
	 * @return
	 */
	@PostMapping("/adminlogin")
	public String adminlogin(@RequestBody Admin admin) throws Exception {
		if(as.adminlogin(admin.getAdmin(), admin.getPassword()) == true){
			return "success";
		}else {
			return "failed";
		}
	}

}
