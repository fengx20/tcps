package com.gxuwz.fx.service.impl;

import java.io.IOException;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gxuwz.fx.mapper.YzmMapper;
import com.gxuwz.fx.pojo.Yzm;
import com.gxuwz.fx.service.YzmService;
import com.shuyuanwl.sms.api.bean.DownRes;
import com.shuyuanwl.sms.api.core.ApiSender;

@Service
public class YzmServiceImpl implements YzmService {
	
	@Autowired YzmMapper ym;
	
	/**
	 * 1向手机发送验证码并保存
	 * @param Phonenum
	 * @return
	 * @throws IOException
	 */
	@Override
	public String sendYzm(String Phonenum) throws IOException {
		String result="";
		try {
			//生成6位验证码
		    String yzmcode = String.valueOf(new Random().nextInt(899999) + 100000);	
		    String url = "http://api.shuyuanwl.com:8080/api/sms/send";
			String mobiles = Phonenum; //号码
			String content = "【Worker】您的验证码是："+yzmcode;//短信内容
			String account = "wzdskj@wzdskj"; //账号
			String extno = "01"; //扩展码
			String password = "USLd7Y8p";//密码
			String batchno = "";//批次号
			DownRes res = ApiSender.send(url, account, password, mobiles, content, extno,batchno);
			System.out.println(res);
			String code = res.getCode();   //获得code的值
			System.out.println(code); 
			
			Yzm yzm = new Yzm();
			yzm.setPhonenum(Phonenum);
			yzm.setCode(yzmcode);
			
			if(code.equals("200")) {
			    ym.add(yzm);
			}
            result = code;
			
		}catch (Exception e) {
	        e.printStackTrace();
	     }
		return result;
	}
	
	/**
	 * 2验证手机号及验证码
	 * @param phonenum
	 * @param code
	 * @return
	 */
	@Override
    public boolean verification(String phonenum,String code) {
		return ym.existByYzm(phonenum, code);
	}
	
	
	

}
