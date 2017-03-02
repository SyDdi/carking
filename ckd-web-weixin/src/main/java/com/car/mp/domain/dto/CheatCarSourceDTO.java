package com.car.mp.domain.dto;

import java.io.Serializable;

/**
 * 车源搜索实体
 * 
 * @author wangjh7
 * @date 2016-09-27
 *
 */
public class CheatCarSourceDTO implements Serializable {

	// 品牌
	private String brandName;
	// 车系
	private String seriesName;
	// 车型
	private String modelName;
	// 页码
	private int pageIndex;
	// 每页数
	private int pageSize;

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

	@Override
	public String toString() {
		return "CheatCarSourceDTO [brandName=" + brandName + ", seriesName=" + seriesName + ", modelName=" + modelName
				+ ", pageIndex=" + pageIndex + ", pageSize=" + pageSize + "]";
	}

}
