package com.car.mp.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 第三方车系参数
 * 
 * @author wangjh7
 * @date 2016-08-14
 *
 */
public class SeriesEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String seriesIds;
	private String seriesName;
	private List<SubSeriesEntity> series;

	public String getSeriesIds() {
		return seriesIds;
	}

	public void setSeriesIds(String seriesIds) {
		this.seriesIds = seriesIds;
	}

	public String getSeriesName() {
		return seriesName;
	}

	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}

	public List<SubSeriesEntity> getSeries() {
		return series;
	}

	public void setSeries(List<SubSeriesEntity> series) {
		this.series = series;
	}

	@Override
	public String toString() {
		return "SeriesEntity [seriesIds=" + seriesIds + ", seriesName=" + seriesName + ", series=" + series + "]";
	}

}
