package com.car.mp.domain.dto;

import java.io.Serializable;

/**
 * 预警实体
 * 
 * @author wangjh7
 * @date 2016-09-28
 *
 */
public class PriceAlarmDTO implements Serializable {

	// 页码
	private int pageIndex;
	// 开始
	private int start;
	// 每页数
	private int pageSize;
	// 地区ID
	private int areaId;
    // 日期
    private String date;
    //品牌
    private String brand;
    //厂商
    private String factory;
    //车系
    private String family;


    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getStart() {
		if (0 < this.pageIndex && 0 < this.pageSize) {
			start = (pageIndex - 1) * pageSize;
		} else {
			start = 0;
		}
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	@Override
	public String toString() {
		return "PriceAlarmDTO [date=" + date + ", pageIndex=" + pageIndex + ", start=" + start + ", pageSize="
				+ pageSize + ", areaId=" + areaId  + ", brand=" + brand + ", factory=" + factory + ", family=" + family + "]";
	}

}
