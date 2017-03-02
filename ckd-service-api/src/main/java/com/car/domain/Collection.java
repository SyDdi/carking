package com.car.domain;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "t_collection")
public class Collection extends IdEntity {
    /**
     * 车源
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
    @OrderBy(" desc")
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 1收藏0取消收藏
     */
    private Short status;

    @Transient
    private Vehicle vehicle;

    /**
     * 获取车源
     *
     * @return vehicle_id - 车源
     */
    public Long getVehicleId() {
        return vehicleId;
    }

    /**
     * 设置车源
     *
     * @param vehicleId 车源
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
     * 获取1收藏0取消收藏
     *
     * @return status - 1收藏0取消收藏
     */
    public Short getStatus() {
        return status;
    }

    /**
     * 设置1收藏0取消收藏
     *
     * @param status 1收藏0取消收藏
     */
    public void setStatus(Short status) {
        this.status = status;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}