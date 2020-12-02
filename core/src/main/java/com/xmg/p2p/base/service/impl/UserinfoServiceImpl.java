package com.xmg.p2p.base.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xmg.p2p.base.domain.MailVerify;
import com.xmg.p2p.base.domain.UserInfo;
import com.xmg.p2p.base.mapper.MailVerifyMapper;
import com.xmg.p2p.base.mapper.UserInfoMapper;
import com.xmg.p2p.base.service.IMailService;
import com.xmg.p2p.base.service.IUserinfoService;
import com.xmg.p2p.base.service.IVerifyCodeService;
import com.xmg.p2p.base.util.BidConst;
import com.xmg.p2p.base.util.BitStatesUtils;
import com.xmg.p2p.base.util.DateUtil;
import com.xmg.p2p.base.util.UserContext;

/**
 * 用户相关信息的实现类
 * 
 * @author 78158
 *
 */
@Service
public class UserinfoServiceImpl implements IUserinfoService {

	/**
	 * 加入依赖
	 */

	// 注入验证邮件的网址
	@Value("${mail.hostUrl}")
	private String hostUrl;

	@Autowired
	private UserInfoMapper userInfoMapper;

	// 加入关于验证码相关的业务类
	@Autowired
	private IVerifyCodeService verifyCodeService;

	// 加入绑定邮箱邮件相关的业务类
	@Autowired
	private MailVerifyMapper mailVerifyMapper;
	
	//加入验证邮箱的验证类
	@Autowired
	private IMailService mailService;

	@Override
	public void update(UserInfo userInfo) {
		int ret = this.userInfoMapper.updateByPrimaryKey(userInfo);
		if (ret == 0) {
			throw new RuntimeException("乐观锁失败!UserInfo:" + userInfo.getId());
		}
	}

	@Override
	/**
	 * 初始化用户信息
	 */
	public void add(UserInfo ui) {
		this.userInfoMapper.insert(ui);
	}

	/**
	 * 登录个人页面后，加载个人用户信息
	 */
	public UserInfo get(Long id) {
		return this.userInfoMapper.selectByPrimaryKey(id);
	}

	/**
	 * 绑定手机号
	 */
	@Override
	public void bindPhone(String phoneNumber, String verifyCode) {
		/**
		 * 这是一个具体的业务实现类，必须实现与业务相关的代码
		 */
		// 1、首先判断当前用户是否已经绑定验证码
		UserInfo current = this.get(UserContext.getcurrent().getId());
		// 2、判断是否已经绑定验证码
		if (!current.getIsBindPhone()) {
			/*
			 * 当前为没有绑定验证码的情况
			 */
			// 验证验证码是否合法
			Boolean ret = this.verifyCodeService.verify(phoneNumber, verifyCode);
			// 判断ret的结果
			if (ret) {
				// 合法，绑定手机
				current.addState(BitStatesUtils.OP_BIND_PHONE);
				// 改变用户设置手机绑定的状态码
				current.setPhoneNumber(phoneNumber);
				this.update(current);
			} else {
				// 不合法，则抛出异常
				throw new RuntimeException("绑定手机失败");
			}

		}
		// 3、已经绑定的情况,就不理他了。

	}

