package com.xmg.mgrsite.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmg.p2p.base.domain.SystemDictionary;
import com.xmg.p2p.base.domain.SystemDictionaryItem;
import com.xmg.p2p.base.query.SystemDictionaryQueryObject;
import com.xmg.p2p.base.service.ISystemDictionaryService;
import com.xmg.p2p.base.util.JSONResult;

/**
 * 数据字典相关的控制类
 * 
 * @author Yang Ke Ke
 *
 */
@Controller
public class SystemDictionaryController {

	/*
	 * 添加依赖
	 */
	@Autowired
	private ISystemDictionaryService systemDictionaryService;
	
 
	
	/**
	 * 数据字典分类列表
	 * @param qo
	 * @param model
	 * @return
	 */
	@RequestMapping("systemDictionary_list")
	public String systemDictionaryList(@ModelAttribute("qo") SystemDictionaryQueryObject qo, Model model) {
		model.addAttribute("pageResult", this.systemDictionaryService.queryDics(qo));
		return "systemdic/systemDictionary_list";
	}
	
	/**
	 * 修改/保存数据字典分类
	 * @param model
	 * @return
	 */
	@RequestMapping("systemDictionary_update")
	@ResponseBody	//因为是使用ajax进行提交的
	public JSONResult systemDictionaryUpdate(SystemDictionary dictionary){
		this.systemDictionaryService.saveOrUpdateDic(dictionary);
		return new JSONResult();
	}
	
	/**
	 * 数据字典明细列表
	 * @param model
	 * @return
	 */
	@RequestMapping("systemDictionaryItem_list")
	public String systemDictionaryItemList(@ModelAttribute("qo") SystemDictionaryQueryObject qo, Model model){
		model.addAttribute("pageResult", this.systemDictionaryService.queryItems(qo));
		model.addAttribute("systemDictionaryGroups", this.systemDictionaryService.listAllDic());
		return "systemdic/systemDictionaryItem_list";
	}
	
	/**
	 * 添加或者修改数据字典
	 * @param item
	 * @return
	 */
	@RequestMapping("systemDictionaryItem_update")
	@ResponseBody
	public JSONResult systemDictionaryItemUpdate(SystemDictionaryItem item){
		this.systemDictionaryService.saveOrUpdateItem(item);
		return new JSONResult();
	}
}
