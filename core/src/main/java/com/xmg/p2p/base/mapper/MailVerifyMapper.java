package com.xmg.p2p.base.mapper;

import com.xmg.p2p.base.domain.MailVerify;
import java.util.List;

public interface MailVerifyMapper {

	int deleteByPrimaryKey(Long id);

	int insert(MailVerify record);

	/**
	 * 根据uuid来查询对应的邮箱验证对象
	 * @param uuid
	 * @return
	 */
	MailVerify selectByUUID(String uuid);

	List<MailVerify> selectAll();

	int updateByPrimaryKey(MailVerify record);
}