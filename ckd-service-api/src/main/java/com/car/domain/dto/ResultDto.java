package com.car.domain.dto;

import java.io.Serializable;
import java.util.Arrays;


public class ResultDto implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 文件访问路径 */
	private String filePath;
	/** 文件所在组名 */
	private String groupName;
	/** 文件远程文件名 */
	private String remoteFilename;

    /** 系统自动对车牌打马赛克的个数 **/
    private Integer mosaicCount = 0;

	/**
	 * 有参构造函数
	 * 
	 * @param filePath
	 *            文件路径
	 * @param groupName
	 *            组名
	 * @param remoteFilename
	 *            远程文件名称
	 * 
	 */
	public ResultDto(String filePath, String groupName, String remoteFilename) {
		super();
		this.filePath = filePath;
		this.groupName = groupName;
		this.remoteFilename = remoteFilename;

	}

	/**
	 * 
	 * 无参构造函数
	 * 
	 */
	public ResultDto() {
		super();
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getRemoteFilename() {
		return remoteFilename;
	}

	public void setRemoteFilename(String remoteFilename) {
		this.remoteFilename = remoteFilename;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

    public Integer getMosaicCount() {
        return mosaicCount;
    }

    public void setMosaicCount(Integer mosaicCount) {
        this.mosaicCount = mosaicCount;
    }
    @Override
    public String toString() {
        return "ResultDto{" +
                "filePath='" + filePath + '\'' +
                ", groupName='" + groupName + '\'' +
                ", remoteFilename='" + remoteFilename + '\'' +
                ", mosaicCount=" + mosaicCount +
                '}';
    }
}
