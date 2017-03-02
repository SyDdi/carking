package com.car.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Entity
@Table(name = "t_quote")
public class Quote extends IdEntity {
    /**
     * 车商id
     */
    @Column(name = "dealer_id")
    private Long dealerId;

    /**
     * 车源id
     */
    @Column(name = "vehicle_id")
    private Long vehicleId;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 报价
     */
    private Long quote;

    /**
     * 1接受报价0不接受
     */
    private Short status;

    @Column(name = "update_user")
    private Long updateUser;

    @Column(name = "update_date")
    private Date updateDate;
    /**
     * 类型 0正常 1无效 2删除
     */
    private Integer type;

    @Transient
    private String updateUserName;
    @Transient
    private String dealerName ;


    /**
     * 车商名称
     */
    @Transient
    private String nickName;
    public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
    @Transient
    private String quoteDesc;
	public String getQuoteDesc() {
		return quoteDesc;
	}
	public void setQuoteDesc(String quoteDesc) {
		this.quoteDesc = quoteDesc;
	}
	/**
     * 获取车商id
     *
     * @return dealer_id - 车商id
     */
    public Long getDealerId() {
        return dealerId;
    }

    /**
     * 设置车商id
     *
     * @param dealerId 车商id
     */
    public void setDealerId(Long dealerId) {
        this.dealerId = dealerId;
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
     * 获取报价
     *
     * @return quote - 报价
     */
    public Long getQuote() {
        return quote;
    }

    /**
     * 设置报价
     *
     * @param quote 报价
     */
    public void setQuote(Long quote) {
        this.quote = quote;
    }

    /**
     * 获取1接受报价0不接受
     *
     * @return status - 1接受报价0不接受
     */
    public Short getStatus() {
        return status;
    }

    /**
     * 设置1接受报价0不接受
     *
     * @param status 1接受报价0不接受
     */
    public void setStatus(Short status) {
        this.status = status;
    }

    public Long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}