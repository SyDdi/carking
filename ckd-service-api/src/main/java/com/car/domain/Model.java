package com.car.domain;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "t_car_model")
public class Model extends IdEntity {

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
     * 车系ID
     */
    @Column(name = "family_id")
    private Integer familyId;

    /**
     * 车型ID
     */
    @Column(name = "model_id")
    private Integer modelId;

    /**
     * 车型描述
     */
    private String description;

    /**
     * 年款
     */
    @Column(name = "car_year")
    private String carYear;

    /**
     * 上市年月
     */
    @Column(name = "sop_year")
    private String sopYear;

    /**
     * 停产年月
     */
    @Column(name = "eop_year")
    private String eopYear;

    /**
     * 价格上限：元
     */
    @Column(name = "low_price")
    private Integer lowPrice;

    /**
     * 价格下限：元
     */
    @Column(name = "high_price")
    private Integer highPrice;

    /**
     * 排放标准
     */
    @Column(name = "emission_standard")
    private String emissionStandard;

    /**
     * 制造商种类1=进口、2=合资、3=自主
     */
    private Byte category;

    /**
     * 车体结构：如两厢
     */
    @Column(name = "body_style")
    private Byte bodyStyle;

    /**
     * 发动机排量：如2.0
     */
    @Column(name = "swept_volume")
    private String sweptVolume;

    /**
     * 驱动形式：如前驱
     */
    private Byte drive;

    /**
     * 进气形式：如涡轮增压
     */
    @Column(name = "induction_description")
    private String inductionDescription;

    /**
     * 燃油类型：如汽油
     */
    @Column(name = "fuel_type")
    private Byte fuelType;

    /**
     * 供油方式
     */
    @Column(name = "fuel_delivery")
    private Byte fuelDelivery;

    /**
     * 变速箱类型
     */
    @Column(name = "gear_type")
    private Byte gearType;

    /**
     * 车门数
     */
    @Column(name = "door_num")
    private Byte doorNum;

    /**
     * 座位数
     */
    @Column(name = "seat_capacity")
    private Byte seatCapacity;

    /**
     * 长度(mm)
     */
    private Integer length;

    /**
     * 宽度（mm）
     */
    private Integer width;

    /**
     * 高度(mm)
     */
    private Integer heigth;

    /**
     * 汽缸数
     */
    private Byte cylinders;

    /**
     * 最大功率(kW)
     */
    private Integer power;

    /**
     * 最大扭矩(N·m)
     */
    private Integer torque;

    /**
     * 排序
     */
    private Byte rank;

    /**
     * 生产状态：1-在产 0-停产
     */
    @Column(name = "make_status")
    private Byte makeStatus;

    /**
     * 销售状态 ：1-在售 2-停售
     */
    @Column(name = "sale_status")
    private Byte saleStatus;

    /**
     * 状态：1：显示，2：隐藏，0：删除
     */
    private Byte status;

    /**
     * 车系
     */
    private String family;

    /**
     * 车系
     */
    @Transient
    private String familyGroupName;

    /**
     * 厂商
     */
    private String factory;

    /**
     * 汽车之家id
     */
    @Column(name = "autohome_id")
    private String autohomeId;

    /**
     * 51汽车id
     */
    @Column(name = "51car_id")
    private String auto51Id;

    /**
     * 品牌名称
     */
    private String brand;

    /**
     * 价格信息
     */
    @Column(name = "price_info")
    private String priceInfo;

    @Column(name = "match_score")
    private Float matchScore;

    /**
     * 车型短名称
     */
    @Column(name = "short_name")
    private String shortName;

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
     * 获取车系ID
     *
     * @return family_id - 车系ID
     */
    public Integer getFamilyId() {
        return familyId;
    }

    /**
     * 设置车系ID
     *
     * @param familyId 车系ID
     */
    public void setFamilyId(Integer familyId) {
        this.familyId = familyId;
    }

    /**
     * 获取车型ID
     *
     * @return model_id - 车型ID
     */
    public Integer getModelId() {
        return modelId;
    }

    /**
     * 设置车型ID
     *
     * @param modelId 车型ID
     */
    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    /**
     * 获取车型描述
     *
     * @return description - 车型描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置车型描述
     *
     * @param description 车型描述
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
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
     * 获取上市年月
     *
     * @return sop_year - 上市年月
     */
    public String getSopYear() {
        return sopYear;
    }

    /**
     * 设置上市年月
     *
     * @param sopYear 上市年月
     */
    public void setSopYear(String sopYear) {
        this.sopYear = sopYear == null ? null : sopYear.trim();
    }

    /**
     * 获取停产年月
     *
     * @return eop_year - 停产年月
     */
    public String getEopYear() {
        return eopYear;
    }

    /**
     * 设置停产年月
     *
     * @param eopYear 停产年月
     */
    public void setEopYear(String eopYear) {
        this.eopYear = eopYear == null ? null : eopYear.trim();
    }

    /**
     * 获取价格上限：元
     *
     * @return low_price - 价格上限：元
     */
    public Integer getLowPrice() {
        return lowPrice;
    }

