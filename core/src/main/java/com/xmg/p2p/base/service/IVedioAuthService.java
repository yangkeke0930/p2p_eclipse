package com.xmg.p2p.base.service;

import com.xmg.p2p.base.query.PageResult;
import com.xmg.p2p.base.query.VedioAuthQueryObject;

/**
 * 视频认证服务
 * @author Yang Ke Ke
 *
 */
public interface IVedioAuthService {

	/*
	 * 视频认证分页查询页面
	 */
	PageResult query(VedioAuthQueryObject qo);

	/**
	 * 完成视频审核
	 * @param state
	 * @param loginInfoValue
	 * @param remark
	 */
	void audit(int state, long loginInfoValue, String remark);
}
