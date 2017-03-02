package com.car.mp.domain;

import java.util.Date;

/**
 * Created by x201 on 2016/9/20.
 * 历史估值记录
 */
public class HistoryEntity{
    private Integer id;
    private String openId;
    private Date createTime;
    private String ip;

    //来源于---------SearchEntity
    private String colorId;
    private String colorName;
    private String timeId;
    private String timeName;
    private String cityId;
    private String cityName;
    private String modelId;
    private String modelName;
    private String mile;

    //来源于---------ResultEntity
    private String purchasePrice;
    private String retailPrice;
    public HistoryEntity(){

    }

    public HistoryEntity(SearchEntity entity){
        if(entity!=null){
            this.colorId = entity.getColorId();
            this.colorName = entity.getColorName();
            this.timeId = entity.getTimeId();
            this.timeName = entity.getTimeName();
            this.cityId = entity.getCityId();
            this.cityName = entity.getCityName();
            this.modelId = entity.getModelId();
            this.modelName = entity.getModelName();
            this.mile = entity.getMile();
        }
    }

    public void addResultEntity(ResultEntity entity) {
        if (entity != null) {
            this.purchasePrice = entity.getPurchasePrice();
            this.retailPrice = entity.getRetailPrice();
        }
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime=createTime;
    }

    public String getColorId() {
        return colorId;
    }

    public void setColorId(String colorId) {
        this.colorId = colorId;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getTimeId() {
        return timeId;
    }

    public void setTimeId(String timeId) {
        this.timeId = timeId;
    }

    public String getTimeName() {
        return timeName;
    }

    public void setTimeName(String timeName) {
        this.timeName = timeName;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getMile() {
        return mile;
    }

    public void setMile(String mile) {
        this.mile = mile;
    }

    public String getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(String purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(String retailPrice) {
        this.retailPrice = retailPrice;
    }
}
