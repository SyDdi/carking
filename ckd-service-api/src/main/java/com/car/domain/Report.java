package com.car.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Table(name = "t_report")
public class Report extends IdEntity {
    /**
     * 车辆id   t_vehicle.id
     */
    @Column(name = "vehicle_id")
    private Long vehicleId;

    /**
     * 车况描述
     */
    @Column(name = "report_desc")
    private String reportDesc;

    /**
     * 状态 1有效 0无效
     */
    private Integer status;

    /**
     * 创建人
     */
    @Column(name = "create_user")
    private Long createUser;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 修改时间
     */
    @Column(name = "update_date")
    private Date updateDate;

    /**
     * 修改人
     */
    @Column(name = "update_user")
    private Long updateUser;

    /**
     * 问题
     */
    @Column(name = "issue_ids")
    private String issueIds;
    
    /**
     * 电子更多信息
     */
    @Column(name = "electron")
    private String electron;
    
    /**
     * 过户 更多
     */
    @Column(name = "transfer")
    private String transfer;
    
    /**
     * 答案
     */
    @Column(name = "answer_ids")
    private String answerIds;

    @Transient
    private List<Issue> issue;

    @Transient
    private String updateUserName ;
    
    @Transient
    private String star;
	public String getStar() {
		return star;
	}
	public void setStar(String star) {
		this.star = star;
	}

	/**
     * 获取车辆id   t_vehicle.id
     *
     * @return vehicle_id - 车辆id   t_vehicle.id
     */
    public Long getVehicleId() {
        return vehicleId;
    }

    /**
     * 设置车辆id   t_vehicle.id
     *
     * @param vehicleId 车辆id   t_vehicle.id
     */
    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    /**
     * 获取车况描述
     *
     * @return report_desc - 车况描述
     */
    public String getReportDesc() {
        return reportDesc;
    }

    /**
     * 设置车况描述
     *
     * @param reportDesc 车况描述
     */
    public void setReportDesc(String reportDesc) {
        this.reportDesc = reportDesc == null ? null : reportDesc.trim();
    }

    /**
     * 获取状态 1有效 0无效
     *
     * @return status - 状态 1有效 0无效
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态 1有效 0无效
     *
     * @param status 状态 1有效 0无效
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取创建人
     *
     * @return create_user - 创建人
     */
    public Long getCreateUser() {
        return createUser;
    }

    /**
     * 设置创建人
     *
     * @param createUser 创建人
     */
    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
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
     * 获取修改时间
     *
     * @return update_date - 修改时间
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 设置修改时间
     *
     * @param updateDate 修改时间
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 获取修改人
     *
     * @return update_user - 修改人
     */
    public Long getUpdateUser() {
        return updateUser;
    }

    /**
     * 设置修改人
     *
     * @param updateUser 修改人
     */
    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }
    /**
     * 获取电子信息
     */
    public String getElectron() {
		return electron;
	}
    /**
     * 修改电子信息
     */
	public void setElectron(String electron) {
		this.electron = electron;
	}
    /**
     * 获取转让信息
     */
	public String getTransfer() {
		return transfer;
	}
    /**
     * 修改转让信息
     */
	public void setTransfer(String transfer) {
		this.transfer = transfer;
	}
	
	public String getIssueIds() {
        return issueIds;
    }

    public void setIssueIds(String issueIds) {
        this.issueIds = issueIds;
    }

    public String getAnswerIds() {
        return answerIds;
    }

    public void setAnswerIds(String answerIds) {
        this.answerIds = answerIds;
    }

    public List<Issue> getIssue() {
        return issue;
    }

    public void setIssue(List<Issue> issue) {
        this.issue = issue;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }
}