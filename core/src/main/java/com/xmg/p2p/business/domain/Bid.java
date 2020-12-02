package com.xmg.p2p.business.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.xmg.p2p.base.domain.BaseDomain;
import com.xmg.p2p.base.domain.Logininfo;

import lombok.Getter;
import lombok.Setter;

/**
 * 一次投标对象
 * 
 * @author Yang Ke Ke
 *
 */
@Setter
@Getter
public class Bid extends BaseDomain {

	private BigDecimal actualRate;			//年化利率
	private BigDecimal availableAmount;		//这次投标的金额
	private Long bidRequestId;				//关联借款
	private String bidRequestTitle;			//冗余数据。等同于借款标题
	private Logininfo bidUser;				//投标人
	private Date bidTime;					//投标时间
	private int bidRequestState;			//不保存到数据库中，只做查询使用 借款人的状态
}