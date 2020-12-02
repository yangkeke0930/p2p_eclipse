package com.xmg.p2p.base.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmg.p2p.base.domain.Logininfo;
import com.xmg.p2p.base.domain.UserInfo;
import com.xmg.p2p.base.domain.VedioAuth;
import com.xmg.p2p.base.mapper.UserInfoMapper;
import com.xmg.p2p.base.mapper.VedioAuthMapper;
import com.xmg.p2p.base.query.PageResult;
import com.xmg.p2p.base.query.VedioAuthQueryObject;
import com.xmg.p2p.base.service.IUserinfoService;
import com.xmg.p2p.base.service.IVedioAuthService;
import com.xmg.p2p.base.util.BitStatesUtils;
import com.xmg.p2p.base.util.UserContext;
/**
 * 视频认证服务
 * @author Yang Ke Ke
 *
 */
@Service
public class VedioAuthQueryObjectImpl implements IVedioAuthService {
	
	@Autowired
	private VedioAuthMapper vedioAuthMapper;
	@Autowired
	private IUserinfoService userinfoservice;
	
	/**
	 * 视频认证分页查询页面
	 */
	@Override
	public PageResult query(VedioAuthQueryObject qo) {
		int count = this.vedioAuthMapper.queryForCount(qo);
		if(count > 0){
			List<VedioAuth> list = this.vedioAuthMapper.query(qo);
			return new PageResult(list, count, qo.getCurrentPage(),
					qo.getPageSize());
		}
		return PageResult.empty(qo.getPageSize()) ;
	}
	
	/**
	 * 完成视频审核
	 */
	@Override
	public void audit(int state, long loginInfoValue, String remark) {
		//判断当前用户没有视频认证
		UserInfo user = this.userinfoservice.get(loginInfoValue);
		if(user != null && !user.getIsVideoAuth()){
			//创建视频认证对象
			VedioAuth va = new VedioAuth();
			//logininfo 是用户登录的相关信息
			Logininfo applier = new Logininfo();
			applier.setId(loginInfoValue);
			va.setApplier(applier);
			va.setApplyTime(new Date());
			va.setAuditor(UserContext.getcurrent());
			va.setAuditTime(new Date());
			va.setRemark(remark);
			va.setState(state);
			this.vedioAuthMapper.insert(va);
			
			//判断用户状态
			if(state == VedioAuth.STATE_AUDIT){
				//修改用户的状态码
				user.addState(BitStatesUtils.OP_VIDEO_AUTH);
				this.userinfoservice.update(user);
			}
		}
	}

}
