package com.xmg.p2p.business.query;

import com.xmg.p2p.base.query.QueryObject;

import lombok.Getter;
import lombok.Setter;
/**
 * 借款查询对象
 * @author Yang Ke Ke
 *
 */
@Setter
@Getter
public class BidRequestQueryObject extends QueryObject {

	/**
	 * 据现在已知的需要通过状态码来查询
	 */
	private int bidRequestState = -1;		//待审核的状态
	
}
