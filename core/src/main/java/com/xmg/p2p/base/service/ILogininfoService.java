package com.xmg.p2p.base.service;

import com.xmg.p2p.base.domain.Logininfo;

/**
 * 与登录相关的业务代码
 * @author 78158
 *
 */
public interface ILogininfoService {

	/**
	 * 用户注册
	 * @param username
	 * @param password
	 */
	public void register(String username, String password);

	/**
	 * 远程校验用户名是否已经存在。
	 * @param username
	 * @return
	 */
	public boolean checkUsername(String username);
	
	/**
	 * 用户登录
	 * 在用户登录的时候加入登录日志的功能
	 * @param username
	 * @param password
	 * @param userClient 
	 * 增加登录时区分前后端用户的功能
	 */
	public Logininfo login(String username, String password, String ip, int userClient);
	
	/**
	 * 初始化管理员
	 */
	public void initAdmin();

	
}
