package com.xmg.p2p.base.domain;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import lombok.Getter;
import lombok.Setter;

/**
 * 数据字典分类
 * 
 * @author 78158
 *
 */
@Setter
@Getter
public class SystemDictionary extends BaseDomain {
	
	/**
	 * 数据字典分类
	 */
	private String sn;
	
	/**
	 * 数据字典名称分类
	 */
	private String title; // 数据字典分类名称

	/**
	 * 返回当前的JSON字符串
	 * @return
	 */
	public String getJsonString(){
		Map<String, Object> json = new HashMap<>();
		json.put("id", id);
		json.put("sn", sn);
		json.put("title", title);
		return JSONObject.toJSONString(json);
	}
}
