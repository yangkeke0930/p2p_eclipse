package com.xmg.p2p.base.mapper;

import java.util.List;

import com.xmg.p2p.base.domain.RealAuth;
import com.xmg.p2p.base.query.RealAuthQueryObject;

public interface RealAuthMapper {
	

    int insert(RealAuth record);

    RealAuth selectByPrimaryKey(Long id);

    int updateByPrimaryKey(RealAuth record);
    
    /**
     * 后台审核分页查询相关
     */
    //查询当前页面有多少页
    int queryForCount(RealAuthQueryObject qo);
    //查询当前页面中的内容
    List<RealAuth> query(RealAuthQueryObject qo);
    
}