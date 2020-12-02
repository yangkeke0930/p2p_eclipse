package com.xmg.mgrsite.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmg.p2p.base.query.UserFileQueryObject;
import com.xmg.p2p.base.service.IUserFileService;
import com.xmg.p2p.base.util.JSONResult;

/**
 * 风控材料审核相关
 * @author Yang Ke Ke
 *
 */
@Controller
public class UserFileController {
	
	/*
	 * 添加对应的依赖
	 */
	@Autowired
	private IUserFileService userFileService;

	
	/**
	 * 显示后台风控材料审核界面
	 * @param model
	 * @param qo
	 * @return
	 */
	@RequestMapping("userFileAuth")
	public String userFileAuth(Model model, @ModelAttribute("qo")UserFileQueryObject qo){
		model.addAttribute("pageResult", this.userFileService.query(qo));
		return "userFileAuth/list";
	}
	
	/**
	 * 用户风控资料的审核
	 * @param id
	 * @param state
	 * @param score
	 * @param remark
	 * @return
	 */
	@RequestMapping("userFile_audit")
	@ResponseBody
	public JSONResult audit(Long id, int state, int score, String remark){
		this.userFileService.audit(id,state,remark,score);
		return new JSONResult();
	}
	
}
