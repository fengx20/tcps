package com.gxuwz.fx.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gxuwz.fx.service.YzmService;

@RestController
public class YzmController {

	@Autowired YzmService ys;

	/**
	 * 向手机发送验证码
	 * @param phonenum
	 * @return
	 * @throws Exception
	 */
	 @PostMapping("/sendyzm/{phonenum}")
	 public String sendyzm(@PathVariable("phonenum")  String phonenum) throws Exception {
		 if( ys.sendYzm(phonenum).equals("200")) {   //发送验证码
			 return "success";
		 }else {
			 return "failed";
		 }
	 }



}
