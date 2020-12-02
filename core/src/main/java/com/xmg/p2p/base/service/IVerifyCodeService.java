package com.xmg.p2p.base.service;
/**
 * 手机验证码相关的服务类
 * @author 78158
 *
 */
public interface IVerifyCodeService {
	
	//给指定的手机号发送验证码
	public void sendVerifyCode(String phoneNumber);
	
	 /**
	  * 用户验证手机验证码是否合法
	  * @param phoneNumber
	  * @param verifyCode
	  * @return
	  */
	public Boolean verify(String phoneNumber, String verifyCode);

}
 