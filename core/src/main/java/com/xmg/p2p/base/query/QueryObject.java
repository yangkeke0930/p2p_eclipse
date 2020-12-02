package com.xmg.p2p.base.query;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
abstract public class QueryObject {
	
	private Integer currentPage = 1;	//当前页面
	private Integer pageSize = 5;		//每个页面展示的记录数
	
	public int getStart(){
		return (currentPage - 1) * pageSize;
	}

	 
}
