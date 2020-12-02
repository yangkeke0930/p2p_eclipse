package com.xmg.p2p.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xmg.p2p.base.query.IplogQueryObject;
import com.xmg.p2p.base.service.IIplogService;
import com.xmg.p2p.base.util.RequireLogin;
import com.xmg.p2p.base.util.UserContext;

/**
 * 查看登录日志的controller
 * @author 78158
 *
 */
@Controller
public class IplogController {
	
	//注入依赖
	@Autowired
	private IIplogService iplogService;
	/**
	 * 个人用户登录的记录列表
	 * @param qo
	 * @param model
	 * @return
	 */
	@RequireLogin
	@RequestMapping("ipLog")
	public String iplogList(@ModelAttribute("qo")IplogQueryObject qo, Model model){
		//在个人用户记录表中只显示自己账户的信息
		qo.setUsername(UserContext.getcurrent().getUsername());
		model.addAttribute("pageResult", this.iplogService.query(qo));
		return "iplog_list";
	}
}