	/**
	 * 绑定邮箱邮件
	 */
	@Override
	public void sendVerifyEmail(String email) {
		/*
		 * 1、由于当前用户没有绑定邮箱，所以要发送邮件必须要获取当前用户 。获取当前操作的用户
		 */
		UserInfo current = this.getCurrent();
		/*
		 * 2、判断当前用户没有绑定邮箱，因为没有绑定邮箱，所以才要去绑定。绑定了的话这里暂时不做任何处理。
		 */
		if (!current.getIsBindEmail()) {
			/*
			 * 3、创建发送邮件的格式
			 */
			String uuid = UUID.randomUUID().toString();
			StringBuilder content = new StringBuilder(100).append("点击<a href='").append(this.hostUrl)
					.append("bindEmail.do?key=").append(uuid).append("'>这里</a>完成邮箱绑定,有效期为")
					.append(BidConst.VERIFYEMAIL_VAILDATE_DAY).append("天");
			/**
			 * 抛出异常，正常情况是发送验证邮件。异常为发送邮件失败
			 */
			try {
				
				// 临时测试
				//System.out.println("发送邮件" + email + "邮件的内容" + content);
				
				//发送邮件
				//content.toString是将邮件内容转化为字符串
				mailService.sendMail(email, "邮箱验证邮件", content.toString());
				
				// 首先我们要构造一个邮件验证对象
				// 但是这个对象是要通过UUID来得到的
				MailVerify mv = new MailVerify();
				/*
				 * 设置属性
				 */
				// 邮件内容
				mv.setEmail(email);
				// 邮件发送的时间
				mv.setSendDate(new Date());
				mv.setUserinfoId(current.getId());
				mv.setUuid(uuid);
				// 将设置好的对象mv插入到数据库中
				this.mailVerifyMapper.insert(mv);

			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("验证邮件发送失败");
			}

		}
	}

	/**
	 * 获取当前用户
	 */
	@Override
	public UserInfo getCurrent() {
		return this.get(UserContext.getcurrent().getId());
	}

	/**
	 * 执行邮箱绑定
	 */
	@Override
	public void bindEmail(String uuid) {
		/*
		 * 1、通过uuid来获取mailverify对象
		 *    通过mailverify对象来获取当前用户
		 * 2、判断当前用户没有绑定邮箱
		 * 
		 * 4、判断有效期
		 * 5、修改用户状态。给用户设置邮箱
		 */
		
		/*
		 * 通过uuid来获取mailverify对象
		 */
		MailVerify mv = this.mailVerifyMapper.selectByUUID(uuid);
		
		/*
		 * 判断当前用户没有绑定邮箱
		 */
		if (mv != null){
			/*
			 * 通过mailverify对象来获取当前用户
			 */
			UserInfo current = this.get(mv.getUserinfoId());
			
			if(!current.getIsBindEmail()){
				/*
				 * 判断当前用户不为空且在有效期内
				 */
				if(mv != null && 
						DateUtil.secondsBetween(mv.getSendDate(), new Date()) <= BidConst.VERIFYEMAIL_VAILDATE_DAY * 24 * 3600 ){
					/*
					 * 修改用户状态码
					 */
					current.addState(BitStatesUtils.OP_BIND_EMAIL);
					/*
					 * 给用户设置邮箱
					 */
					current.setEmail(mv.getEmail());
					this.update(current);
					return;
				}
				
			}
		}
		
		throw new RuntimeException("绑定邮箱失败");
	}

	/**
	 * 更新用户基本数据
	 */
	@Override
	public void updateBasicInfo(UserInfo userInfo) {
		//获取当前的用户信息
		UserInfo old = this.getCurrent();
		//拷贝需要修改的内容
		old.setEducationBackground(userInfo.getEducationBackground());
		old.setIncomeGrade(userInfo.getIncomeGrade());
		old.setMarriage(userInfo.getIncomeGrade());
		old.setKidCount(userInfo.getKidCount());
		old.setHouseCondition(userInfo.getHouseCondition());
		
		//判断当前用户的状态码    判断当前用户是已经填写基本资料  
		//更新用户的状态码 因为第一次啊填写个人资料是从我要借款那里点击进去的
		//如果没有填写资料，则添加状态码信息
		if(!old.getIsBasicInfo()){
			old.addState(BitStatesUtils.OP_BASIC_INFO);
		}
		//执行更新用户资料
		this.update(old);
	}

	/**
	 * 用于用户得autocomplate
	 */
	@Override
	public List<Map<String, Object>> autocomplate(String keyword) {
		
		return this.userInfoMapper.autocomplate(keyword);
	}


}
