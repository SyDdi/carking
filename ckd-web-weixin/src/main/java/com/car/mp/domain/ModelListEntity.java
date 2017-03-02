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
public class ModelListEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer status;
	private String messge;
	private List<ModelEntity> data;

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

	public List<ModelEntity> getData() {
		return data;
	}

	public void setData(List<ModelEntity> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ModelListEntity [status=" + status + ", messge=" + messge + ", data=" + data + "]";
	}

}
