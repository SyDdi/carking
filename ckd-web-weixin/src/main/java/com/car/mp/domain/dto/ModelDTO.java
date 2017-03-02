package com.car.mp.domain.dto;

/**
 * Created by Administrator on 2016/10/28.
 */
public class ModelDTO {
    //品牌
    private String brand;
    //厂商
    private String factory;
    //车系
    private String family;


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

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }
}
