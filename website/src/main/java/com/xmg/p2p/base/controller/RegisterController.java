package com.xmg.p2p.base.controller;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.util.JAXBResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmg.p2p.base.domain.Logininfo;
import com.xmg.p2p.base.service.ILogininfoService;
import com.xmg.p2p.base.util.JSONResult;
 

/**
 * 用于注册/登录相关的controller
 * @author 78158
 *
 */
@Controller
public class RegisterController {
	
	//添加service层的依赖
	@Autowired
	private ILogininfoService logininfoService;
	

	/**
	 * 用户注册 
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping("register")
	@ResponseBody
	public JSONResult register(String username, String password){
		JSONResult json = new JSONResult();
		try{
			logininfoService.register(username, password);
		}catch(RuntimeException e){
			
			json.setSuccess(false);
			//该处的message是从后台获取到的 message
			json.setMsg(e.getMessage());
		}
		
		return json;
	}
	
	/**
	 * 用户注册时候，在输入用户名称的时候，就可以显示该用户名是否已经存在。
	 * @param username
	 * @return
	 */
	@RequestMapping("checkUsername")
	@ResponseBody
	public boolean checkUsername(String username){
		return !this.logininfoService.checkUsername(username);
	}
	
	/**
	 * 用户登录
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping("login")
	@ResponseBody
	public JSONResult login(String username, String password, HttpServletRequest request){
		
			JSONResult json = new JSONResult();
			//执行查询操作的代码。
			//添加该登录账户为前端登录账户
			Logininfo current = this.logininfoService.login(username, password , request.getRemoteAddr(), Logininfo.USER_CLIENT);
			
			//给出提示信息
			if(current == null){
				json.setSuccess(false);				
				json.setMsg("用户名或者密码错误！");
			}
			return json;
	}
}
