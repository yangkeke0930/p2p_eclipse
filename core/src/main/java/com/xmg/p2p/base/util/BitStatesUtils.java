package com.xmg.p2p.base.util;

/**
 * 用户状态类，记录用户在平台使用系统中所有的状态。
 * 
 * @author Administrator
 */
public class BitStatesUtils {
	
	/**
	 * 用户绑定手机的状态码
	 */
	public final static Long OP_BIND_PHONE = 1L << 0;  
	
	/**
	 * 
	 */
	public final static Long OP_BIND_EMAIL = 1L << 1;  //定义用户绑定邮箱
	
	public final static Long OP_BASIC_INFO = 1L << 2;	//用户是否填写基本资料
	
	public final static Long OP_REAL_AUTH = 1L  << 3;    //用户是否已经实名认证
	
	public final static Long OP_VIDEO_AUTH = 1L << 4;	 //用户是否已经进行视频认证
	
	public final static Long OP_HAS_BIDREQUEST_PROCESS = 1L << 5; //用户是否有一个正在进行的贷款流程
    
	/*
	 * 测试1是否可以移位
	 */
    public static void main(String[] args) {
		
	}

	/**
	 * @param states
	 *            所有状态值
	 * @param value
	 *            需要判断状态值
	 * @return 是否存在
	 */
	public static boolean hasState(long states, long value) {
		return (states & value) != 0;
	}

	/**
	 * @param states
	 *            已有状态值
	 * @param value
	 *            需要添加状态值
	 * @return 新的状态值
	 */
	public static long addState(long states, long value) {
		if (hasState(states, value)) {
			return states;
		}
		return (states | value);
	}

	/**
	 * @param states
	 *            已有状态值
	 * @param value
	 *            需要删除状态值
	 * @return 新的状态值
	 */
	public static long removeState(long states, long value) {
		if (!hasState(states, value)) {
			return states;
		}
		return states ^ value;
	}
}
