package com.car.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Table(name = "t_issue")
public class Issue extends IdEntity{

    /**
     * 问题描述
     */
    @Column(name = "issue_desc")
    private String issueDesc;

    /**
     * 是否多选  0 单选  1多选
     */
    @Column(name = "is_choices")
    private Integer isChoices;

    /**
     * 答案 多选用逗号隔开 
     */
    @Column(name = "answer_ids")
    private String answerIds;

    /**
     * 状态 1有效 0无效
     */
    private Integer status;

    /**
     * 问题类型  （预留字段）
     */
    private Integer type;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;


    @Transient
    private List<Choice> choice;
    /**
     * 获取问题描述
     *
     * @return issue_desc - 问题描述
     */
    public String getIssueDesc() {
        return issueDesc;
    }

    /**
     * 设置问题描述
     *
     * @param issueDesc 问题描述
     */
    public void setIssueDesc(String issueDesc) {
        this.issueDesc = issueDesc == null ? null : issueDesc.trim();
    }

    /**
     * 获取是否多选  0 单选  1多选
     *
     * @return is_choices - 是否多选  0 单选  1多选
     */
    public Integer getIsChoices() {
        return isChoices;
    }

    /**
     * 设置是否多选  0 单选  1多选
     *
     * @param isChoices 是否多选  0 单选  1多选
     */
    public void setIsChoices(Integer isChoices) {
        this.isChoices = isChoices;
    }

    /**
     * 获取答案 多选用逗号隔开 
     *
     * @return answer_ids - 答案 多选用逗号隔开 
     */
    public String getAnswerIds() {
        return answerIds;
    }

    /**
     * 设置答案 多选用逗号隔开 
     *
     * @param answerIds 答案 多选用逗号隔开 
     */
    public void setAnswerIds(String answerIds) {
        this.answerIds = answerIds == null ? null : answerIds.trim();
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
     * 获取问题类型  （预留字段）
     *
     * @return type - 问题类型  （预留字段）
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置问题类型  （预留字段）
     *
     * @param type 问题类型  （预留字段）
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

    public List<Choice> getChoice() {
        return choice;
    }

    public void setChoice(List<Choice> choice) {
        this.choice = choice;
    }
}