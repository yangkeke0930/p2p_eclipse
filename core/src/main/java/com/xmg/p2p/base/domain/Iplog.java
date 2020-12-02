package com.xmg.p2p.base.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * 登录日志
 * 
 * @author 78158
 *
 */
@Setter
@Getter
public class Iplog extends BaseDomain {
 
	/**
	 * 登录成功状态
	 */
	public static final int STATE_SUCCESS = 1;
	
	/**
	 * 登录失败状态
	 */
	public static final int STATE_FAILED = 0;
	
	/**
	 * ip
	 */
	private String ip;
	
	/**
	 * 登录时间
	 */
	private Date loginTime;
	
	/**
	 * 登录的用户名
	 */
	private String userName;
	
	/**
	 *登录状态 
	 */
	private int state;
	
	/**
	 * 用户登录类型
	 */ 
	private int userType;

 
	/**
	 * 使用中文提示的登录状态
	 * @return
	 */
	public String getStateDisplay() {
		return state == STATE_SUCCESS ? "登陆成功" : "登录失败";
	}


	/**
	 * 判断用户是前端用户还是后天管理员
	 * @return
	 */
	public String getUserTypeDisplay() {
		return userType == Logininfo.USER_CLIENT ? "前端用户" : "后台管理员";
	}
}
