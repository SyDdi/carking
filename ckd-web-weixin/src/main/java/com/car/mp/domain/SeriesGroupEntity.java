package com.car.mp.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 第三方车系结果参数
 * 
 * @author wangjh7
 * @date 2016-08-14
 *
 */
public class SeriesGroupEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer status;
	private String messge;
	private List<SeriesEntity> data;

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

	public List<SeriesEntity> getData() {
		return data;
	}

	public void setData(List<SeriesEntity> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "SeriesGroupEntity [status=" + status + ", messge=" + messge + ", data=" + data + "]";
	}

}
