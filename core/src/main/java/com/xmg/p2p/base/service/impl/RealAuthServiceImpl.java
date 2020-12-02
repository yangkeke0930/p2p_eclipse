package com.xmg.p2p.base.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmg.p2p.base.domain.RealAuth;
import com.xmg.p2p.base.domain.UserInfo;
import com.xmg.p2p.base.mapper.RealAuthMapper;
import com.xmg.p2p.base.query.PageResult;
import com.xmg.p2p.base.query.RealAuthQueryObject;
import com.xmg.p2p.base.service.IRealAuthService;
import com.xmg.p2p.base.service.IUserinfoService;
import com.xmg.p2p.base.util.BitStatesUtils;
import com.xmg.p2p.base.util.UserContext;

/**
 * 实名认证服务接口的实现类
 * 
 * @author Yang Ke Ke
 *
 */
@Service
public class RealAuthServiceImpl implements IRealAuthService {

	@Autowired
	private RealAuthMapper realAuthMapper;
	@Autowired
	private IUserinfoService userinfoService;

	@Override
	public RealAuth get(Long id) {
		return realAuthMapper.selectByPrimaryKey(id);
	}

	/**
	 * 申请实名认证
	 */
	@Override
	public void apply(RealAuth realAuth) {
		// 0.获取当前用户，注意这里的当前用户指的是userinfo中的用户。不是logininfo中的用户。
		UserInfo current = this.userinfoService.getCurrent();
		// 1.判断当前用户没有实名认证，且不处于待审核的状态即审核状态的id为空
		if (!current.getIsRealAuth() && current.getRealAuthId() == null) {
			// 2.保存实名认证对象
			realAuth.setState(RealAuth.STATE_NORML); // 状态正常
			realAuth.setApplier(UserContext.getcurrent()); // 获取当前用户
			realAuth.setApplyTime(new Date());
			// 3.将该用户插入到数据库中
			this.realAuthMapper.insert(realAuth);
		}
		// 4.给当前用户设置实名认证的id
		current.setRealAuthId(realAuth.getId());
		this.userinfoService.update(current);
	}

	/**
	 * 分页查询结果的页面回写
	 */
	@Override
	public PageResult query(RealAuthQueryObject qo) {
		int count = this.realAuthMapper.queryForCount(qo);
		if (count > 0) {
			List<RealAuth> list = this.realAuthMapper.query(qo);
			return new PageResult(list, count, qo.getCurrentPage(), qo.getPageSize());
		}
		return PageResult.empty(qo.getCurrentPage());
	}

	/**
	 * 实名认证审核
	 */
	@Override
	public void audit(long id, int state, String remark) {
		// 1、获取实名认证对象
		RealAuth ra = this.get(id);
		// 2、判断实名认证对象存在且处于待审核状态
		if (ra != null && ra.getState() == RealAuth.STATE_NORML) {
			ra.setAuditor(UserContext.getcurrent());
			ra.setApplyTime(new Date());
			ra.setState(state);

			/**
			 * 判断当前用户的实名认证状态码
			 */
			// 获取当前的申请用户
			UserInfo applier = this.userinfoService.get(ra.getApplier().getId());
			if (state == RealAuth.STATE_AUDIT) {
				//用户已经提交实名认证。但是还未开始实名认证
				if(!applier.getIsRealAuth()){
					//开始实名认证的相关属性
					applier.addState(BitStatesUtils.OP_REAL_AUTH);
					applier.setRealName(ra.getRealName());
					applier.setIdNumber(ra.getIdNumber());
					applier.setRealAuthId(ra.getId());
				}

			} else {// 实名认证用户的实名认证状态码不正常
				applier.setRealAuthId(null);
			}
			this.userinfoService.update(applier);
			this.realAuthMapper.updateByPrimaryKey(ra);
		}
	}

}
