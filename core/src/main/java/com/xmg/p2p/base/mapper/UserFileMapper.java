package com.xmg.p2p.base.mapper;

import com.xmg.p2p.base.domain.UserFile;
import com.xmg.p2p.base.query.UserFileQueryObject;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * 风控材料
 * 
 * @author Yang Ke Ke
 *
 */
public interface UserFileMapper {

	int insert(UserFile record); // 添加风控材料

	UserFile selectByPrimaryKey(Long id); // 根据id来查询风控材料

	List<UserFile> selectAll(); // 查看风控材料

	int updateByPrimaryKey(UserFile record); // 根据ID来更新风控材料

	/**
	 * 列出一个用户的风控材料对象
	 * 
	 * @param logininfoId
	 *            当前用户的id
	 * @param hasType
	 *            true:风控资料有类型 false：风控资料没有类型
	 * @return
	 */
	List<UserFile> listFilesByHasType(@Param("logininfoId")Long logininfoId, @Param("hasType")boolean hasType);
	
	/**
	 * 查询用户风控资料的分页查询
	 */
	int queryForCount(UserFileQueryObject qo);
	List<UserFile>query(UserFileQueryObject qo);
}