package com.xmg.p2p.base.mapper;

import com.xmg.p2p.base.domain.Iplog;
import com.xmg.p2p.base.query.IplogQueryObject;

import java.util.List;

public interface IplogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Iplog record);

    Iplog selectByPrimaryKey(Long id);

    List<Iplog> selectAll();

    int updateByPrimaryKey(Iplog record);
    
    /**
     * 分页
     * @param qo
     * @return
     */
	int queryForCount(IplogQueryObject qo);

	List<Iplog> query(IplogQueryObject qo);
}