    /**
     * 设置价格上限：元
     *
     * @param lowPrice 价格上限：元
     */
    public void setLowPrice(Integer lowPrice) {
        this.lowPrice = lowPrice;
    }

    /**
     * 获取价格下限：元
     *
     * @return high_price - 价格下限：元
     */
    public Integer getHighPrice() {
        return highPrice;
    }

    /**
     * 设置价格下限：元
     *
     * @param highPrice 价格下限：元
     */
    public void setHighPrice(Integer highPrice) {
        this.highPrice = highPrice;
    }

    /**
     * 获取排放标准
     *
     * @return emission_standard - 排放标准
     */
    public String getEmissionStandard() {
        return emissionStandard;
    }

    /**
     * 设置排放标准
     *
     * @param emissionStandard 排放标准
     */
    public void setEmissionStandard(String emissionStandard) {
        this.emissionStandard = emissionStandard == null ? null : emissionStandard.trim();
    }

    /**
     * 获取制造商种类1=进口、2=合资、3=自主
     *
     * @return category - 制造商种类1=进口、2=合资、3=自主
     */
    public Byte getCategory() {
        return category;
    }

    /**
     * 设置制造商种类1=进口、2=合资、3=自主
     *
     * @param category 制造商种类1=进口、2=合资、3=自主
     */
    public void setCategory(Byte category) {
        this.category = category;
    }

    /**
     * 获取车体结构：如两厢
     *
     * @return body_style - 车体结构：如两厢
     */
    public Byte getBodyStyle() {
        return bodyStyle;
    }

    /**
     * 设置车体结构：如两厢
     *
     * @param bodyStyle 车体结构：如两厢
     */
    public void setBodyStyle(Byte bodyStyle) {
        this.bodyStyle = bodyStyle;
    }

    /**
     * 获取发动机排量：如2.0
     *
     * @return swept_volume - 发动机排量：如2.0
     */
    public String getSweptVolume() {
        return sweptVolume;
    }

    /**
     * 设置发动机排量：如2.0
     *
     * @param sweptVolume 发动机排量：如2.0
     */
    public void setSweptVolume(String sweptVolume) {
        this.sweptVolume = sweptVolume == null ? null : sweptVolume.trim();
    }

    /**
     * 获取驱动形式：如前驱
     *
     * @return drive - 驱动形式：如前驱
     */
    public Byte getDrive() {
        return drive;
    }

    /**
     * 设置驱动形式：如前驱
     *
     * @param drive 驱动形式：如前驱
     */
    public void setDrive(Byte drive) {
        this.drive = drive;
    }

    /**
     * 获取进气形式：如涡轮增压
     *
     * @return induction_description - 进气形式：如涡轮增压
     */
    public String getInductionDescription() {
        return inductionDescription;
    }

    /**
     * 设置进气形式：如涡轮增压
     *
     * @param inductionDescription 进气形式：如涡轮增压
     */
    public void setInductionDescription(String inductionDescription) {
        this.inductionDescription = inductionDescription == null ? null : inductionDescription.trim();
    }

    /**
     * 获取燃油类型：如汽油
     *
     * @return fuel_type - 燃油类型：如汽油
     */
    public Byte getFuelType() {
        return fuelType;
    }

    /**
     * 设置燃油类型：如汽油
     *
     * @param fuelType 燃油类型：如汽油
     */
    public void setFuelType(Byte fuelType) {
        this.fuelType = fuelType;
    }

    /**
     * 获取供油方式
     *
     * @return fuel_delivery - 供油方式
     */
    public Byte getFuelDelivery() {
        return fuelDelivery;
    }

    /**
     * 设置供油方式
     *
     * @param fuelDelivery 供油方式
     */
    public void setFuelDelivery(Byte fuelDelivery) {
        this.fuelDelivery = fuelDelivery;
    }

    /**
     * 获取变速箱类型
     *
     * @return gear_type - 变速箱类型
     */
    public Byte getGearType() {
        return gearType;
    }

    /**
     * 设置变速箱类型
     *
     * @param gearType 变速箱类型
     */
    public void setGearType(Byte gearType) {
        this.gearType = gearType;
    }

    /**
     * 获取车门数
     *
     * @return door_num - 车门数
     */
    public Byte getDoorNum() {
        return doorNum;
    }

    /**
     * 设置车门数
     *
     * @param doorNum 车门数
     */
    public void setDoorNum(Byte doorNum) {
        this.doorNum = doorNum;
    }

    /**
     * 获取座位数
     *
     * @return seat_capacity - 座位数
     */
    public Byte getSeatCapacity() {
        return seatCapacity;
    }

    /**
     * 设置座位数
     *
     * @param seatCapacity 座位数
     */
    public void setSeatCapacity(Byte seatCapacity) {
        this.seatCapacity = seatCapacity;
    }

    /**
     * 获取长度(mm)
     *
     * @return length - 长度(mm)
     */
    public Integer getLength() {
        return length;
    }

