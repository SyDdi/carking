package com.car.mp.domain;

import java.io.Serializable;

/**
 * 第三方车型结果参数
 * 
 * @author wangjh7
 * @date 2016-08-14
 *
 */
public class ModelEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String makeYear;
	private String modelId;
	private String modelName;
	private SeriesEntity series;

	public String getMakeYear() {
		return makeYear;
	}

	public void setMakeYear(String makeYear) {
		this.makeYear = makeYear;
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

	public SeriesEntity getSeries() {
		return series;
	}

	public void setSeries(SeriesEntity series) {
		this.series = series;
	}

	@Override
	public String toString() {
		return "ModelEntity [makeYear=" + makeYear + ", modelId=" + modelId + ", modelName="
				+ modelName + ", series=" + series + "]";
	}

}
