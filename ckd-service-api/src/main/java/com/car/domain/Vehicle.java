package com.car.domain;


import com.car.security.HashIdsHelper;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_vehicle")
public class Vehicle extends IdEntity {
    /**
     * 品牌
     */
    @Column(name = "brand_id")
    private Long brandId;

    /**
     * 车系
     */
    @Column(name = "family_id")
    private Long familyId;

    /**
     * 车型
     */
    @Column(name = "model_id")
    private Long modelId;

    /**
     * 用户
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 省份
     */
    @Column(name = "province_id")
    private Long provinceId;

    /**
     * 地区
     */
    @Column(name = "zone_id")
    private Long zoneId;

    /**
     * 车牌所在地省份
     */
    @Column(name = "reg_province_id")
    private Long regProvinceId;

    /**
     * 拍照所在地
     */
    @Column(name = "reg_zone_id")
    private Long regZoneId;


    /**
     * 年款
     */
    @Column(name = "car_year")
    private String carYear;

    /**
     * 预期售价
     */
    @Column(name = "expect_price")
    private Long expectPrice;

    /**
     * 上牌日期
     */
    @Column(name = "reg_date")
    private String regDate;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 发布时间
     */
    @Column(name = "public_date")
    private Date publicDate;

    /**
     * 状态0不显示1显示
     */
    private Short status;

    /**
     * 车身颜色
     */
    @Column(name = "outer_color")
    private String outerColor;

    /**
     * 里程
     */
    private String miles;

    /**
     * 车牌号
     */
    @Column(name = "number_plate")
    private String numberPlate;

    /**
     * 车源评估
     */
    @Column(name = "report_id")
    private Long reportId;

    /**
     * 信息说明
     */
    private String info;


    /**
     * 修改时间
     */
    @Column(name = "update_date")
    private Date updateDate;

    /**
     * 修改人
     */
    @Column(name = "update_user")
    private Long updateUser;

    /**
     * 是否删除车源 0未删除  1已删除
     */
    @Column(name = "is_delete")
    private Integer isDelete = 0;
    /**
     * 厂商id
     */
    @Column(name = "factory_id")
    private Integer factoryId;
    /**
     * 手机号
     */
    @Column(name = "telephone")
    private String telephone;
    /**
     * 报价次数
     */
    @Column(name = "quote_count")
    private Integer quoteCount;
    /**
     * 最后一个报价时间
     */
    @Column(name = "last_quote_date")
    private Date lastQuoteDate;

    @Transient
    private String orderByColumn;

    @Transient
    private Integer quoteCountBig;//报价次数 大
    @Transient
    private Integer quoteCountSmall;//报价次数 小
    @Transient
    private String lastQuoteDateStart;//报价开始时间 （查询）
    @Transient
    private String lastQuoteDateEnd;//报价结束时间（查询）

    @Transient
    private Zone zone;
    @Transient
    private User user;
    @Transient
    private List<Picture> pictures=new ArrayList<Picture>();
    @Transient
    private Brand brand;//品牌
    @Transient
    private Family family;//车系
    @Transient
    private Model model;//车型
    @Transient
    private String startDate;//车源生成开始时间 （查询）
    @Transient
    private String endDate;//车源生成结束时间（查询）
    @Transient
    private String series;
    @Transient
    private String strModel;
    @Transient
    private Double carPrice;
    @Transient
    private String updateUserName;

    public Zone getZone() {
		return zone;
	}

	public void setZone(Zone zone) {
		this.zone = zone;
	}

	public List<Picture> getPictures() {
		return pictures;
	}
	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	/**
     * 获取品牌
     *
     * @return brand_id - 品牌
     */
    public Long getBrandId() {
        return brandId;
    }

    /**
     * 设置品牌
     *
     * @param brandId 品牌
     */
    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    /**
     * 获取车系
     *
     * @return family_id - 车系
     */
    public Long getFamilyId() {
        return familyId;
    }

    /**
     * 设置车系
     *
     * @param familyId 车系
     */
    public void setFamilyId(Long familyId) {
        this.familyId = familyId;
    }

    /**
     * 获取车型
     *
     * @return model_id - 车型
     */
    public Long getModelId() {
        return modelId;
    }

    /**
     * 设置车型
     *
     * @param modelId 车型
     */
    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    /**
     * 获取用户
     *
     * @return user_id - 用户
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户
     *
     * @param userId 用户
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取省份
     *
     * @return province_id - 省份
     */
    public Long getProvinceId() {
        return provinceId;
    }

    /**
     * 设置省份
     *
     * @param provinceId 省份
     */
    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    /**
     * 获取地区
     *
     * @return zone_id - 地区
     */
    public Long getZoneId() {
        return zoneId;
    }

    /**
     * 设置地区
     *
     * @param zoneId 地区
     */
    public void setZoneId(Long zoneId) {
        this.zoneId = zoneId;
    }

    /**
     * 获取拍照所在地
     *
     * @return reg_zone_id - 拍照所在地
     */
    public Long getRegZoneId() {
        return regZoneId;
    }

    /**
     * 设置拍照所在地
     *
     * @param regZoneId 拍照所在地
     */
    public void setRegZoneId(Long regZoneId) {
        this.regZoneId = regZoneId;
    }

    /**
     * 获取年款
     *
     * @return car_year - 年款
     */
    public String getCarYear() {
        return carYear;
    }

    /**
     * 设置年款
     *
     * @param carYear 年款
     */
    public void setCarYear(String carYear) {
        this.carYear = carYear == null ? null : carYear.trim();
    }

