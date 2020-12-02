package com.xmg.p2p.base.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * 基础的审核对象
 * @author Yang Ke Ke
 *
 */
@Setter
@Getter

abstract public class BaseAuditDomain extends BaseDomain {

	/**
	 * 审核正常（待审核）
	 */
	public static final int STATE_NORML = 0;  
	/**
	 * 审核通过
	 */
	public static final int STATE_AUDIT = 1;  
	/**
	 * 审核拒绝
	 */
	public static final int STATE_REJECT = 2; 

	/**
	 * 审核人相关的资料内容
	 */
	
	/**
	 * 状态
	 */
	protected int state;  
	/**
	 *  审核人
	 */
	protected Logininfo auditor;  
	/**
	 * 申请人
	 */
	protected Logininfo applier; 
	/**
	 * 审核备注
	 */
	protected String remark; 
	/**
	 * 申请时间
	 */
	protected Date applyTime; 
	/**
	 * 审核时间
	 */
	protected Date auditTime; 

	/**
	 * 关于审核时间的显示
	 */
	public String getStateDisplay() {
		switch (state) {
		case STATE_NORML:
			return "待审核";
		case STATE_AUDIT:
			return "审核通过";
		case STATE_REJECT:
			return "审核拒绝";
		default:
			return "";
		}
	}
}
