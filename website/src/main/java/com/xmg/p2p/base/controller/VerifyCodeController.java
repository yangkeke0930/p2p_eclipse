 package com.xmg.p2p.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmg.p2p.base.service.IVerifyCodeService;
import com.xmg.p2p.base.util.JSONResult;

/**
 * 验证码相关的controller
 * @author 78158
 *
 */
@Controller
public class VerifyCodeController {
	
	//添加依赖
	@Autowired
	IVerifyCodeService verifyCodeService;
	
	@RequestMapping("sendVerifyCode")
	@ResponseBody
	public JSONResult sendVerifyCode(String phoneNumber){
		
		/*
		 * 抛出发短信发不出的异常
		 */
		JSONResult json = new JSONResult();
		try{
			verifyCodeService.sendVerifyCode(phoneNumber);
		}catch(RuntimeException e){	
			json.setMsg(e.getMessage());
			json.setSuccess(false);
		}
	 
		return  json;
	}
}
