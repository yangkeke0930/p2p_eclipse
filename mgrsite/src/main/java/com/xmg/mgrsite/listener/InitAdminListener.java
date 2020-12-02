package com.xmg.mgrsite.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.xmg.p2p.base.service.ILogininfoService;
/**4
 * 初始化管理员的监听器
 * @author 78158
 *
 */
@Component
public class InitAdminListener implements ApplicationListener<ContextRefreshedEvent> {
	
	//注入依赖
	@Autowired
	private ILogininfoService logininfoService;

	/**
	 * 该方法是在监听到指定的事件之后，所要做的事情。 contextRefreshedEvent就是这次监听的事件对象
	 */
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		/**
		 * 初始化第一个管理员,具体的实现方法交给service
		 */
		logininfoService.initAdmin();
	}

}
