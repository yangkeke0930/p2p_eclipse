package com.xmg.mgrsite.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmg.p2p.base.query.IplogQueryObject;
import com.xmg.p2p.base.service.IIplogService;

/**
 * 后台登录查询日志controller
 * @author 78158
 *
 */
@Controller
public class IplogController {
	
	//注入依赖
	@Autowired
	private IIplogService iplogService;
	
	/*
	 * 后台管理用户查询日志的功能
	 */
	@RequestMapping("ipLog")
	//@ModelAttribute("qo")为了让页面更好的回显 qo，前端页面就可以使用qo了
	public String ipLog(@ModelAttribute("qo") IplogQueryObject qo, Model model){
		model.addAttribute("pageResult", iplogService.query(qo));
		return "ipLog/list";
	}
}