    /**
     * 获取预期售价
     *
     * @return expect_price - 预期售价
     */
    public Long getExpectPrice() {
        return expectPrice;
    }

    /**
     * 设置预期售价
     *
     * @param expectPrice 预期售价
     */
    public void setExpectPrice(Long expectPrice) {
        this.expectPrice = expectPrice;
    }

    /**
     * 获取上牌日期
     *
     * @return reg_date - 上牌日期
     */
    public String getRegDate() {
        return regDate;
    }

    /**
     * 设置上牌日期
     *
     * @param regDate 上牌日期
     */
    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    /**
     * 获取创建时间
     *
     * @return create_date - 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置创建时间
     *
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取发布时间
     *
     * @return public_date - 发布时间
     */
    public Date getPublicDate() {
        return publicDate;
    }

    /**
     * 设置发布时间
     *
     * @param publicDate 发布时间
     */
    public void setPublicDate(Date publicDate) {
        this.publicDate = publicDate;
    }

    /**
     * 获取状态0不显示1显示
     *
     * @return status - 状态0不显示1显示
     */
    public Short getStatus() {
        return status;
    }

    /**
     * 设置状态0不显示1显示
     *
     * @param status 状态0不显示1显示
     */
    public void setStatus(Short status) {
        this.status = status;
    }

    /**
     * 获取车身颜色
     *
     * @return outer_color - 车身颜色
     */
    public String getOuterColor() {
        return outerColor;
    }

    /**
     * 设置车身颜色
     *
     * @param outerColor 车身颜色
     */
    public void setOuterColor(String outerColor) {
        this.outerColor = outerColor == null ? null : outerColor.trim();
    }

    /**
     * 获取里程
     *
     * @return miles - 里程
     */
    public String getMiles() {
        return miles;
    }

    /**
     * 设置里程
     *
     * @param miles 里程
     */
    public void setMiles(String miles) {
        this.miles = miles == null ? null : miles.trim();
    }

    /**
     * 获取车牌号
     *
     * @return number_plate - 车牌号
     */
    public String getNumberPlate() {
        return numberPlate;
    }

    /**
     * 设置车牌号
     *
     * @param numberPlate 车牌号
     */
    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate == null ? null : numberPlate.trim();
    }

    /**
     * 获取车源评估
     *
     * @return report_id - 车源评估
     */
    public Long getReportId() {
        return reportId;
    }

    /**
     * 设置车源评估
     *
     * @param reportId 车源评估
     */
    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    /**
     * 获取信息说明
     *
     * @return info - 信息说明
     */
    public String getInfo() {
        return info;
    }

    /**
     * 设置信息说明
     *
     * @param info 信息说明
     */
    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }
    
    /**
     * 获取手机号
     *
     * @return info - 信息说明
     */
    public String getTelephone() {
		return telephone;
	}

	/**
     * 设置手机号
     *
     * @param info 信息说明
     */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
    

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Family getFamily() {
        return family;
    }

    public void setFamily(Family family) {
        this.family = family;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Long getRegProvinceId() {
        return regProvinceId;
    }

    public void setRegProvinceId(Long regProvinceId) {
        this.regProvinceId = regProvinceId;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getStrModel() {
        return strModel;
    }

    public void setStrModel(String strModel) {
        this.strModel = strModel;
    }

    public Double getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(Double carPrice) {
        this.carPrice = carPrice;
    }

    public Integer getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Integer factoryId) {
        this.factoryId = factoryId;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public Integer getQuoteCount() {
        return quoteCount;
    }

    public void setQuoteCount(Integer quoteCount) {
        this.quoteCount = quoteCount;
    }

    public Date getLastQuoteDate() {
        return lastQuoteDate;
    }

    public void setLastQuoteDate(Date lastQuoteDate) {
        this.lastQuoteDate = lastQuoteDate;
    }

    public Integer getQuoteCountBig() {
        return quoteCountBig;
    }

    public void setQuoteCountBig(Integer quoteCountBig) {
        this.quoteCountBig = quoteCountBig;
    }

    public Integer getQuoteCountSmall() {
        return quoteCountSmall;
    }

    public void setQuoteCountSmall(Integer quoteCountSmall) {
        this.quoteCountSmall = quoteCountSmall;
    }

    public String getLastQuoteDateStart() {
        return lastQuoteDateStart;
    }

    public void setLastQuoteDateStart(String lastQuoteDateStart) {
        this.lastQuoteDateStart = lastQuoteDateStart;
    }

    public String getLastQuoteDateEnd() {
        return lastQuoteDateEnd;
    }

    public void setLastQuoteDateEnd(String lastQuoteDateEnd) {
        this.lastQuoteDateEnd = lastQuoteDateEnd;
    }

    public String getOrderByColumn() {
        return orderByColumn;
    }

    public void setOrderByColumn(String orderByColumn) {
        this.orderByColumn = orderByColumn;
    }

    public String getHashId() {
        return HashIdsHelper.encode(this.id);
    }



    @Override
	public String toString() {
		return "Vehicle [brandId=" + brandId + ", familyId=" + familyId + ", modelId=" + modelId + ", userId=" + userId
				+ ", provinceId=" + provinceId + ", zoneId=" + zoneId + ", regZoneId=" + regZoneId + ", carYear="
				+ carYear + ", expectPrice=" + expectPrice + ", regDate=" + regDate + ", createDate=" + createDate
				+ ", publicDate=" + publicDate + ", status=" + status + ", outerColor=" + outerColor + ", miles="
				+ miles + ", numberPlate=" + numberPlate + ", reportId=" + reportId + ", info=" + info + ", pictures="
				+ pictures + ", user=" + user + "]";
	}
}