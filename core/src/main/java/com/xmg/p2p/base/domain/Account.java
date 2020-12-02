package com.xmg.p2p.base.domain;

import java.math.BigDecimal;

import com.xmg.p2p.base.util.BidConst;

import lombok.Getter;
import lombok.Setter;

/**
 * 账户
 * 
 * @author 78158
 *
 */
@Setter
@Getter
public class Account extends BaseDomain {
	
	/**
	 * 版本信息，使用乐观锁
	 */
	private int version;
	/**
	 * 交易密码
	 */
	private String tradePassword;  
	/**
	 * 账户可用余额
	 */
	private BigDecimal usableAmount = BidConst.ZERO; 
	/**
	 * 账户冻结余额
	 */
	private BigDecimal freezedAmount = BidConst.ZERO;  
	/**
	 * 账户代收利息
	 */
	private BigDecimal unReceiveInterest = BidConst.ZERO;
	/**
	 * 账户代收本金
	 */
	private BigDecimal unReceivePrincipal = BidConst.ZERO; 
	/**
	 * 账户待还金额
	 */
	private BigDecimal unReturnAmount = BidConst.ZERO; 
	
	/**
	 * 账户剩余守信额度
	 */
	private BigDecimal remainBorrowLimit = BidConst.INIT_BORROW_LIMIT; 
	
	/**
	 * 账户授信额度
	 */
	private BigDecimal borrowLimit = BidConst.INIT_BORROW_LIMIT; // 账户授信额度 

	
	/**
	 * 账户总额
	 * @return
	 */
	public BigDecimal getTotalAmount(){
		return  usableAmount.add(this.freezedAmount).add(this.unReceivePrincipal);
	}
}
