package com.xmg.p2p.business.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.xmg.p2p.base.domain.BaseDomain;
import com.xmg.p2p.base.domain.Logininfo;
import com.xmg.p2p.base.util.BidConst;

import lombok.Getter;
import lombok.Setter;

/**
 * 借款对象
 * 
 * @author Yang Ke Ke
 *
 */
@Setter
@Getter
public class BidRequest extends BaseDomain {

	private int version;						//版本号
	private int returnType;						//还款类型（等额利息）
	private int bidRequestType;					//借款类型(信用标)
	private int bidRequestState; 				//借款状态
	private BigDecimal bidRequestAmount;		//借款金额
	private BigDecimal currentRate;				//年化利率
	private BigDecimal minBidAmount;			//最小投标金额
	private int monthes2Return;					//还款月数
	private int bidCount = 0;					//已投标次数（冗余数据）
	private BigDecimal totalRewardAmount;		//总汇报金额
	private BigDecimal currentSum = BidConst.ZERO;			//当前已投标金额 默认
	private String title;						//借款标题
	private String description;					//借款描述
	private String note;						//风控意见
	private Date disableDate;					//招标截止日期
	private int disableDays;					//招标天数
	private Logininfo createUser;				//借款人     createuser_id
	private List<Bid> bids;						//针对该借款的投标信息
	private Date applyTime;						//申请时间
	private Date publishTime;					//发布时间

}
