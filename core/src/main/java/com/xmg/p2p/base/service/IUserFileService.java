package com.xmg.p2p.base.service;

import java.util.List;

import com.xmg.p2p.base.domain.UserFile;
import com.xmg.p2p.base.query.PageResult;
import com.xmg.p2p.base.query.QueryObject;
import com.xmg.p2p.base.query.UserFileQueryObject;

/**
 * 风控资料服务
 * @author Yang Ke Ke
 *
 */
public interface IUserFileService {

	/**
	 * 上传一个风控文件对象
	 * @param fileName
	 */
	void apply(String fileName);

	/**
	 * 列出一个风控材料对象
	 * hasType:如果为true有选择类型，false没有选择类型
	 * @param id
	 * @return
	 */
	List<UserFile> listFilesByHasType(Long logininfoId , boolean hasType);
	
	/**
	 * 批量修改风控文件的类型
	 * @param id
	 * @param fileType
	 */
	void batchUpdateFileType(long[] ids, long[] fileTypes);

 
	/**
	 * 风控材料认证对象的分页查询
	 */
	PageResult query(UserFileQueryObject qo);

	void audit(Long id, int state, String remark ,int score);
 


}
