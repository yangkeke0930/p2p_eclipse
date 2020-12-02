package com.xmg.p2p.base.service;

import com.xmg.p2p.base.query.IplogQueryObject;
import com.xmg.p2p.base.query.PageResult;

/**
 * 查询登录日志的业务类
 * @author 78158
 *
 */
public interface IIplogService {
	
	 
	 /**
	  * 分页
	  * @param qo
	  * @return
	  */
	//查询整个页面的对象，如果要分页需要总记录数
	public PageResult query(IplogQueryObject qo);

}
