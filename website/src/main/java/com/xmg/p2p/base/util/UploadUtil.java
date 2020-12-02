package com.xmg.p2p.base.util;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;



 
public class UploadUtil {
	
	/**
	 * 处理文件上传的
	 * @param file
	 * @param basePath 存放文件目录的绝对路径
	 * servletContext.getRealPath("/upload");
	 * @return
	 */
	public static String upload(MultipartFile file, String basePath) {
		String orgFileName = file.getOriginalFilename();
		String fileName = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(orgFileName);
		try {
			File targetFile = new File(basePath, fileName);
			FileUtils.writeByteArrayToFile(targetFile, file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileName;
	}
}
