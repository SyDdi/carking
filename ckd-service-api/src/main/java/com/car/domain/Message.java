package com.car.domain;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "t_message")
public class Message extends IdEntity {
    /**
     * 车源id
     */
    @Column(name = "vehicle_id")
    private Long vehicleId;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 回复的留言对象
     */
    @Column(name = "parent_id")
    private Long parentId;

    @OrderBy(" desc")
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 1正常0删除
     */
    private Short status;

    /**
     * 留言内容
     */
    private String content;


    @Transient
    private int isOnwer;// 是否是车主  0 不是   1是
    @Transient
    private String userName;
    @Transient
    private User user;
    @Transient
    private String createTime;
    public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
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
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取回复的留言对象
     *
     * @return parent_id - 回复的留言对象
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 设置回复的留言对象
     *
     * @param parentId 回复的留言对象
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * @return create_date
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取1正常0删除
     *
     * @return status - 1正常0删除
     */
    public Short getStatus() {
        return status;
    }

    /**
     * 设置1正常0删除
     *
     * @param status 1正常0删除
     */
    public void setStatus(Short status) {
        this.status = status;
    }

    /**
     * 获取留言内容
     *
     * @return content - 留言内容
     */
    public String getContent() {
        return content;
    }
    
    /**
     * 设置留言内容
     *
     * @param content 留言内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getIsOnwer() {
        return isOnwer;
    }

    public void setIsOnwer(int isOnwer) {
        this.isOnwer = isOnwer;
    }
}