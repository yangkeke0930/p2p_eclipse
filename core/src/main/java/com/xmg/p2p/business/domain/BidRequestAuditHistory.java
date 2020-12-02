package com.xmg.p2p.business.domain;

import com.xmg.p2p.base.domain.BaseAuditDomain;

import lombok.Getter;
import lombok.Setter;

/**
 * 一个借款项目的审核历史
 * 
 * @author Yang Ke Ke
 *
 */
@Setter
@Getter
public class BidRequestAuditHistory extends BaseAuditDomain {

	/**
	 * 各阶段的审核状态
	 */
	public static final int PUBLISH_AUDIT = 0;
	public static final int FULL_AUDIT_1 = 1; // 满标一审
	public static final int FULL_AUDIT_2 = 2; // 满标二审

	private Long bidRequestId; // 借款id
	private int auditType; // 借款的类型（当前处于那种审核状态）
}
