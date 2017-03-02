package com.car.mp.domain.dto;

import java.io.Serializable;

/**
 * 车源搜索实体
 * 
 * @author wangjh7
 * @date 2016-09-27
 *
 */
public class CheapCarSourceDTO implements Serializable {

	// 品牌
	private String brandName;
	// 车系
	private String seriesName;
	// 车型
	private String modelName;
	// 页码
	private int pageIndex;
	// 开始
	private int start;
	// 每页数
	private int pageSize;
	// 日期
	private String findDay;
	// 地区ID
	private int areaId;

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getSeriesName() {
		return seriesName;
	}

	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getStart() {
		if (0 < this.pageIndex && 0 < this.pageSize) {
			start = (pageIndex - 1) * pageSize;
		} else {
			start = 0;
		}
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public String getFindDay() {
		return findDay;
	}

	public void setFindDay(String findDay) {
		this.findDay = findDay;
	}

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	@Override
	public String toString() {
		return "CheapCarSourceDTO [brandName=" + brandName + ", seriesName=" + seriesName + ", modelName=" + modelName
				+ ", pageIndex=" + pageIndex + ", start=" + start + ", pageSize=" + pageSize + ", findDay=" + findDay
				+ ", areaId=" + areaId + "]";
	}

}
