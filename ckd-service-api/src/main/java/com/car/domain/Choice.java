package com.car.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_choice")
public class Choice extends IdEntity {
    /**
     * 问题表ID t_issue.id
     */
    @Column(name = "issue_id")
    private Integer issueId;

    /**
     * 答案描述
     */
    @Column(name = "choice_desc")
    private String choiceDesc;

    /**
     * 状态 1有效 0无效
     */
    private Integer status;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 类型 0普通类型  1其他（需要填写项目）
     */
    @Column(name = "type")
    private Integer type ;

    /**
     * 类型描述
     */
    @Column(name = "type_desc")
    private String typeDesc;

    /**
     * 获取问题表ID t_issue.id
     *
     * @return issue_id - 问题表ID t_issue.id
     */
    public Integer getIssueId() {
        return issueId;
    }

    /**
     * 设置问题表ID t_issue.id
     *
     * @param issueId 问题表ID t_issue.id
     */
    public void setIssueId(Integer issueId) {
        this.issueId = issueId;
    }

    /**
     * 获取答案描述
     *
     * @return choice_desc - 答案描述
     */
    public String getChoiceDesc() {
        return choiceDesc;
    }

    /**
     * 设置答案描述
     *
     * @param choiceDesc 答案描述
     */
    public void setChoiceDesc(String choiceDesc) {
        this.choiceDesc = choiceDesc == null ? null : choiceDesc.trim();
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

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}