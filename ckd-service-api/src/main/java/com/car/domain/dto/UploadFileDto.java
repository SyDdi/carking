package com.car.domain.dto;

import java.io.Serializable;
import java.util.Arrays;

public class UploadFileDto implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 文件扩展名 */
	private String fileExtName;
	/** 文件字节形式 */
	private byte[] fileBytes;

    /** 是否需要对图片进行车牌马赛克处理 默认否**/
    private boolean mosaic = false;

	/**
	 * 带参构造函数
	 * 
	 * @param fileExtName
	 *            文件扩展名（如：jpg、xls、text）
	 * @param fileBytes
	 *            文件字节
	 */
	public UploadFileDto(String fileExtName, byte[] fileBytes) {
		super();
		this.fileExtName = fileExtName;
		this.fileBytes = fileBytes;
	}

	/**
	 * 默认构造函数
	 */
	public UploadFileDto() {
		super();
	}



	public byte[] getFileBytes() {
		return fileBytes;
	}

	public void setFileBytes(byte[] fileBytes) {
		this.fileBytes = fileBytes;
	}

	public String getFileExtName() {
		return fileExtName;
	}

	public void setFileExtName(String fileExtName) {
		this.fileExtName = fileExtName;
	}

    public boolean isMosaic() {
        return mosaic;
    }

    public void setMosaic(boolean mosaic) {
        this.mosaic = mosaic;
    }

    @Override
    public String toString() {
        return "UploadFileDto{" +
                "fileExtName='" + fileExtName + '\'' +
                ", fileBytes=" + Arrays.toString(fileBytes) +
                ", mosaic=" + mosaic +
                '}';
    }
}
