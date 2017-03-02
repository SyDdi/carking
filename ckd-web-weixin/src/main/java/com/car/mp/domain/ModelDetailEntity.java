package com.car.mp.domain;

import java.io.Serializable;

/**
 * 第三方车型结果参数
 * 
 * @author wangjh7
 * @date 2016-08-14
 *
 */
public class ModelDetailEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer status;
	private String messge;
	private String brandId;
	private String brand;
	private String makeId;
	private String make;
	private String seriesId;
	private String seriesGroupName;
	private String seriesName;
	private String modelId;
	private String modelName;
	private String makeYear;
	private String newPriceW;

	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMessge() {
		return messge;
	}
	public void setMessge(String messge) {
		this.messge = messge;
	}
	public String getBrandId() {
		return brandId;
	}
	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getMakeId() {
		return makeId;
	}
	public void setMakeId(String makeId) {
		this.makeId = makeId;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getSeriesId() {
		return seriesId;
	}
	public void setSeriesId(String seriesId) {
		this.seriesId = seriesId;
	}



	public String getSeriesGroupName() {
		return seriesGroupName;
	}



	public void setSeriesGroupName(String seriesGroupName) {
		this.seriesGroupName = seriesGroupName;
	}



	public String getSeriesName() {
		return seriesName;
	}



	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}



	public String getModelId() {
		return modelId;
	}



	public void setModelId(String modelId) {
		this.modelId = modelId;
	}



	public String getModelName() {
		return modelName;
	}



	public void setModelName(String modelName) {
		this.modelName = modelName;
	}



	public String getMakeYear() {
		return makeYear;
	}



	public void setMakeYear(String makeYear) {
		this.makeYear = makeYear;
	}



	public String getNewPriceW() {
		return newPriceW;
	}



	public void setNewPriceW(String newPriceW) {
		this.newPriceW = newPriceW;
	}
	@Override
	public String toString() {
		return "ModelDetailEntity [brandId=" + brandId + ", brand=" + brand + ", makeId=" + makeId + ", make=" + make
				+ ", seriesId=" + seriesId + ", seriesGroupName=" + seriesGroupName + ", seriesName=" + seriesName
				+ ", modelId=" + modelId + ", modelName=" + modelName + ", makeYear=" + makeYear + ", newPriceW="
				+ newPriceW + "]";
	}

}
