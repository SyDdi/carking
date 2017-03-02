package com.car.service;

import com.car.domain.dto.ResultDto;
import com.car.domain.dto.UploadFileDto;
import com.car.exception.CommonException;

/**
 * 
 * @ClassName: CommonUploadFileService
 * @Description: 公共文件上传业务逻辑层接口
 *
 */
public interface CommonUploadFileService {
	/**
	 * 上传文件
	 * 
	 * @param uploadFileDto
	 *            上传文件dto
	 * @return
	 */
	public ResultDto uploadFile(UploadFileDto uploadFileDto)throws CommonException;

	/**
	 * 删除远程文件
	 * 
	 * @param filePath
	 *            文件远程地址
	 * @return
	 */
	public void deleteFile(String filePath) throws CommonException;

}
