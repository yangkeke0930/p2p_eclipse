package com.xmg.p2p.base.mapper;

import com.xmg.p2p.base.domain.VedioAuth;
import com.xmg.p2p.base.query.VedioAuthQueryObject;

import java.util.List;

public interface VedioAuthMapper {
	
	/**
	 * 由于我们这个是后台提取视频认证的视频来进行认证的
	 * 前端没有可以进行视频的页面及业务逻辑
	 * 导致：不需要删除
	 *     不需更新
	 *     不需要查询所有
	 *     需要查询单个和插入
	 * @param id
	 * @return
	 */

    int insert(VedioAuth record);

    VedioAuth selectByPrimaryKey(Long id);

    /**
     * 分页查询相关
     */
    int queryForCount(VedioAuthQueryObject qo);
    List<VedioAuth> query(VedioAuthQueryObject qo);

}