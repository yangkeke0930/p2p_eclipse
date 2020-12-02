package com.xmg.p2p.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmg.p2p.base.domain.Logininfo;
import com.xmg.p2p.base.service.IAccountService;
import com.xmg.p2p.base.service.IUserinfoService;
import com.xmg.p2p.base.util.JSONResult;
import com.xmg.p2p.base.util.RequireLogin;
import com.xmg.p2p.base.util.UserContext;

/**
 * 个人中心
 * @author 78158
 *
 */
@Controller
public class PersonalController {
	
	/**
	 * 往personal里面放东西
	 * @param model
	 * @return
	 */ 
	@Autowired
	private IUserinfoService userinfoService; 
	@Autowired
	private IAccountService accountService;
	
	@RequireLogin
	@RequestMapping("personal")
	public String personalCenter(Model model){
		//获取当前用户
		Logininfo current = UserContext.getcurrent();
		
		//当前用户相关的信息
		model.addAttribute("userinfo", this.userinfoService.get(current.getId()));
		//当前用户用户相关的信息
		model.addAttribute("account", this.accountService.get(current.getId()));
		
		return "personal";
	}
	
	/**
	 * 绑定手机号
	 * @param phoneNumber
	 * @param verifyCode
	 * @return
	 */
	@RequireLogin
	@RequestMapping("bindPhone")
	@ResponseBody
	public JSONResult bindPhone(String phoneNumber, String verifyCode){
		JSONResult json = new JSONResult();
		try{
			this.userinfoService.bindPhone(phoneNumber, verifyCode);
		}catch(RuntimeException e){
			json.setSuccess(false);
			json.setMsg(e.getMessage());
		}
		return json;
	}
	
	/**
	 * 发送绑定邮箱邮件
	 * @param email
	 * @return
	 */
	@RequireLogin
	@RequestMapping("sendEmail")
	@ResponseBody
	public JSONResult sendEmail(String email){
		
		/**
		 *1、创建jsonResult对象
		 *2、调用sendVerifyEmail()方法
		 *3、抛出异常 
		 */
		
		JSONResult json = new JSONResult();
		try{
			this.userinfoService.sendVerifyEmail(email);
		}catch(RuntimeException e){
			json.setSuccess(false);
			json.setMsg(e.getMessage());
		}
		return json;
	}
	
	/**
	 * 执行邮箱绑定
	 */
	@RequestMapping("bindEmail")
	public String bindEmail(String key,Model model){
		try{
			this.userinfoService.bindEmail(key);
			model.addAttribute("success", true);
		}catch(RuntimeException e){
			model.addAttribute("success", false);
			model.addAttribute("msg", e.getMessage());
		}
		/*
		 * 执行完毕后，返回指定页面
		 */
		return "checkmail_result";
	}
}
