package com.car.domain;

import javax.persistence.*;
@Deprecated
@Table(name = "car_brand")
public class CarBrand extends IdEntity {

    @Column(name = "brand_name_cn")
    private String brandNameCn;

    @Column(name = "brand_name_en")
    private String brandNameEn;

    @Column(name = "first_letter")
    private String firstLetter;

    @Column(name = "brand_id")
    private Integer brandId;

    @Column(name = "father_brand_id")
    private Integer fatherBrandId;

    @Column(name = "brand_level")
    private Integer brandLevel;

    @Column(name = "pic_id")
    private Integer picId;

    @Column(name = "hot_flag")
    private Integer hotFlag;


    /**
     * @return brand_name_cn
     */
    public String getBrandNameCn() {
        return brandNameCn;
    }

    /**
     * @param brandNameCn
     */
    public void setBrandNameCn(String brandNameCn) {
        this.brandNameCn = brandNameCn == null ? null : brandNameCn.trim();
    }

    /**
     * @return brand_name_en
     */
    public String getBrandNameEn() {
        return brandNameEn;
    }

    /**
     * @param brandNameEn
     */
    public void setBrandNameEn(String brandNameEn) {
        this.brandNameEn = brandNameEn == null ? null : brandNameEn.trim();
    }

    /**
     * @return first_letter
     */
    public String getFirstLetter() {
        return firstLetter;
    }

    /**
     * @param firstLetter
     */
    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter == null ? null : firstLetter.trim();
    }

    /**
     * @return brand_id
     */
    public Integer getBrandId() {
        return brandId;
    }

    /**
     * @param brandId
     */
    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    /**
     * @return father_brand_id
     */
    public Integer getFatherBrandId() {
        return fatherBrandId;
    }

    /**
     * @param fatherBrandId
     */
    public void setFatherBrandId(Integer fatherBrandId) {
        this.fatherBrandId = fatherBrandId;
    }

    /**
     * @return brand_level
     */
    public Integer getBrandLevel() {
        return brandLevel;
    }

    /**
     * @param brandLevel
     */
    public void setBrandLevel(Integer brandLevel) {
        this.brandLevel = brandLevel;
    }

    /**
     * @return pic_id
     */
    public Integer getPicId() {
        return picId;
    }

    /**
     * @param picId
     */
    public void setPicId(Integer picId) {
        this.picId = picId;
    }

    /**
     * @return hot_flag
     */
    public Integer getHotFlag() {
        return hotFlag;
    }

    /**
     * @param hotFlag
     */
    public void setHotFlag(Integer hotFlag) {
        this.hotFlag = hotFlag;
    }
}