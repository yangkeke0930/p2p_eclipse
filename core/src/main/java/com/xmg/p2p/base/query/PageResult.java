package com.xmg.p2p.base.query;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

//分页查询结果对象---> 当前页面所要显示的页面对象
@SuppressWarnings("all")
@Getter
public class PageResult {
	/**
	 * 当前页面结果集数据
	 */
	private List listData; 
	
	/**
	 * 总记录数
	 */
	private Integer totalCount; 

	/**
	 * 起始页面
	 */
	private Integer currentPage = 1;
	/**
	 * 每页记录的数据数字
	 */
	private Integer pageSize = 10;

	/**
	 * 上一页
	 */
	private Integer prevPage; 
	
	/**
	 * 下一页
	 */
	private Integer nextPage; 
	
	/**
	 * 总页数
	 */
	private Integer totalPage; 

	
	 /**
	  * 如果返回的数据为0，则返回一个空集
	  * @param pageSize
	  * @return
	  */
	public static PageResult empty(Integer pageSize) {
		return new PageResult(new ArrayList<>(), 0, 1, pageSize);
	}

	/**
	 * 处理分页中当前页面不能小于总页面的问题
	 * @param pageSize
	 * @return
	 */
	public int getTotalPage() {
		return totalPage == 0 ? 1 : totalPage;
	}

	public PageResult(List listData, Integer totalCount, Integer currentPage,
			Integer pageSize) {
		this.listData = listData;
		this.totalCount = totalCount;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		// ----------------------------------------
		this.totalPage = this.totalCount % this.pageSize == 0 ? this.totalCount
				/ this.pageSize : this.totalCount / this.pageSize + 1;

		this.prevPage = this.currentPage - 1 >= 1 ? this.currentPage - 1 : 1;
		this.nextPage = this.currentPage + 1 <= this.totalPage ? this.currentPage + 1
				: this.totalPage;
	}
}
