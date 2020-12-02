package com.xmg.p2p.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xmg.p2p.base.domain.Logininfo;
import com.xmg.p2p.base.service.IAccountService;
import com.xmg.p2p.base.service.IUserinfoService;
import com.xmg.p2p.base.util.BidConst;
import com.xmg.p2p.base.util.UserContext;
import com.xmg.p2p.business.domain.BidRequest;
import com.xmg.p2p.business.service.IBidRequestService;

/**
 * 借款申请相关的控制器
 * @author Yang Ke Ke
 *
 */
@Controller
public class BorrowController {
	
	/**
	 * 账户信息
	 */
	@Autowired
	private IAccountService accountService;
	@Autowired
	private IUserinfoService userinfoService;
	
	/**
	 * 注入借款服务
	 */
	@Autowired
	private IBidRequestService bidRequestService;
	
	/**
	 * 
	 */

	/**
	 * 导向到我要借款的首页
	 * @param model
	 * @return
	 */
	@RequestMapping("borrow")
	public String borrowIndex(Model model){
		
		//从当前登录用户的额session中获取当前用户
		Logininfo current = UserContext.getcurrent();
		
		if(current == null){
			//用户为null则重定向到borrow.html静态页面
			return "redirect:borrow.html";
			
		}else{
			model.addAttribute("account", this.accountService.getCurrent());
			model.addAttribute("userinfo",this.userinfoService.getCurrent());
			model.addAttribute("creditBorrowScore",BidConst.BASE_BORROW_SCORE);
			
			//转发到borrow.html动态页面
			return "borrow";
		}
	}
	
	/**
	 * 导航到可以申请借款的页面
	 * @param model
	 * @return
	 */
	@RequestMapping("borrowInfo")
	public String borrowInfo(Model model){
		/*
		 * 获取当前的用户
		 * 1、判断当前用户是否满足要求
		 * 2、满足要求是导向一个页面
		 * 3、不满足要求是导向一个页面
		 */
		Long id = UserContext.getcurrent().getId();
		if(this.bidRequestService.canApplyRequest(id)){
			model.addAttribute("minBidRequestAmount", BidConst.SMALLEST_BIDREQUEST_AMOUNT);	//系统规定的最小借款金额
			model.addAttribute("minBidAmount", BidConst.SMALLEST_BID_AMOUNT);	//系统规定的最小投标金额
			model.addAttribute("account", this.accountService.getCurrent() );	//当前登录的账户
			return "borrow_apply";
		}else{
			return "borrow_apply_result";
		}
	}
	
	/**
	 * 申请借款
	 * @param model
	 * @return 重定向到borrowInfo.do页面
	 */
	@RequestMapping("borrow_apply")
	public String borrowApply(BidRequest bidRequest){
		this.bidRequestService.apply(bidRequest);
		return "redirect:/borrowInfo";
	}
	
	
	
}
