package com.car.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_weixin_share")
public class WeixinShare extends IdEntity {

    /**
     * 车源id
     */
    @Column(name = "vehicle_id")
    private Long vehicleId;

    /**
     * 用户
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 分享渠道  0朋友圈 1朋友
     */
    @Column(name = "share_source")
    private Integer shareSource;

    /**
     * 分享类型  1 直接分享 其他 间接分享
     */
    private Integer type;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取车源id
     *
     * @return vehicle_id - 车源id
     */
    public Long getVehicleId() {
        return vehicleId;
    }

    /**
     * 设置车源id
     *
     * @param vehicleId 车源id
     */
    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
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
     * 获取分享类型  1 直接分享 其他 间接分享
     *
     * @return type - 分享类型  1 直接分享 其他 间接分享
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置分享类型  1 直接分享 其他 间接分享
     *
     * @param type 分享类型  1 直接分享 其他 间接分享
     */
    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getShareSource() {
        return shareSource;
    }

    public void setShareSource(Integer shareSource) {
        this.shareSource = shareSource;
    }
}