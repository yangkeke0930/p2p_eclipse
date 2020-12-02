package com.xmg.p2p.base.mapper;

import com.xmg.p2p.base.domain.Logininfo;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface LogininfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Logininfo record);

    Logininfo selectByPrimaryKey(Long id);

    List<Logininfo> selectAll();

    int updateByPrimaryKey(Logininfo record);

    /**
     * 根据用户名查询用户数量
     * @param username
     * @return
     */
	int getCountByUsername(String username);
	
	/**
	 * 用户登录
	 * @param username
	 * @param encode
	 * @param userType 
	 * @return
	 */
	Logininfo login(@Param("username")String username, @Param("password")String encode, @Param("userType")int userType);
	
	/*
	 * 按照类型来查询用户？
	 * 
	 */
	int countByUserType(int userManager);
}