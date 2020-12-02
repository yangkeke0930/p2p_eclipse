package com.xmg.p2p.base.service;
/**
 * 专门用于发送邮件的服务类
 * @author Yang Ke Ke
 *
 */
public interface IMailService {

	/**
	 * 发送邮件
	 * @param target  目标邮件地址
	 * @param title   邮件标题
	 * @param content 邮件内容
	 */
	public void sendMail(String target, String title, String content );
}
