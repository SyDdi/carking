package com.car.domain;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "t_weixin_push_msg")
public class WeixinPushMsg extends IdEntity {

    /**
     * 车源ID
     */
    @Column(name = "vehicle_id")
    private Long vehicleId;

    /**
     * 接受用户Id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 内容
     */
    private String content;

    /**
     * 0 未发送 1已发送
     */
    private Integer status;

    /**
     * 1 车商报价 2留言  3后台消息推送  4公众号添加车辆，（是/否）满意报价 
     */
    private Integer type;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 微信接口发送之后返回的id
     */
    @Column(name = "send_id")
    private String sendId;
    /**
     * 微信接口发送之后返回的id
     */
    @Column(name = "send_user")
    private Long sendUser;


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
     * 获取车源ID
     *
     * @return vehicle_id - 车源ID
     */
    public Long getVehicleId() {
        return vehicleId;
    }

    /**
     * 设置车源ID
     *
     * @param vehicleId 车源ID
     */
    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    /**
     * 获取接受用户Id
     *
     * @return user_id - 接受用户Id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置接受用户Id
     *
     * @param userId 接受用户Id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取内容
     *
     * @return content - 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容
     *
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 获取0 未发送 1已发送
     *
     * @return status - 0 未发送 1已发送
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置0 未发送 1已发送
     *
     * @param status 0 未发送 1已发送
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取1 车商报价 2留言  3后台消息推送  4公众号添加车辆，（是/否）满意报价 
     *
     * @return type - 1 车商报价 2留言  3后台消息推送  4公众号添加车辆，（是/否）满意报价 
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置1 车商报价 2留言  3后台消息推送  4公众号添加车辆，（是/否）满意报价 
     *
     * @param type 1 车商报价 2留言  3后台消息推送  4公众号添加车辆，（是/否）满意报价 
     */
    public void setType(Integer type) {
        this.type = type;
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

    public String getSendId() {
        return sendId;
    }

    public void setSendId(String sendId) {
        this.sendId = sendId;
    }

    public Long getSendUser() {
        return sendUser;
    }

    public void setSendUser(Long sendUser) {
        this.sendUser = sendUser;
    }
}