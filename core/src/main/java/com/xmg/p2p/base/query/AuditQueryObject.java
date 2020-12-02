package com.xmg.p2p.base.query;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.xmg.p2p.base.util.DateUtil;

import lombok.Getter;
import lombok.Setter;

/**
 * 基本的审核查询对象
 * @author Yang Ke Ke
 *
 */
@Setter
@Getter
public class AuditQueryObject extends QueryObject {
	
	/**
	 * 审核状态
	 */
	private int state = -1;
	
	/**
	 * 审核开始时间
	 */
	private Date beginDate;

	/**
	 * 审核结束时间
	 */
	private Date endDate;
	
	/**
	 * 格式化开始时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setBeginDate(Date beginDate){
		this.beginDate = beginDate;
	}
	
	/**
	 * 格式化结束时间
	 * @param endDate
	 */
	@DateTimeFormat(pattern = "yyy-MM-dd")
	public void setEndDate(Date endDate){
		this.endDate = endDate;
	}
	
	/**
	 * 将结束的时间设置为今天的最后一天
	 */
	public Date getEndDate(){
		return endDate == null ? null : DateUtil.endOfDay(endDate);
	}
	
}
