package com.car.portal.dto;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/9/26.
 */
public class PriceItem implements Serializable {

    private String carAge;
    private String retailPrice;
    private String personalTradingPrice;
    private String purchasePrice;

    public String getCarAge() {
        return carAge;
    }

    public void setCarAge(String carAge) {
        this.carAge = carAge;
    }

    public String getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(String retailPrice) {
        this.retailPrice = retailPrice;
    }

    public String getPersonalTradingPrice() {
        return personalTradingPrice;
    }

    public void setPersonalTradingPrice(String personalTradingPrice) {
        this.personalTradingPrice = personalTradingPrice;
    }

    public String getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(String purchasePrice) {
        this.purchasePrice = purchasePrice;
    }




}
