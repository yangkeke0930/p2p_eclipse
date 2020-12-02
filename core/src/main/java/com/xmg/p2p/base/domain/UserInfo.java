package com.xmg.p2p.base.domain;

import com.xmg.p2p.base.util.BitStatesUtils;

import lombok.Getter;
import lombok.Setter;

/**
 * 用户相关信息
 * @author 78158
 *
 */
@Setter
@Getter

public class UserInfo extends BaseDomain {
	
	/**
	 * 版本号  乐观锁
	 */
	private int version;
	
	/**
	 * 用户状态码
	 */
	private long bitState = 0;
	
	/**
	 * 真实姓名
	 */
	private String realName;
	
	/**
	 * 用户身份证
	 */
	private String idNumber;
	
	/**
	 * 用户电话
	 */
	private String phoneNumber;
	
	/**
	 * 用户邮箱
	 */
	private String email;
	
	/**
	 * 用户实名认证后的id
	 */
	private Long realAuthId;    
	
	/**
	 * 用户风控累计分数
	 */
	private int score;		 
	
	/**
	 * 用户收入情况
	 */
	private SystemDictionaryItem incomeGrade;
	
	/**
	 * 用户婚姻情况
	 */
	private SystemDictionaryItem marriage;
	
	/**
	 * 用户子女情况
	 */
	private SystemDictionaryItem kidCount;
	
	/**
	 * 用户学历情况
	 */
	private SystemDictionaryItem educationBackground;	 
	
	/**
	 * 用户的住房条件
	 */
	private SystemDictionaryItem houseCondition;	//住房条件
	
	/**
	 * 添加绑定手机的方法
	 */
	public void addState(long state){
		this.setBitState(BitStatesUtils.addState(this.bitState, state));
	}
	
	/**
	 * 返回用户是否已经绑定手机
	 */
	public boolean getIsBindPhone(){
		return BitStatesUtils.hasState(this.bitState, BitStatesUtils.OP_BIND_PHONE);
	}
	
	/**
	 * 返回用户是否已经绑定邮箱
	 */
	public boolean getIsBindEmail(){
		return BitStatesUtils.hasState(this.bitState, BitStatesUtils.OP_BIND_EMAIL);
	}
	
	/**
	 * 返回用户是否已经填写基本资料
	 * @return
	 */
	public boolean getIsBasicInfo(){
		return BitStatesUtils.hasState(this.bitState, BitStatesUtils.OP_BASIC_INFO);
	}
	
	/**
	 * 返回用户是否已经实名认证
	 * @return
	 */
	public boolean getIsRealAuth(){
		return BitStatesUtils.hasState(this.bitState, BitStatesUtils.OP_REAL_AUTH);
	}
	
	/**
	 * 返回用户是否视频认证
	 * @return
	 */
	public boolean getIsVideoAuth(){
		return BitStatesUtils.hasState(this.bitState, BitStatesUtils.OP_VIDEO_AUTH);
	}
	
	/**
	 * 返回用户是否有一个借款正在进行中
	 * @return
	 */
	public boolean getHasBidRequestProcess(){
		return BitStatesUtils.hasState(this.bitState, BitStatesUtils.OP_HAS_BIDREQUEST_PROCESS);
	}
}
