package com.xmg.p2p.base.mapper;

import com.xmg.p2p.base.domain.SystemDictionary;
import com.xmg.p2p.base.query.SystemDictionaryQueryObject;

import java.util.List;

public interface SystemDictionaryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SystemDictionary record);

    SystemDictionary selectByPrimaryKey(Long id);

    List<SystemDictionary> selectAll();

    int updateByPrimaryKey(SystemDictionary record);
    
    /**
     * 分页的方法
     * @param qo
     * @return
     */
    int queryForCount(SystemDictionaryQueryObject qo);
    List<SystemDictionary> query(SystemDictionaryQueryObject qo);
}