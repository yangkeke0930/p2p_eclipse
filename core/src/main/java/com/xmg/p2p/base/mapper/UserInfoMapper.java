package com.xmg.p2p.base.mapper;

import com.xmg.p2p.base.domain.UserInfo;
import java.util.List;
import java.util.Map;

public interface UserInfoMapper {
	
    int deleteByPrimaryKey(Long id);

    int insert(UserInfo record);

    UserInfo selectByPrimaryKey(Long id);

    List<UserInfo> selectAll();

    int updateByPrimaryKey(UserInfo record);

    /**
     * 用于用户得autocomplate
     * @param keyword
     * @return
     */

	List<Map<String, Object>> autocomplate(String keyword);
}