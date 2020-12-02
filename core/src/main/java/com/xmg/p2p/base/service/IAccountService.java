package com.xmg.p2p.base.service;

import com.xmg.p2p.base.domain.Account;

/**
 * 账户相关信息服务
 * @author 78158
 *
 */
public interface IAccountService {
	
	/**
	 * 写完mapper之后，回来立即写service，因为这个service是支持乐观锁的。
	 * @param account
	 */
	public void update(Account account);

	public void add(Account account);

	public Account get(Long id);
	
	/**
	 * 得到当前登录用户对应的账户信息
	 * @return
	 */
	public Account getCurrent();
	
}
