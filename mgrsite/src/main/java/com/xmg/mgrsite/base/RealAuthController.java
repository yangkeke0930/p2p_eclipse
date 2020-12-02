package com.xmg.mgrsite.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmg.p2p.base.query.RealAuthQueryObject;
import com.xmg.p2p.base.service.IRealAuthService;
import com.xmg.p2p.base.util.JSONResult;

/**
 * 实名认证审核相关
 * 
 * @author Yang Ke Ke
 *
 */
@Controller
public class RealAuthController {
	
	@Autowired
	private IRealAuthService realAuthService;

	@RequestMapping("realAuth")
	public String realAuth(@ModelAttribute("qo") RealAuthQueryObject qo, Model model) {
		model.addAttribute("pageResult", this.realAuthService.query(qo));
		return "realAuth/list";
	}
	
	/**
	 * 实名认证审核
	 * @param id
	 * @param state
	 * @param remark
	 * @return
	 */
	@RequestMapping("realAuth_audit")
	@ResponseBody
	public JSONResult realAuthAudit(long id, int state, String remark){
		this.realAuthService.audit(id,state,remark);
		return new JSONResult();
	}
}
