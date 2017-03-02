package com.car.mp.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 车源实体
 * 
 * @author wangjh7
 * @date 2016-09-27
 *
 */
public class CheatCarSourceEntity implements Serializable {
	private int id;
	// 牌照日期
	private Date licenseDay;
	// 上牌地点
	private String licenseArea;
	// 所属地
	private String carArea;
	// 品牌id
	private int brandId;
	// 品牌
	private String brand;
	// 颜色
	private String color;
	// 车型
	private String carMode;
	// 公里（单位KM）
	private float mileage;
	// 指导价
	private float factoryPrice;
	// 指导价（含税）
	private float factoryTaxPrice;
	// 报价
	private float quotedPrice;
	// 买价
	private float buyPrice;
	// 卖价
	private float sellPrice;
	// 利润比例
	private float profitRate;
	// 来源
	private String srcName;
	// 来源地址
	private String url;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getLicenseDay() {
		return licenseDay;
	}

	public void setLicenseDay(Date licenseDay) {
		this.licenseDay = licenseDay;
	}

	public String getLicenseArea() {
		return licenseArea;
	}

	public void setLicenseArea(String licenseArea) {
		this.licenseArea = licenseArea;
	}

	public String getCarArea() {
		return carArea;
	}

	public void setCarArea(String carArea) {
		this.carArea = carArea;
	}

	public int getBrandId() {
		return brandId;
	}

	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getCarMode() {
		return carMode;
	}

	public void setCarMode(String carMode) {
		this.carMode = carMode;
	}

	public float getMileage() {
		return mileage;
	}

	public void setMileage(float mileage) {
		this.mileage = mileage;
	}

	public float getFactoryPrice() {
		return factoryPrice;
	}

	public void setFactoryPrice(float factoryPrice) {
		this.factoryPrice = factoryPrice;
	}

	public float getFactoryTaxPrice() {
		return factoryTaxPrice;
	}

	public void setFactoryTaxPrice(float factoryTaxPrice) {
		this.factoryTaxPrice = factoryTaxPrice;
	}

	public float getQuotedPrice() {
		return quotedPrice;
	}

	public void setQuotedPrice(float quotedPrice) {
		this.quotedPrice = quotedPrice;
	}

	public float getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(float buyPrice) {
		this.buyPrice = buyPrice;
	}

	public float getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(float sellPrice) {
		this.sellPrice = sellPrice;
	}

	public float getProfitRate() {
		return profitRate;
	}

	public void setProfitRate(float profitRate) {
		this.profitRate = profitRate;
	}

	public String getSrcName() {
		return srcName;
	}

	public void setSrcName(String srcName) {
		this.srcName = srcName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "CheatCarSourceEntity [id=" + id + ", licenseDay=" + licenseDay + ", licenseArea=" + licenseArea
				+ ", carArea=" + carArea + ", brandId=" + brandId + ", brand=" + brand + ", color=" + color
				+ ", carMode=" + carMode + ", mileage=" + mileage + ", factoryPrice=" + factoryPrice
				+ ", factoryTaxPrice=" + factoryTaxPrice + ", quotedPrice=" + quotedPrice + ", buyPrice=" + buyPrice
				+ ", sellPrice=" + sellPrice + ", profitRate=" + profitRate + ", srcName=" + srcName + ", url=" + url
				+ "]";
	}

}
