package com.xmg.p2p.base.service;

import java.util.List;

import com.xmg.p2p.base.domain.SystemDictionary;
import com.xmg.p2p.base.domain.SystemDictionaryItem;
import com.xmg.p2p.base.query.PageResult;
import com.xmg.p2p.base.query.SystemDictionaryQueryObject;

/**
 * 数据字典相关服务
 * @author Yang Ke Ke
 *
 */
public interface ISystemDictionaryService {
	
	/**
	 * 数据字典分类分页查询
	 * @param qo
	 * @return
	 */
	PageResult queryDics(SystemDictionaryQueryObject qo);
	
	/**
	 * 修改/保存数据字典分类
	 * @param dictionary
	 */
	void saveOrUpdateDic(SystemDictionary dictionary);
	
	/**
	 * 数据字典明细的分页查询
	 * @param qo
	 * @return
	 */
	PageResult queryItems(SystemDictionaryQueryObject qo);
	
	/**
	 * 查询所有的字典明细
	 * @return
	 */
	List<SystemDictionary> listAllDic();

	/**
	 * 修改或者保存数据字典明细
	 * @param item
	 */
	void saveOrUpdateItem(SystemDictionaryItem item);
	
	/**
	 * 根据数字字典sn查询明细
	 * @param sn
	 * @return
	 */
	List<SystemDictionaryItem> listByParentSn(String sn);

}
