package com.xmg.p2p.base.service.impl;

import java.util.Properties;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.xmg.p2p.base.service.IMailService;

/**
 * 专门用于发送邮件服务类的实现类
 * 
 * @author Yang Ke Ke
 *
 */
@Service
public class MailImpl implements IMailService {

	@Value("${mail.host}")
	private String host;

	@Value("${mail.username}")
	private String username;

	@Value("${mail.password}")
	private String password;

	@Override
	public void sendMail(String target, String title, String content) {
		try {
			JavaMailSenderImpl sender = new JavaMailSenderImpl();
			// 设置SMTP服务器地址
			sender.setHost(host);
			// 创建邮件对象
			MimeMessage message = sender.createMimeMessage();
			// 创建一个邮件对象助手
			MimeMessageHelper helper = new MimeMessageHelper(message);
			// 通过邮件助手来设置相关的内容
			// 设置目标邮件
			helper.setTo(target);
			// 设置发送邮件
//			helper.setFrom("p2p");
			helper.setFrom("Admin@xmg.com", "系统管理员");		//这个地方的内容一定要和propertie中的username一致
			// 设置邮件标题
			helper.setSubject(title);
			// 设置邮件内容
			helper.setText(content, true);

			// 设置发送邮件的账号和密码
			sender.setUsername(username);
			sender.setPassword(password);

			// 进行服务器认证
			Properties prop = new Properties();
			//身份验证
			prop.put("mail.smtp.auth", "true");
			//超时
			prop.put("mail.smtp.timeout", "25000");

			sender.setJavaMailProperties(prop);

			// 发送邮件
			sender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("发送邮件失败！");
		}
	}

}
