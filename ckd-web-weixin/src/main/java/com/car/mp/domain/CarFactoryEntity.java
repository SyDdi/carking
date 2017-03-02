package com.car.mp.domain;

import java.io.Serializable;

/**
 * 厂商实体
 * 
 * @author wangjh7
 * @date 2016-08-14
 *
 */
public class CarFactoryEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String factoryNameCn;
	private String factoryNameEn;
	private Integer brandId;
	private Integer factoryId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFactoryNameCn() {
		return factoryNameCn;
	}

	public void setFactoryNameCn(String factoryNameCn) {
		this.factoryNameCn = factoryNameCn;
	}

	public String getFactoryNameEn() {
		return factoryNameEn;
	}

	public void setFactoryNameEn(String factoryNameEn) {
		this.factoryNameEn = factoryNameEn;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public Integer getFactoryId() {
		return factoryId;
	}

	public void setFactoryId(Integer factoryId) {
		this.factoryId = factoryId;
	}

	@Override
	public String toString() {
		return "CarFactory [id=" + id + ", factoryNameCn=" + factoryNameCn + ", factoryNameEn=" + factoryNameEn
				+ ", brandId=" + brandId + ", factoryId=" + factoryId + "]";
	}

}
