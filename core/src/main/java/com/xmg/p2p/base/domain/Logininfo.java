package com.xmg.p2p.base.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * 用户登录的相关信息
 * 	1、账号
 *  2、密码
 *  3、状态
 *  4、前台还是后台用户
 * @author Yang Ke Ke
 *
 */
@Setter
@Getter
public class Logininfo extends BaseDomain {
	
	/**
	 * 账户正常状态
	 */
	public static final int STATE_NORMAL = 0;
	
	/**
	 * 账户锁定的状态
	 */
	public static final int STATE_LOCK = 1;
	
	/**
	 * 后台用户标识
	 */
	public static final int USER_MANAGER = 0; 
	
	/**
	 * 前台用户标识
	 */
	public static final int USER_CLIENT= 1;		
	
	/**
	 * 登录用户的账号
	 */
	private String username;

	/**
	 * 登录用户的密码
	 */
	private String password;
 
	/**
	 * 登录用户的状态码
	 */
	private int state;
	
	/**
	 * 区别前后台用户的属性
	 */
	private int userType;

}