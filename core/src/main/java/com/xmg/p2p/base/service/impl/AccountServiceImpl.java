package com.xmg.p2p.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmg.p2p.base.domain.Account;
import com.xmg.p2p.base.mapper.AccountMapper;
import com.xmg.p2p.base.service.IAccountService;
import com.xmg.p2p.base.util.UserContext;

/**
 * 账户相关服务的实现类
 * 
 * @author 78158
 *
 */
@Service
public class AccountServiceImpl implements IAccountService {

	@Autowired
	/**
	 * service依赖于DAO
	 */
	private AccountMapper accountMapper;

	@Override
	public void update(Account account) {

		int ret = this.accountMapper.updateByPrimaryKey(account);

		if (ret == 0) {
			throw new RuntimeException("乐观锁失败！Account:" + account.getId());
		}
	}

	@Override
	/**
	 * 初始化用户信息
	 */
	public void add(Account account) {
		this.accountMapper.insert(account);
	}

	/**
	 * 登入个人页面后，加载用户信息
	 */
	public Account get(Long id) {
		return this.accountMapper.selectByPrimaryKey(id);
	}

	/**
	 * 得到当前登录用户对应的账户信息
	 */
	@Override
	public Account getCurrent() {
		// TODO Auto-generated method stub
		return this.get(UserContext.getcurrent().getId());
	}

}
