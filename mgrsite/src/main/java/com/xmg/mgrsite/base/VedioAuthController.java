package com.xmg.mgrsite.base;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmg.p2p.base.mapper.UserInfoMapper;
import com.xmg.p2p.base.query.VedioAuthQueryObject;
import com.xmg.p2p.base.service.IUserinfoService;
import com.xmg.p2p.base.service.IVedioAuthService;
import com.xmg.p2p.base.util.JSONResult;

/**
 * 视频认证服务的controller
 * 
 * @author Yang Ke Ke
 *
 */
@Controller
public class VedioAuthController {
	/**
	 * 视频认证分页查询页面显示
	 */
	@Autowired
	private IVedioAuthService vedioAuhtService;
	@Autowired
	private IUserinfoService userinfoService;
	
	@RequestMapping("vedioAuth")
	public String vedioAuth(@ModelAttribute("qo") VedioAuthQueryObject qo, Model model) {
		model.addAttribute("pageResult", this.vedioAuhtService.query(qo));
		return "vedioAuth/list";
	}

	/**
	 * 完成视频审核
	 */
	@RequestMapping("vedioAuth_audit")
	@ResponseBody
	public JSONResult vedioAuthAudit(int state, long loginInfoValue, String remark) {
		this.vedioAuhtService.audit(state, loginInfoValue, remark);
		return new JSONResult();
	}
	
	/**
	 * 用于用户得autocomplate
	 */
	@RequestMapping("vedioAuth_autocomplate")
	@ResponseBody
	public List<Map<String, Object>> autoComplate(String keyword){
		return this.userinfoService.autocomplate(keyword);
	}
}
