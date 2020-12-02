package com.xmg.p2p.business.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.tools.doclets.internal.toolkit.Content;
import com.sun.tools.javac.util.Context;
import com.xmg.p2p.base.domain.Account;
import com.xmg.p2p.base.domain.Logininfo;
import com.xmg.p2p.base.domain.UserInfo;
import com.xmg.p2p.base.query.PageResult;
import com.xmg.p2p.base.service.IAccountService;
import com.xmg.p2p.base.service.IUserinfoService;
import com.xmg.p2p.base.util.BidConst;
import com.xmg.p2p.base.util.BitStatesUtils;
import com.xmg.p2p.base.util.UserContext;
import com.xmg.p2p.business.domain.BidRequest;
import com.xmg.p2p.business.mapper.BidRequestMapper;
import com.xmg.p2p.business.query.BidRequestQueryObject;
import com.xmg.p2p.business.service.IBidRequestService;
import com.xmg.p2p.business.util.CalculatetUtil;

/**
 * 借款相关
 * 
 * @author Yang Ke Ke
 *
 */
@Service
public class BidRequestServiceImpl implements IBidRequestService {

	/**
	 * 注入相关依赖
	 */
	@Autowired
	private BidRequestMapper bidRequestMapper;

	/*
	 * 添加当前用户的依赖
	 */
	@Autowired
	private IUserinfoService userinfoService;

	/**
	 * 注入账户相关的依赖
	 */
	@Autowired
	private IAccountService accountService;
	
	/**
	 * 验证乐观锁的状态
	 */
	public void update(BidRequest bidRequest) {

		int state = this.bidRequestMapper.updateByPrimaryKey(bidRequest);
		if (state == 0) {
			throw new RuntimeException("乐观锁验证失败    bidRequest:" + bidRequest.getBidCount());
		}

	}

	/**
	 * 判断当前用户是否满足贷款申请的要求
	 * 
	 * @return true 或者false
	 * @param logininfoId为当前登录用户的id，可以通过该id查到用户的其他信息
	 */
	@Override
	public boolean canApplyRequest(Long logininfoId) {

		/**
		 * 获取当前用户 1、因为要判断该用户是否满足申请得贷款得要求， 2、所以需要更多得信息 3、所以需通过id来查询到更多得用户用心
		 */
		UserInfo userinfo = this.userinfoService.get(logininfoId);
		/*
		 * 判断当前用户是否满足要求 1、用户不能为空 2、基本资料 3、实名认证 4、视频认证 5、分控分数 6、没有旧的贷款在流程中
		 */
		return userinfo != null && userinfo.getIsBasicInfo() && userinfo.getIsRealAuth() && userinfo.getIsVideoAuth()
				&& userinfo.getScore() >= BidConst.BASE_BORROW_SCORE && !userinfo.getHasBidRequestProcess();

	}

	/**
	 * 借款申请
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.xmg.p2p.business.service.IBidRequestService#apply(com.xmg.p2p.
	 * business.domain.BidRequest)
	 */
	public void apply(BidRequest br) {
		/*
		 * 1、获取账户信息
		 */
		Account account = this.accountService.getCurrent();

		/*
		 * 1、基础校验 2.数据校验： 1.需要获当前账户得数据然后跟基础数据进行校验 2.当前账户关于金额得信息都在account中
		 */
		if (this.canApplyRequest(UserContext.getcurrent().getId()) // 判断满足贷款要求

				// 页面数据得校验
				/**
				 * 最小得借款金额 >= 借款金额要 <= 剩余得借款额度
				 */
				&& br.getBidRequestAmount().compareTo(BidConst.SMALLEST_BID_AMOUNT) >= 0
				&& br.getBidRequestAmount().compareTo(account.getRemainBorrowLimit()) <= 0
				/**
				 * 利息要在规定的范围内5~20
				 */
				&& br.getCurrentRate().compareTo(BidConst.SMALLEST_CURRENT_RATE) >= 0
				&& br.getCurrentRate().compareTo(BidConst.MAX_CURRENT_RATE) <= 0
				&& br.getMinBidAmount().compareTo(BidConst.SMALLEST_BID_AMOUNT) >= 0) {

			/**
			 * 保存数据
			 */
			/**
			 * 保存数据的业务逻辑分析 1、创建一个新的bidrequest对象 2、添加必要的属性 3、设置状态 4、执行数据库中的操作
			 */
			BidRequest bidRequest = new BidRequest();
			// 添加对应的属性
			bidRequest.setBidRequestAmount(br.getBidRequestAmount()); // 借款金额
			bidRequest.setCurrentRate(br.getCurrentRate()); // 借款利率
			bidRequest.setDescription(br.getDescription()); // 借款描述
			bidRequest.setDisableDays(br.getDisableDays()); // 招标天数
			bidRequest.setMinBidAmount(br.getMinBidAmount()); // 最小投标金额
			bidRequest.setReturnType(br.getReturnType()); // 借款类型
			bidRequest.setMonthes2Return(br.getMonthes2Return()); // 还款月数
			bidRequest.setTitle(br.getTitle()); // 借款标题

			/* 设置相关参数值 */
			bidRequest.setApplyTime(new Date()); // 设置申请时间

			/* 总汇报的计算 使用已经包装好的类 直接调用即可 */
			bidRequest.setBidRequestState(BidConst.BIDREQUEST_STATE_PUBLISH_PENDING);
			bidRequest.setCreateUser(UserContext.getcurrent());
			bidRequest.setTotalRewardAmount(CalculatetUtil.calTotalInterest(br.getReturnType(),
					br.getBidRequestAmount(), br.getCurrentRate(), br.getMonthes2Return()));
			/**
			 * 保存对象 ，并为当前用户添加对应的验证码
			 */
			this.bidRequestMapper.insert(bidRequest);
			UserInfo userInfo = this.userinfoService.getCurrent();
			userInfo.setBitState(BitStatesUtils.OP_HAS_BIDREQUEST_PROCESS);
			this.userinfoService.update(userInfo);

		}  
	}

	/**
	 * 借款审核对象的分页页面
	 */
	@Override
	public PageResult query(BidRequestQueryObject qo) {
		
		int count = this.bidRequestMapper.queryForCount(qo);
		if(count > 0){
			List<BidRequest> list = this.bidRequestMapper.query(qo);
			return new PageResult(list, count, qo.getCurrentPage(), qo.getPageSize());
		}else{
			return PageResult.empty(qo.getPageSize());
		}
		
	}
 

}
