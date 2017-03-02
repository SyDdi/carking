package com.car.portal.dto;

import java.io.Serializable;

/**
 * 查询实体
 * 
 * @author wangjh7
 * @date 2016-08-21
 *
 */
public class SearchEntity implements Serializable {

	private String colorId;
	private String colorName;
	private String timeId;
	private String timeName;
	private String cityId;
	private String cityName;
	private String modelId;
	private String modelName;
	private String mile;
	private Integer flag;// 1查询价格趋势图表需要的数据 不传不查询

	public String getColorId() {
		return colorId;
	}

	public void setColorId(String colorId) {
		this.colorId = colorId;
	}

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public String getTimeId() {
		return timeId;
	}

	public void setTimeId(String timeId) {
		this.timeId = timeId;
	}

	public String getTimeName() {
		return timeName;
	}

	public void setTimeName(String timeName) {
		this.timeName = timeName;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
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

	
	public String getMile() {
		return mile;
	}

	public void setMile(String mile) {
		this.mile = mile;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "SearchEntity [colorId=" + colorId + ", colorName=" + colorName + ", timeId=" + timeId + ", timeName="
				+ timeName + ", cityId=" + cityId + ", cityName=" + cityName + ", modelId=" + modelId + ", modelName="
				+ modelName + ", mile=" + mile + "]";
	}

}
