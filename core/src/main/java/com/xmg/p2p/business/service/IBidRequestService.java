package com.xmg.p2p.business.service;

import com.xmg.p2p.base.query.PageResult;
import com.xmg.p2p.business.domain.BidRequest;
import com.xmg.p2p.business.query.BidRequestQueryObject;

/**
 * 借款服务
 * @author Yang Ke Ke
 *
 */
public interface IBidRequestService {

	/**
	 * 乐观锁相关
	 * @param bidRequest
	 */
	void update(BidRequest bidRequest);

	/**
	 * 判断当前用户是否可以申请借款
	 * @param id 当以前登录用户的id
	 */
	boolean canApplyRequest(Long logininfoId);

	/**
	 * 借款申请
	 * @param bidRequest
	 */
	void apply(BidRequest bidRequest);
	
	/**
	 * 借款对象的分页查询语句
	 */
	PageResult query(BidRequestQueryObject qo);
 
}