    /**
     * 设置长度(mm)
     *
     * @param length 长度(mm)
     */
    public void setLength(Integer length) {
        this.length = length;
    }

    /**
     * 获取宽度（mm）
     *
     * @return width - 宽度（mm）
     */
    public Integer getWidth() {
        return width;
    }

    /**
     * 设置宽度（mm）
     *
     * @param width 宽度（mm）
     */
    public void setWidth(Integer width) {
        this.width = width;
    }

    /**
     * 获取高度(mm)
     *
     * @return heigth - 高度(mm)
     */
    public Integer getHeigth() {
        return heigth;
    }

    /**
     * 设置高度(mm)
     *
     * @param heigth 高度(mm)
     */
    public void setHeigth(Integer heigth) {
        this.heigth = heigth;
    }

    /**
     * 获取汽缸数
     *
     * @return cylinders - 汽缸数
     */
    public Byte getCylinders() {
        return cylinders;
    }

    /**
     * 设置汽缸数
     *
     * @param cylinders 汽缸数
     */
    public void setCylinders(Byte cylinders) {
        this.cylinders = cylinders;
    }

    /**
     * 获取最大功率(kW)
     *
     * @return power - 最大功率(kW)
     */
    public Integer getPower() {
        return power;
    }

    /**
     * 设置最大功率(kW)
     *
     * @param power 最大功率(kW)
     */
    public void setPower(Integer power) {
        this.power = power;
    }

    /**
     * 获取最大扭矩(N·m)
     *
     * @return torque - 最大扭矩(N·m)
     */
    public Integer getTorque() {
        return torque;
    }

    /**
     * 设置最大扭矩(N·m)
     *
     * @param torque 最大扭矩(N·m)
     */
    public void setTorque(Integer torque) {
        this.torque = torque;
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
     * 获取生产状态：1-在产 0-停产
     *
     * @return make_status - 生产状态：1-在产 0-停产
     */
    public Byte getMakeStatus() {
        return makeStatus;
    }

    /**
     * 设置生产状态：1-在产 0-停产
     *
     * @param makeStatus 生产状态：1-在产 0-停产
     */
    public void setMakeStatus(Byte makeStatus) {
        this.makeStatus = makeStatus;
    }

    /**
     * 获取销售状态 ：1-在售 2-停售
     *
     * @return sale_status - 销售状态 ：1-在售 2-停售
     */
    public Byte getSaleStatus() {
        return saleStatus;
    }

    /**
     * 设置销售状态 ：1-在售 2-停售
     *
     * @param saleStatus 销售状态 ：1-在售 2-停售
     */
    public void setSaleStatus(Byte saleStatus) {
        this.saleStatus = saleStatus;
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

    /**
     * 获取车系
     *
     * @return family - 车系
     */
    public String getFamily() {
        return family;
    }

    /**
     * 设置车系
     *
     * @param family 车系
     */
    public void setFamily(String family) {
        this.family = family == null ? null : family.trim();
    }

    /**
     * 获取厂商
     *
     * @return factory - 厂商
     */
    public String getFactory() {
        return factory;
    }

    /**
     * 设置厂商
     *
     * @param factory 厂商
     */
    public void setFactory(String factory) {
        this.factory = factory == null ? null : factory.trim();
    }

    /**
     * 获取汽车之家id
     *
     * @return autohome_id - 汽车之家id
     */
    public String getAutohomeId() {
        return autohomeId;
    }

    /**
     * 设置汽车之家id
     *
     * @param autohomeId 汽车之家id
     */
    public void setAutohomeId(String autohomeId) {
        this.autohomeId = autohomeId == null ? null : autohomeId.trim();
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
     * 获取品牌名称
     *
     * @return brand - 品牌名称
     */
    public String getBrand() {
        return brand;
    }

    /**
     * 设置品牌名称
     *
     * @param brand 品牌名称
     */
    public void setBrand(String brand) {
        this.brand = brand == null ? null : brand.trim();
    }

    /**
     * 获取价格信息
     *
     * @return price_info - 价格信息
     */
    public String getPriceInfo() {
        return priceInfo;
    }

    /**
     * 设置价格信息
     *
     * @param priceInfo 价格信息
     */
    public void setPriceInfo(String priceInfo) {
        this.priceInfo = priceInfo == null ? null : priceInfo.trim();
    }

    /**
     * @return match_score
     */
    public Float getMatchScore() {
        return matchScore;
    }

    /**
     * @param matchScore
     */
    public void setMatchScore(Float matchScore) {
        this.matchScore = matchScore;
    }

    /**
     * 获取车型短名称
     *
     * @return short_name - 车型短名称
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * 设置车型短名称
     *
     * @param shortName 车型短名称
     */
    public void setShortName(String shortName) {
        this.shortName = shortName == null ? null : shortName.trim();
    }

    public String getFamilyGroupName() {
        return familyGroupName;
    }

    public void setFamilyGroupName(String familyGroupName) {
        this.familyGroupName = familyGroupName;
    }
}