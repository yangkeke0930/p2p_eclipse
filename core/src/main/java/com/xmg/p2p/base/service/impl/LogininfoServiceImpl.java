package com.xmg.p2p.base.service.impl;

import java.util.Date;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmg.p2p.base.domain.Account;
import com.xmg.p2p.base.domain.Iplog;
import com.xmg.p2p.base.domain.Logininfo;
import com.xmg.p2p.base.domain.UserInfo;
import com.xmg.p2p.base.mapper.IplogMapper;
import com.xmg.p2p.base.mapper.LogininfoMapper;
import com.xmg.p2p.base.service.IAccountService;
import com.xmg.p2p.base.service.ILogininfoService;
import com.xmg.p2p.base.service.IUserinfoService;
import com.xmg.p2p.base.util.BidConst;
import com.xmg.p2p.base.util.MD5;
import com.xmg.p2p.base.util.UserContext;
/**
 * 登录注册功能
 * @author 78158
 *
 */
@Service
public class LogininfoServiceImpl implements ILogininfoService {

	// 加入dao的依赖
	@Autowired
	private LogininfoMapper logininfoMapper;
	
	/**
	 * 添加关于用户  账户相关的业务信息。综合判断用户登录的情况 
	 */
	@Autowired
	private IUserinfoService userInfoService;
	@Autowired
	private IAccountService acccountService;
	@Autowired
	private IplogMapper iplogMapper;
	
	/**
	 * 用户注册
	 */
	public void register(String username, String password) {
		
		// 判断用户名是否存在
		int count = logininfoMapper.getCountByUsername(username);
		// 如果不存在就保存数据
		if (count <= 0) {
			Logininfo li = new Logininfo();
			li.setUsername(username);
			li.setPassword(MD5.encode(password));
			li.setState(Logininfo.STATE_NORMAL);
			//增加区分前后端用户的
			li.setUserType(Logininfo.USER_CLIENT);
			this.logininfoMapper.insert(li);
			
			//初始化账户
			Account account = new Account();
			account.setId(li.getId());
			this.acccountService.add(account);
			
			//初始化用户信息
			UserInfo ui = new UserInfo();
			ui.setId(li.getId());
			this.userInfoService.add(ui);
			
			
		} else {
			// 如果存在就抛出运行时异常
			throw new RuntimeException("用户名已存在");
		}
	}

	/**
	 * 用户注册远程查看用户名是否存在
	 */
	@Override
	public boolean checkUsername(String username) {
		// 若存在，则返回true
		return this.logininfoMapper.getCountByUsername(username) > 0;
	}

	/**
	 * 用户登录
	 */
	@Override
	public Logininfo login(String username, String password, String ip, int userType) {
		Logininfo current = this.logininfoMapper.login(username, MD5.encode(password), userType);
		
		/**
		 * 实现登录日志的功能
		 * 添加区分登录用户是前端还是后端用户的功能
		 */
		Iplog iplog = new Iplog();
		iplog.setIp(ip);
		iplog.setLoginTime(new Date());
		iplog.setUserName(username);
		iplog.setUserType(userType);
		// 判断用户是否存在
		if(current != null){
			//如果存在  则放置到usercontext中
			UserContext.putCurrent(current);
			iplog.setState(Iplog.STATE_SUCCESS);
		}else{
			//如果不存在
			iplog.setState(Iplog.STATE_FAILED);
			//throw new RuntimeException("用户名或密码错误！");
		}
		 iplogMapper.insert(iplog);
		 return current;
	}

	/**
	 * 初始化第一个管理员
	 */
	@Override
	public void initAdmin() {
		/**
		 * 1、判断第一个管理员是否存在
		 * 2、如没有，则创建一个管理员
		 */
		int count = this.logininfoMapper.countByUserType(Logininfo.USER_MANAGER);
		if( count == 0){
		 //创建管理员对象
			Logininfo admin = new Logininfo();
			admin.setUsername(BidConst.DEFAULT_ADMIN_USER);
			admin.setPassword(MD5.encode(BidConst.DEFAULT_ADMIN_PASSWORD));
			admin.setState(Logininfo.STATE_NORMAL);
			admin.setUserType(Logininfo.USER_MANAGER);
			this.logininfoMapper.insert(admin);
			
		}
	}

 

}
