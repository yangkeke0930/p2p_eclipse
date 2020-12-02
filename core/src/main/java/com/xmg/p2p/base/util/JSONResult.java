package com.xmg.p2p.base.util;

import lombok.Getter;
import lombok.Setter;

/**
 * 用来存放后台响应回来的AJAX数据的
 * 
 * @author 78158
 *
 */
@Setter
@Getter
public class JSONResult {
	
	private boolean success = true;

	private String msg;
	
	/**
	 * 无参构造方法
	 */
	public JSONResult() {
		super();
	}

	/**
	 * 一个参数的构造方法
	 * @param msg
	 */
	public JSONResult(String msg) {
		super();
		this.msg = msg;
	}
	
	/**
	 * 两个参数的构造方法
	 * @param success
	 * @param msg
	 */
	public JSONResult(boolean success, String msg) {
		super();
		this.success = success;
		this.msg = msg;
	}

}
