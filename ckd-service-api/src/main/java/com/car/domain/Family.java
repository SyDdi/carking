package com.car.domain;

import javax.persistence.*;

@Table(name = "t_car_family")
public class Family extends IdEntity {


    /**
     * 品牌ID
     */
    @Column(name = "family_id")
    private Integer familyId;

    /**
     * 车系名称
     */
    @Column(name = "family_name")
    private String familyName;

    /**
     * 车系别名
     */
    @Column(name = "family_alias")
    private String familyAlias;

    /**
     * 51汽车ID
     */
    @Column(name = "51car_id")
    private String auto51Id;

    /**
     * 汽车之家ID
     */
    @Column(name = "autohome_id")
    private String autohomeId;

    /**
     * 品牌ID
     */
    @Column(name = "brand_id")
    private Integer brandId;

    /**
     * 厂商ID
     */
    @Column(name = "factory_id")
    private Integer factoryId;

    /**
     * 车系标准照
     */
    private String picture;

    /**
     * 车辆类别：1-轿车 2-SUV 3-旅行车 4-客车 5-跑车 6-MPV
     */
    @Column(name = "vehicle_type")
    private Byte vehicleType;

    /**
     * 车辆类别2:1-越野车 2-面包车
     */
    @Column(name = "vehicle_type_ext")
    private Byte vehicleTypeExt;

    /**
     * 车辆级别：1-微型 2-小型 3-紧凑型 4-中型 5-中大型 6-豪华型
     */
    @Column(name = "vehicle_type_grade")
    private Byte vehicleTypeGrade;

    /**
     * 排序
     */
    private Byte rank;

    /**
     * 状态：1：显示，2：隐藏，0：删除
     */
    private Byte status;


    /**
     * 子车系名，例如宝马三系下面还有320 325等等
     */
    @Transient
    private String subFamilyIds;


    /**
     * 获取品牌ID
     *
     * @return family_id - 品牌ID
     */
    public Integer getFamilyId() {
        return familyId;
    }

    /**
     * 设置品牌ID
     *
     * @param familyId 品牌ID
     */
    public void setFamilyId(Integer familyId) {
        this.familyId = familyId;
    }

    /**
     * 获取车系名称
     *
     * @return family_name - 车系名称
     */
    public String getFamilyName() {
        return familyName;
    }

    /**
     * 设置车系名称
     *
     * @param familyName 车系名称
     */
    public void setFamilyName(String familyName) {
        this.familyName = familyName == null ? null : familyName.trim();
    }

    /**
     * 获取车系别名
     *
     * @return family_alias - 车系别名
     */
    public String getFamilyAlias() {
        return familyAlias;
    }

    /**
     * 设置车系别名
     *
     * @param familyAlias 车系别名
     */
    public void setFamilyAlias(String familyAlias) {
        this.familyAlias = familyAlias == null ? null : familyAlias.trim();
    }

    /**
     * 获取51汽车ID
     *
     * @return 51car_id - 51汽车ID
     */

    public String getAuto51Id() {
        return auto51Id;
    }

    /**
     * 设置51汽车ID
     *
     * @param auto51Id 51汽车ID
     */

    public void setAuto51Id(String auto51Id) {
        this.auto51Id = auto51Id;
    }


    /**
     * 获取汽车之家ID
     *
     * @return autohome_id - 汽车之家ID
     */
    public String getAutohomeId() {
        return autohomeId;
    }

    /**
     * 设置汽车之家ID
     *
     * @param autohomeId 汽车之家ID
     */
    public void setAutohomeId(String autohomeId) {
        this.autohomeId = autohomeId == null ? null : autohomeId.trim();
    }

    /**
     * 获取品牌ID
     *
     * @return brand_id - 品牌ID
     */
    public Integer getBrandId() {
        return brandId;
    }

    /**
     * 设置品牌ID
     *
     * @param brandId 品牌ID
     */
    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    /**
     * 获取厂商ID
     *
     * @return factory_id - 厂商ID
     */
    public Integer getFactoryId() {
        return factoryId;
    }

    /**
     * 设置厂商ID
     *
     * @param factoryId 厂商ID
     */
    public void setFactoryId(Integer factoryId) {
        this.factoryId = factoryId;
    }

    /**
     * 获取车系标准照
     *
     * @return picture - 车系标准照
     */
    public String getPicture() {
        return picture;
    }

    /**
     * 设置车系标准照
     *
     * @param picture 车系标准照
     */
    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }

    /**
     * 获取车辆类别：1-轿车 2-SUV 3-旅行车 4-客车 5-跑车 6-MPV
     *
     * @return vehicle_type - 车辆类别：1-轿车 2-SUV 3-旅行车 4-客车 5-跑车 6-MPV
     */
    public Byte getVehicleType() {
        return vehicleType;
    }

    /**
     * 设置车辆类别：1-轿车 2-SUV 3-旅行车 4-客车 5-跑车 6-MPV
     *
     * @param vehicleType 车辆类别：1-轿车 2-SUV 3-旅行车 4-客车 5-跑车 6-MPV
     */
    public void setVehicleType(Byte vehicleType) {
        this.vehicleType = vehicleType;
    }

    /**
     * 获取车辆类别2:1-越野车 2-面包车
     *
     * @return vehicle_type_ext - 车辆类别2:1-越野车 2-面包车
     */
    public Byte getVehicleTypeExt() {
        return vehicleTypeExt;
    }

    /**
     * 设置车辆类别2:1-越野车 2-面包车
     *
     * @param vehicleTypeExt 车辆类别2:1-越野车 2-面包车
     */
    public void setVehicleTypeExt(Byte vehicleTypeExt) {
        this.vehicleTypeExt = vehicleTypeExt;
    }

    /**
     * 获取车辆级别：1-微型 2-小型 3-紧凑型 4-中型 5-中大型 6-豪华型
     *
     * @return vehicle_type_grade - 车辆级别：1-微型 2-小型 3-紧凑型 4-中型 5-中大型 6-豪华型
     */
    public Byte getVehicleTypeGrade() {
        return vehicleTypeGrade;
    }

    /**
     * 设置车辆级别：1-微型 2-小型 3-紧凑型 4-中型 5-中大型 6-豪华型
     *
     * @param vehicleTypeGrade 车辆级别：1-微型 2-小型 3-紧凑型 4-中型 5-中大型 6-豪华型
     */
    public void setVehicleTypeGrade(Byte vehicleTypeGrade) {
        this.vehicleTypeGrade = vehicleTypeGrade;
    }

    /**
     * 获取排序
     *
     * @return rank - 排序
     */
    public Byte getRank() {
        return rank;
    }

    /**
     * 设置排序
     *
     * @param rank 排序
     */
    public void setRank(Byte rank) {
        this.rank = rank;
    }

    /**
     * 获取状态：1：显示，2：隐藏，0：删除
     *
     * @return status - 状态：1：显示，2：隐藏，0：删除
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置状态：1：显示，2：隐藏，0：删除
     *
     * @param status 状态：1：显示，2：隐藏，0：删除
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getSubFamilyIds() {
        return subFamilyIds;
    }

    public void setSubFamilyIds(String subFamilyIds) {
        this.subFamilyIds = subFamilyIds;
    }
}