package com.xmg.mgrsite.base;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmg.p2p.base.domain.Logininfo;
import com.xmg.p2p.base.service.ILogininfoService;
import com.xmg.p2p.base.util.JSONResult;

/**
 * 后台登录
 * 
 * @author 78158
 *
 */
@Controller
public class LoginController {

	// 注入依赖
	@Autowired
	private ILogininfoService logininfoService;

	/**
	 * 后台登录
	 * 
	 * @param username
	 * @param password
	 * @param request
	 * @return
	 */
	@RequestMapping("/login")
	@ResponseBody
	public JSONResult login(String username, String password, HttpServletRequest request) {

		// 创建响应json返回的对象
		JSONResult json = new JSONResult();
		// 调用service中的方法从后台查询 出来的结果
		Logininfo current = this.logininfoService.login(username, password, request.getRemoteAddr(),
				Logininfo.USER_MANAGER);
		//判断结果是否存在
		if(current == null){
			json.setSuccess(false);
			json.setMsg("账号或密码错误！");
		}
		return json;
	}
	
	/**
	 * 后台管理员登录后的主页
	 * @return
	 */
	@RequestMapping("index")
	public String index(){
		
		return "main";
	}
}
