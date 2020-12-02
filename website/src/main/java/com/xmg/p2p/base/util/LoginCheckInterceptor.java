package com.xmg.p2p.base.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
/**
 * 专门用户登录检查的拦截器
 * @author 78158
 *
 */
public class LoginCheckInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		/**
		 * 判断登陆的逻辑
		 */
		if(handler instanceof HandlerMethod){
			HandlerMethod hm = (HandlerMethod) handler;
			RequireLogin rl = hm.getMethodAnnotation(RequireLogin.class);
			/**
			 * 继续判断该方法上是否存在注解且该方法上没有用户信息
			 */
			if(rl != null && UserContext.getcurrent() == null){
				/*
				 * 转发到登录页面
				 * 由于该拦截器上么有视图解析器。所以使用/login.html的方式。
				 */
				response.sendRedirect("/login.html");
				/*
				 * 由于spring拦截器是一个套一个接着往下传的，所以需要将其打断
				 */
				return false;
			}
		}
		
		return super.preHandle(request, response, handler);
	}
  
}
