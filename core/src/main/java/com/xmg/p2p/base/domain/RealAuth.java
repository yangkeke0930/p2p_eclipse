package com.xmg.p2p.base.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import lombok.Getter;
import lombok.Setter;

/**
 * 实名认证对象
 * 
 * @author Yang Ke Ke
 *
 */
@Setter
@Getter
public class RealAuth extends BaseAuditDomain {
	
	/**
	 * 性别0代表男人
	 */
	public static final int SEX_MALE = 0; 
	
	/**
	 * 性别1代表女人
	 */
	public static final int SEX_FEMALE = 1;  

	/**
	 * 0未审核正常的标识
	 */
	public static final int STATE_NORML = 0;
	
	/**
	 * 1为审核通过的标识
	 */
	public static final int STATE_AUDIT = 1; // 审核通过
	
	/**
	 * 2为审核拒绝的标识
	 */
	public static final int STATE_REJECT = 2; // 审核拒绝

	/**
	 * 申请人的相关资料内容
	 */
	/**
	 * 申请人
	 */
	private Logininfo applier; 
	
	/**
	 * 用户名
	 */
	private String realName; 
	
	/**
	 * 性别
	 */
	private int sex;  
	
	/**
	 * 证件号码
	 */
	private String idNumber;
	
	/**
	 * 出生日期
	 */
	private String bornDate;
	
	/**
	 * 证件地址
	 */
	private String address; 
	
	/**
	 * 身份证正面图片的地址
	 */
	private String image1;
	
	/**
	 * 身份证反面图片的地址
	 */
	private String image2;  

	/**
	 * 审核人相关的资料内容
	 */
	/**
	 * 审核状态
	 */
	private int state; // 状态
	
	/**
	 * 审核人
	 */
	private Logininfo auditor;
	
	/**
	 * 审核备注
	 */
	private String remark; 
	
	/**
	 * 申请时间
	 */
	private Date applyTime;
	
	/**
	 * 审核时间
	 */
	private Date auditTime;  

	/**
	 * 关于页面上有多余选项的修改
	 */
	public String getSexDisplay() {
		return sex == SEX_MALE ? "男" : "女";
	}

	/**
	 * 返回当前的审核状态
	 */
	public String getStateDisplay() {
		switch (state) {
		case STATE_NORML:
			return "待审核";
		case STATE_AUDIT:
			return "审核通过";
		case STATE_REJECT:
			return "审核拒绝";
		default:
			return "";
		}
	}
	
	/**
	 * 返回当前的json字符串
	 * @return
	 */
	public String getJsonString(){
		Map<String, Object> json = new HashMap<>();
		json.put("id", id);
		json.put("applier", this.applier.getUsername());
		json.put("realName", realName);
		json.put("idNumber", idNumber);
		json.put("sex", getSexDisplay());
		json.put("bornDate", bornDate);
		json.put("address", address);
		json.put("image1", image1);
		json.put("image2", image2);
		return JSONObject.toJSONString(json);
	}
}
