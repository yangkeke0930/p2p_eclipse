package com.xmg.p2p.base.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.xmg.p2p.base.domain.UserFile;
import com.xmg.p2p.base.service.ISystemDictionaryService;
import com.xmg.p2p.base.service.IUserFileService;
import com.xmg.p2p.base.util.JSONResult;
import com.xmg.p2p.base.util.UploadUtil;
import com.xmg.p2p.base.util.UserContext;

/**
 * 风控材料的controller
 * 
 * @author Yang Ke Ke
 *
 */
@Controller
public class UserFileController {

	/**
	 * 添加对应的依赖
	 */
	@Autowired
	private IUserFileService userFileService;
	@Autowired
	private ServletContext servletContext;
	@Autowired
	private ISystemDictionaryService systemDictionService;

	@RequestMapping("userFile")
	public String userFile(Model model, HttpServletRequest request) {
		/**
		 * 查出风控文件对象
		 */
		List<UserFile> userFiles = this.userFileService.listFilesByHasType(UserContext.getcurrent().getId(), false);
		// 当前用户如果还有尚未选择类型的风控材料 设置数据字典明细 。跳转到userFiles_commit页面中
		if (userFiles.size() > 0) {
			// 添加风控材料对象
			model.addAttribute("userFiles", userFiles);
			// 添加风控材料对象的类型
			model.addAttribute("fileTypes", this.systemDictionService.listByParentSn("userFileType"));
			// 跳转到指定的页面
			return "userFiles_commit";

		} else { // 当前用户全部的风控材料都选择类型
			model.addAttribute("sessionid", request.getSession().getId());
			// 显示对应的风控材料
			userFiles = this.userFileService.listFilesByHasType(UserContext.getcurrent().getId(), true);
			// 添加风控材料对象
			model.addAttribute("userFiles", userFiles);
			// 跳转到指定的页面
			return "userFiles";
		}
	}

	/**
	 * 上传用户风控文件
	 */
	@RequestMapping("userFileUpload")
	@ResponseBody
	public void userFileUpload(MultipartFile file) {
		String basePath = this.servletContext.getRealPath("/upload");
		String fileName = UploadUtil.upload(file, basePath);
		fileName = "/upload/" + fileName;
		this.userFileService.apply(fileName);
	}

	/**
	 * 处理用户风控文件类型的选择
	 */
	@RequestMapping("userFile_selectType")
	@ResponseBody
	public JSONResult userFileSelectType(long[] id, long[] fileType) {
		if (id != null && fileType != null && fileType.length == id.length) {
			this.userFileService.batchUpdateFileType(id, fileType);
		}
		return new JSONResult();
	}
}
