package com.xmg.p2p.base.util;

import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.xmg.p2p.base.domain.Logininfo;
import com.xmg.p2p.base.vo.VerifyCodeVo;

/**
 * 用来存放用户的上下文
 * 
 * @author 78158
 *
 */
public class UserContext {

	// 创建usersession
	public static final String USER_IN_SESSION = "logininfo";
	// 创建verifyCode session
	public static final String VERIFYCODE_IN_SESSION = "verify_in_session";
	

	// 反向获取session的方法,请查看RequestContextLister.requestInitialized打包过程

	public static HttpSession getSession() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();

	}

	// 将当前用户放置在session中
	public static void putCurrent(Logininfo current) {
		getSession().setAttribute(USER_IN_SESSION, current);
	}

	// 获取session
	public static Logininfo getcurrent() {

	return (Logininfo) getSession().getAttribute(USER_IN_SESSION);
	}
	
	/**
	 * 获取短信验证码
	 */
	public static VerifyCodeVo getCurrentVerifyCode(){
		 return (VerifyCodeVo) getSession().getAttribute(VERIFYCODE_IN_SESSION);	 
	}
	
	 
	/**
	* 将verifcode的对象放置在session中
	*/
	public static void putVerifyCode(VerifyCodeVo vc){
		getSession().setAttribute(VERIFYCODE_IN_SESSION,  vc);
	}

}
