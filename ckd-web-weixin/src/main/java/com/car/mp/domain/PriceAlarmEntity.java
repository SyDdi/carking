package com.car.mp.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 新车价格预警
 * 
 * @author wangjh7
 * @date 2016-09-25
 */
public class PriceAlarmEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private String area;
	private String brand;
	private String factory;
	private String carLine;
	private int year;
	private String carModel;
	private float todayPrice;
	private float yesterdayPrice;
	private float dayDiffPrice;
	private float weekAgoPrice;
	private float weekDiffPrice;
	private float monthDiffPrice;
	private Date alermDay;
	private float monthAgoPrice;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getFactory() {
		return factory;
	}

	public void setFactory(String factory) {
		this.factory = factory;
	}

	public String getCarLine() {
		return carLine;
	}

	public void setCarLine(String carLine) {
		this.carLine = carLine;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public float getTodayPrice() {
		return todayPrice;
	}

	public void setTodayPrice(float todayPrice) {
		this.todayPrice = todayPrice;
	}

	public float getYesterdayPrice() {
		return yesterdayPrice;
	}

	public void setYesterdayPrice(float yesterdayPrice) {
		this.yesterdayPrice = yesterdayPrice;
	}

	public float getDayDiffPrice() {
		return dayDiffPrice;
	}

	public void setDayDiffPrice(float dayDiffPrice) {
		this.dayDiffPrice = dayDiffPrice;
	}

	public float getWeekAgoPrice() {
		return weekAgoPrice;
	}

	public void setWeekAgoPrice(float weekAgoPrice) {
		this.weekAgoPrice = weekAgoPrice;
	}

	public float getWeekDiffPrice() {
		return weekDiffPrice;
	}

	public void setWeekDiffPrice(float weekDiffPrice) {
		this.weekDiffPrice = weekDiffPrice;
	}

	public float getMonthDiffPrice() {
		return monthDiffPrice;
	}

	public void setMonthDiffPrice(float monthDiffPrice) {
		this.monthDiffPrice = monthDiffPrice;
	}

	public Date getAlermDay() {
		return alermDay;
	}

	public void setAlermDay(Date alermDay) {
		this.alermDay = alermDay;
	}

	public float getMonthAgoPrice() {
		return monthAgoPrice;
	}

	public void setMonthAgoPrice(float monthAgoPrice) {
		this.monthAgoPrice = monthAgoPrice;
	}

}
