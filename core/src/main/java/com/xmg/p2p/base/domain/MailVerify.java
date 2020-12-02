package com.xmg.p2p.base.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * 邮箱验证
 * @author Yang Ke Ke
 *
 */
@Setter
@Getter
public class MailVerify extends BaseDomain {
		
		/**
		 * 发送短息的id
		 */
		private Long userinfoId;
		
		/**
		 * 查询邮箱验证的uuid
		 */
		private String uuid;
		
		/**
		 * 发送邮箱的时间
		 */
		private Date sendDate;
		
		/**
		 * 发送右键的内容
		 */
		private String email;		 
}
