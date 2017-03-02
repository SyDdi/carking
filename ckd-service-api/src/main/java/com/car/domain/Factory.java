package com.car.domain;

import javax.persistence.*;

@Table(name = "t_car_factory")
public class Factory extends IdEntity {


    /**
     * 厂商id
     */
    @Column(name = "factory_id")
    private Integer factoryId;

    /**
     * 厂商名称
     */
    @Column(name = "factory_name")
    private String factoryName;

    /**
     * 厂商别名
     */
    @Column(name = "factory_alias")
    private String factoryAlias;

    private Integer brandId;

    /**
     * 首字母
     */
    private String letter;

    /**
     * 制造商种类1=进口、2=合资、3=自主
     */
    private Byte category;

    /**
     * 排序
     */
    private Byte rank;

    /**
     * 状态：1：显示，2：隐藏，0：删除
     */
    private Byte status;

    /**
     * 厂商图片
     */
    private String picture;

    /**
     * 获取厂商id
     *
     * @return factory_id - 厂商id
     */
    public Integer getFactoryId() {
        return factoryId;
    }

    /**
     * 设置厂商id
     *
     * @param factoryId 厂商id
     */
    public void setFactoryId(Integer factoryId) {
        this.factoryId = factoryId;
    }

    /**
     * 获取厂商名称
     *
     * @return factory_name - 厂商名称
     */
    public String getFactoryName() {
        return factoryName;
    }

    /**
     * 设置厂商名称
     *
     * @param factoryName 厂商名称
     */
    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName == null ? null : factoryName.trim();
    }

    /**
     * 获取厂商别名
     *
     * @return factory_alias - 厂商别名
     */
    public String getFactoryAlias() {
        return factoryAlias;
    }

    /**
     * 设置厂商别名
     *
     * @param factoryAlias 厂商别名
     */
    public void setFactoryAlias(String factoryAlias) {
        this.factoryAlias = factoryAlias == null ? null : factoryAlias.trim();
    }

    /**
     * 获取首字母
     *
     * @return letter - 首字母
     */
    public String getLetter() {
        return letter;
    }

    /**
     * 设置首字母
     *
     * @param letter 首字母
     */
    public void setLetter(String letter) {
        this.letter = letter == null ? null : letter.trim();
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

    /**
     * 获取厂商图片
     *
     * @return picture - 厂商图片
     */
    public String getPicture() {
        return picture;
    }

    /**
     * 设置厂商图片
     *
     * @param picture 厂商图片
     */
    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }


    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }
}