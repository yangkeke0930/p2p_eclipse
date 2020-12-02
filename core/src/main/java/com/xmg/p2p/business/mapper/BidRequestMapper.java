package com.xmg.p2p.business.mapper;

import com.xmg.p2p.business.domain.BidRequest;
import com.xmg.p2p.business.query.BidRequestQueryObject;

import java.util.List;

public interface BidRequestMapper {

    int insert(BidRequest record);

    BidRequest selectByPrimaryKey(Long id);

    int updateByPrimaryKey(BidRequest record);
    
    /**
     * 借款审核的分页查询页面
     */
    int queryForCount(BidRequestQueryObject qo);
    List<BidRequest> query(BidRequestQueryObject qo);
}