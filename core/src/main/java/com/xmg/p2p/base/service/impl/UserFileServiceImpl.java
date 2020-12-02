package com.xmg.p2p.base.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmg.p2p.base.domain.RealAuth;
import com.xmg.p2p.base.domain.SystemDictionaryItem;
import com.xmg.p2p.base.domain.UserFile;
import com.xmg.p2p.base.domain.UserInfo;
import com.xmg.p2p.base.mapper.UserFileMapper;
import com.xmg.p2p.base.query.PageResult;
import com.xmg.p2p.base.query.UserFileQueryObject;
import com.xmg.p2p.base.service.IUserFileService;
import com.xmg.p2p.base.service.IUserinfoService;
import com.xmg.p2p.base.util.UserContext;
/**
 * 风控资料的服务接口的实现类
 * @author Yang Ke Ke
 *
 */
@Service
public class UserFileServiceImpl implements IUserFileService {

	/**
	 * 添加对应的依赖
	 */
	@Autowired
	private UserFileMapper userFileMapper;
	@Autowired
	private IUserinfoService userinfoService;

	/**
	 * 上传用户风控文件
	 */
	@Override
	public void apply(String fileName) {
		/**
		 * 创建一个风控文件对象，应该包含如下的属性
		 * 		申请人
		 * 		申请时间
		 * 		申请的内容
		 * 		申请后的状态	应该为未审核的状态
		 * 	然后执行插入对象的操作
		 */
		 UserFile uf = new UserFile();
		 uf.setApplier(UserContext.getcurrent());
		 uf.setApplyTime(new Date());
		 uf.setImage(fileName);
		 uf.setState(UserFile.STATE_NORML);		//审核状态为未审核
		 this.userFileMapper.insert(uf);
		 
	}


	/**
	 * 列出一个风控材料对象
	 */
	@Override
	public List<UserFile> listFilesByHasType(Long logininfoId, boolean hasType) {
		return  this.userFileMapper.listFilesByHasType(logininfoId, hasType);
	}

	
	/**
	 * 批量修改风控文件的类型
	 */
	@Override
	public void batchUpdateFileType(long[] ids, long[] fileTypes) {
		for(int i = 0; i < ids.length; i++){
			//查询出风控文件对象
			UserFile uf = this.userFileMapper.selectByPrimaryKey(ids[i]);
			//为风控文件对象设置类型
			SystemDictionaryItem item = new SystemDictionaryItem();
			item.setId(fileTypes[i]);
			uf.setFileType(item);
			this.userFileMapper.updateByPrimaryKey(uf);
		}
	}


	/**
	 * 风控材料审核后台的分页页面
	 */
	@Override
	public PageResult query(UserFileQueryObject qo) {
			
		int count = this.userFileMapper.queryForCount(qo);
		if(count > 0){
			List<UserFile> list = this.userFileMapper.query(qo);
			return new PageResult(list, count, qo.getCurrentPage(), qo.getPageSize());
		}
		return PageResult.empty(qo.getPageSize());
		
	}

	/**
	 * 用户风控材料的审核
	 */

	@Override
	public void audit(Long id, int state, String remark, int score) {
		/**
		 * 获取申请人的用户资料
		 * 判断当前审核资料的状态
		 * 未审核
		 * 		添加属性
		 * 已经审核
		 * 		暂时先不管
		 */
		UserFile uf = this.userFileMapper.selectByPrimaryKey(id);
		if(uf != null && uf.getState() == UserFile.STATE_NORML){
			//添加属性
			uf.setAuditTime(new Date());
			uf.setAuditor(UserContext.getcurrent());
			uf.setState(state);
			if(state == UserFile.STATE_AUDIT){
				//添加认证分数
				uf.setScore(score);
				//获取申请人
				UserInfo userinfo = this.userinfoService.get(uf.getApplier().getId());
				userinfo.setScore(score + userinfo.getScore());
				this.userinfoService.update(userinfo);
			}
		}
		this.userFileMapper.updateByPrimaryKey(uf);
	}
	
	
}
