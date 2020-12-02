package com.xmg.p2p.base.service;

import com.xmg.p2p.base.domain.RealAuth;
import com.xmg.p2p.base.query.PageResult;
import com.xmg.p2p.base.query.RealAuthQueryObject;

/**
 * 实名认证对象服务
 * @author Yang Ke Ke
 *
 */
public interface IRealAuthService {

	RealAuth get(Long id);

	/**
	 * 申请实名认证
	 * @param realAuth
	 */
	void apply(RealAuth realAuth);
	
	/**
	 * 查询分页查询的结果
	 */
	PageResult query(RealAuthQueryObject qo);

	/**
	 * 实名认证审核
	 * @param id
	 * @param state
	 * @param remark
	 */
	void audit(long id, int state, String remark);
}
