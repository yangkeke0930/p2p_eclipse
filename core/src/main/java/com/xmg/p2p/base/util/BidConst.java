package com.xmg.p2p.base.util;

import java.math.BigDecimal;

/**
 * 用户系统中表示常量的
 * 
 * @author 78158
 *
 */
public class BidConst {
	/*
	 * 定义存储精度
	 */
	public static final int STORE_SCALE = 4;
	/**
	 * 定义运算精度
	 */
	public static final int CAL_SCALE = 8;
	/**
	 * 定义显示精度
	 */
	public static final int DISPLAY_SCALE = 2;

	/**
	 * 定义系统级别的
	 */
	public static final BigDecimal ZERO = new BigDecimal("0.0000");

	/**
	 * 定义初始授信额度
	 */
	public static final BigDecimal INIT_BORROW_LIMIT = new BigDecimal("5000");

	/**
	 * 设置超级管理员的初始密码和账号
	 * 
	 */
	public static final String DEFAULT_ADMIN_USER = "admin";
	public static final String DEFAULT_ADMIN_PASSWORD = "1111";

	/**
	 * 定义手机号码的有效时间
	 */
	public static final int VERIFYCODE_VALIDATE_SECOND = 300;

	/**
	 * 定义邮箱验证链接的有效时间（天数）
	 */
	public static final int VERIFYEMAIL_VAILDATE_DAY = 5;

	/**
	 * 能借款需要达到的最低风控分数
	 */
	public static final int BASE_BORROW_SCORE = 30;

	// ---------------------借款状态---------------------------
	
	
	/**
	 * 系统规定得最小投标金额
	 */
	public static final BigDecimal SMALLEST_BID_AMOUNT = new BigDecimal("50.0000");// 系统规定的最小投标金额
	
	/**
	 * 系统规定得最小借款金额
	 */
	public static final BigDecimal SMALLEST_BIDREQUEST_AMOUNT = new BigDecimal("500.0000");// 系统规定的最小借款金额
	
	/**
	 * 最小借款利息
	 */
	public static final BigDecimal SMALLEST_CURRENT_RATE = new BigDecimal("5.0000");
	
	/**
	 * 最大借款利息
	 */
	public static final BigDecimal MAX_CURRENT_RATE = new BigDecimal("20.0000");
	
	
	//---------------------------------借款相关的状态码--------------------
	/**
	 * 借款待发布的状态码
	 */
	public static final int BIDREQUEST_STATE_PUBLISH_PENDING = 0;	
	/**
	 * 借款待审核的状态码
	 */
	public static final int BIDREQUEST_STATE_PROCESS = 0;
	public static final int RETURN_TYPE_MONTH_INTEREST_PRINCIPAL = 0;
	public static final int RETURN_TYPE_MONTH_INTEREST = 0;
}
