package com.xmg.p2p.base.query;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;

import com.xmg.p2p.base.util.DateUtil;

import lombok.Getter;
import lombok.Setter;

/**
 * 查询登录日志对象
 * 
 * @author 78158
 *
 */
@Setter
@Getter
public class IplogQueryObject extends QueryObject {
	/**
	 * 这里面就是你当前页面要显示的对象的bean对象
	 */
	
	/**
	 * 开始时间
	 */
	private Date beginDate ;
	
	/**
	 * 结束时间
	 */
	private Date endDate;
	
	/**
	 * 未登录的状态
	 */
	private int state = -1;
 
	/**
	 * 登录时候用的用户名
	 */
	private String username;
	
	/**
	 * 前后台用户的判断
	 */
	private int userType = -1;
	
	
	/**
	 * 开始时间格式化
	 * @param beginDate
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	 public void setBeginDate(Date beginDate){
		 this.beginDate = beginDate;
	 }
	
	/**
	 * 结束时间格式化
	 * @param endDate
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	 public void setEndDate(Date endDate){
		 this.endDate = endDate;
	 }
 
	 /**
	  * 设置时间为当天的最后一秒
	  * @return
	  */
	 public Date getEndDate(){
		return endDate == null ? null : DateUtil.endOfDay(endDate); 
	 }
	
	 /**
	  * 对登录的用户名进行非空判断
	  * @return
	  */
	 public String getUsername(){
		 return StringUtils.hasLength(username) ? username : null;
	 }
}
