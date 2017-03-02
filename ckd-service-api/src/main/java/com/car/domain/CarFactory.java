package com.car.domain;

import javax.persistence.*;
@Deprecated
@Table(name = "car_factory")
public class CarFactory extends IdEntity {

    @Column(name = "factory_name_cn")
    private String factoryNameCn;

    @Column(name = "factory_name_en")
    private String factoryNameEn;

    @Column(name = "brand_id")
    private Integer brandId;

    @Column(name = "factory_id")
    private Integer factoryId;

    /**
     * @return factory_name_cn
     */
    public String getFactoryNameCn() {
        return factoryNameCn;
    }

    /**
     * @param factoryNameCn
     */
    public void setFactoryNameCn(String factoryNameCn) {
        this.factoryNameCn = factoryNameCn == null ? null : factoryNameCn.trim();
    }

    /**
     * @return factory_name_en
     */
    public String getFactoryNameEn() {
        return factoryNameEn;
    }

    /**
     * @param factoryNameEn
     */
    public void setFactoryNameEn(String factoryNameEn) {
        this.factoryNameEn = factoryNameEn == null ? null : factoryNameEn.trim();
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
     * @return factory_id
     */
    public Integer getFactoryId() {
        return factoryId;
    }

    /**
     * @param factoryId
     */
    public void setFactoryId(Integer factoryId) {
        this.factoryId = factoryId;
    }
}