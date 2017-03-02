package com.car.mp.domain;

import java.io.Serializable;

/**
 * 城市实体
 * 
 * @author wangjh7
 * @date 2016-10-01
 *
 */
public class CarAreaEntity implements Serializable {
	private int id;
	// id
	private int carAreaId;
	// 所属地
	private String carAreaName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCarAreaId() {
		return carAreaId;
	}

	public void setCarAreaId(int carAreaId) {
		this.carAreaId = carAreaId;
	}

	public String getCarAreaName() {
		return carAreaName;
	}

	public void setCarAreaName(String carAreaName) {
		this.carAreaName = carAreaName;
	}

	@Override
	public String toString() {
		return "CarAreaEntity [id=" + id + ", carAreaId=" + carAreaId + ", carAreaName=" + carAreaName + "]";
	}

}
