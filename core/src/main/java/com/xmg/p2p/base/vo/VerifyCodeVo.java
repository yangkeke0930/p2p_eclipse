package com.xmg.p2p.base.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * 存放验证码相关的内容，该对象是放在session中的
 * @author 78158
 *
 */
@Setter
@Getter
public class VerifyCodeVo {
	
	private String verifyCode;	//验证码
	private String phoneNumber;	//手机号
	private Date lastSendTime;	//最后成功发送的时间
}
