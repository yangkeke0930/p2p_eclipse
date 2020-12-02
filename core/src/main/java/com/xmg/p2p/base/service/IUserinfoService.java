package com.xmg.p2p.base.service;

import java.util.List;
import java.util.Map;

import com.xmg.p2p.base.domain.UserInfo;

/**
 * 用户相关信息服务
 * @author 78158
 *
 */
public interface IUserinfoService {

	/**
	 * 有乐观锁支持
	 * @param userInfo
	 */
	public void update (UserInfo userInfo);

	/**
	 * 添加userinfo
	 * @param ui
	 */
	public void add(UserInfo ui);

	public UserInfo get(Long id);
	
	/**
	 * 绑定手机号
	 * @param phoneNumber
	 * @param verifyCode
	 */
	public void bindPhone(String phoneNumber, String verifyCode);

	/**
	 * 发送绑定邮箱邮件
	 * @param email
	 */
	public void sendVerifyEmail(String email);
	
	/**
	 * 得到当前的userinfo对象。目的是为了通过当前的userInfo对象来获取当前的需要发送邮件的对象。
	 * @return
	 */
	public UserInfo getCurrent();

	/**
	 * 执行邮箱绑定
	 * @param key
	 */
	public void bindEmail(String uuid);
	
	/**
	 * 更新用户基本数据
	 */
	public void updateBasicInfo(UserInfo userInfo);
	/**
	 * 用于用户得complate
	 * @param keyword
	 * @return
	 */
	public List<Map<String, Object>> autocomplate(String keyword);

	
	
}
