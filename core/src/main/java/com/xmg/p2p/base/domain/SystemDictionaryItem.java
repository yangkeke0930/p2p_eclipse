package com.xmg.p2p.base.domain;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import lombok.Getter;
import lombok.Setter;

/**
 * 数据字典明细
 * @author 78158
 *
 */
@Setter
@Getter
public class SystemDictionaryItem extends BaseDomain {
	
	/**
	 * 数据字典明细对应的分类名称
	 */
	private Long parentId;	 
	
	/**
	 * 数据字典明细对应的显示名称
	 */
	private String title;	 
	
	/**
	 * 数据字明细分类的排序
	 */
	private Integer sequence; //该分类的排序
	
	/**
	 * 返回当前的json字符串
	 */
	public String getJsonString(){
		Map<String, Object> json = new HashMap<>();
		json.put("id", id);
		json.put("title", title);
		json.put("sequence", sequence);
		json.put("parentId", parentId);
		return JSONObject.toJSONString(json);
	}
}
