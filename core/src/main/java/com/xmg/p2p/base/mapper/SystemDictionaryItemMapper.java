package com.xmg.p2p.base.mapper;

import com.xmg.p2p.base.domain.SystemDictionaryItem;
import com.xmg.p2p.base.query.SystemDictionaryQueryObject;

import java.util.List;

public interface SystemDictionaryItemMapper {
	
	int deleteByPrimaryKey(Long id);

	int insert(SystemDictionaryItem record);

	SystemDictionaryItem selectByPrimaryKey(Long id);

	List<SystemDictionaryItem> selectAll();

	int updateByPrimaryKey(SystemDictionaryItem record);
	
	/**
	 * 分页相关的查询
	 * 	1>先查询有多少页
	 * 	2>然后查询每页中有多少个数据
	 */
	int queryForCount(SystemDictionaryQueryObject qo);
	List<SystemDictionaryItem> query(SystemDictionaryQueryObject qo);

	/**
	 * 根据数字字典sn来查询明细
	 * @param sn
	 * @return
	 */
	List<SystemDictionaryItem> listByParentSn(String sn);
	
}