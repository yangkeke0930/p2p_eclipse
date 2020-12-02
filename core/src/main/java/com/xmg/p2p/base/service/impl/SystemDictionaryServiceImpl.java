package com.xmg.p2p.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmg.p2p.base.domain.SystemDictionary;
import com.xmg.p2p.base.domain.SystemDictionaryItem;
import com.xmg.p2p.base.mapper.SystemDictionaryItemMapper;
import com.xmg.p2p.base.mapper.SystemDictionaryMapper;
import com.xmg.p2p.base.query.PageResult;
import com.xmg.p2p.base.query.SystemDictionaryQueryObject;
import com.xmg.p2p.base.service.ISystemDictionaryService;

/**
 * 数据字典相关服务类接口的实现类
 * @author Yang Ke Ke
 *
 */
@Service
public class SystemDictionaryServiceImpl implements ISystemDictionaryService {

	/*
	 * 添加依赖
	 */
	@Autowired
	private SystemDictionaryMapper systemDictionaryMapper;
	
	@Autowired
	private SystemDictionaryItemMapper systemDictionaryItemMapper;
	
	@Override
	public PageResult queryDics(SystemDictionaryQueryObject qo) {
		
		int count = this.systemDictionaryMapper.queryForCount(qo);
		if(count > 0){
			List<SystemDictionary> list = this.systemDictionaryMapper.query(qo);
			return new PageResult(list, count, qo.getCurrentPage(), qo.getPageSize());
		}
		return PageResult.empty(qo.getPageSize()) ;
	}

	/**
	 * 修改/保存数据字典分类
	 */
	@Override
	public void saveOrUpdateDic(SystemDictionary dictionary) {
		if(dictionary.getId() != null){
			this.systemDictionaryMapper.updateByPrimaryKey(dictionary);
		}else{
			this.systemDictionaryMapper.insert(dictionary);
		}
		
	}

	/**
	 * 数据字典分类的分页查询
	 */
	@Override
	public PageResult queryItems(SystemDictionaryQueryObject qo) {
		int count = this.systemDictionaryItemMapper.queryForCount(qo);
		if(count > 0){
			List<SystemDictionaryItem> list = this.systemDictionaryItemMapper.query(qo);
			return new PageResult(list, count, qo.getCurrentPage(),
					qo.getPageSize());
		}
		return PageResult.empty(qo.getPageSize()) ;
	}

	/**
	 * 查询所有的字典明细
	 */
	@Override
	public List<SystemDictionary> listAllDic() {
	 
		return this.systemDictionaryMapper.selectAll();
	}

	/**
	 * 修改或者保存数据字典明细
	 */
	@Override
	public void saveOrUpdateItem(SystemDictionaryItem item) {
		//判断item是否为空
		if(item.getId() != null){
			//如果不为空，则更新数据
			this.systemDictionaryItemMapper.updateByPrimaryKey(item);
		}else{
			//如果为空则插入新数据
			this.systemDictionaryItemMapper.insert(item);
		}
	}

	/**
	 * 根据数字字典sn来查询明细
	 */
	@Override
	public List<SystemDictionaryItem> listByParentSn(String sn) {
		 
		return this.systemDictionaryItemMapper.listByParentSn(sn);
	}
	

}
