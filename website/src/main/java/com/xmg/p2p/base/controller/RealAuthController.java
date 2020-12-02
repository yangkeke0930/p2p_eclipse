package com.xmg.p2p.base.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.xmg.p2p.base.domain.RealAuth;
import com.xmg.p2p.base.domain.UserInfo;
import com.xmg.p2p.base.service.IRealAuthService;
import com.xmg.p2p.base.service.IUserinfoService;
import com.xmg.p2p.base.util.JSONResult;
import com.xmg.p2p.base.util.RequireLogin;
import com.xmg.p2p.base.util.UploadUtil;

/**
 * 实名认证的controller
 * @author Yang Ke Ke
 *
 */
@Controller
public class RealAuthController {
	
	@Autowired
	private IUserinfoService userinfoService;
	@Autowired
	private IRealAuthService realAuthService;
	@Autowired
	private ServletContext servletContext;
	
	@RequireLogin
	@RequestMapping("realAuth")
	public String realAuth(Model model){
		//1、获取当前用户userinfo
		UserInfo current = this.userinfoService.getCurrent();
		//2、如果用户已经实名认证则返回认证成功的页面realAuth_result 
		if(current.getIsRealAuth()){
			//将实名认证对象放置到model
			model.addAttribute("realAuth",this.realAuthService.get(current.getRealAuthId()));
			// 将auditing设置为false
			model.addAttribute("auditing", false);
			return "realAuth_result";
		}else{
			//3、如果用户没有实名认证 ,但是实名认证id不为空，说明有实名认证id，说明正在实名认证的过程中 也就是说auditing为true
			if(current.getRealAuthId() != null){
				//1.userinfo上面是否存在realAuthId, 将auditing 设置为true
				model.addAttribute("auditing", true);
				return "realAuth_result";
			}else{//反之则没有实名认证的id 则直接打回到实名认证的界面，也就是说realAuth的界面。
				//	  2.userinfo上面没有realAuthId,将跳转到realauth界面
				 return "realAuth";
			}
		}
	}
	
	/**
	 * 千万不要加requireLogin
	 * 实名认证之上传身份证正反面 
	 * @param file
	 */
	@RequestMapping("reaAuthUpload")
	@ResponseBody    //使用方式：用于数据回显
  	public String reaAuthUpload(MultipartFile	 file){
		/**
		 * 后台处理上传文件的逻辑分析
		 */
		//首先要得到basepath
		String basePath = servletContext.getRealPath("/upload");
		//获取上传文件的名称
		String fileName = UploadUtil.upload(file, basePath);
		//返回上传文件的名称
		return "/upload/" + fileName;
	}
	
	/**
	 * 申请实名认证
	 * @param realAuth
	 * @return
	 */
	@RequireLogin
	@RequestMapping("realAuth_save")
	@ResponseBody
	public JSONResult realAuthSave(RealAuth realAuth){
		this.realAuthService.apply(realAuth);
		return new JSONResult();
	}
}
