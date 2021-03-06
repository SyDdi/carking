package com.car.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IssueExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public IssueExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIssueDescIsNull() {
            addCriterion("issue_desc is null");
            return (Criteria) this;
        }

        public Criteria andIssueDescIsNotNull() {
            addCriterion("issue_desc is not null");
            return (Criteria) this;
        }

        public Criteria andIssueDescEqualTo(String value) {
            addCriterion("issue_desc =", value, "issueDesc");
            return (Criteria) this;
        }

        public Criteria andIssueDescNotEqualTo(String value) {
            addCriterion("issue_desc <>", value, "issueDesc");
            return (Criteria) this;
        }

        public Criteria andIssueDescGreaterThan(String value) {
            addCriterion("issue_desc >", value, "issueDesc");
            return (Criteria) this;
        }

        public Criteria andIssueDescGreaterThanOrEqualTo(String value) {
            addCriterion("issue_desc >=", value, "issueDesc");
            return (Criteria) this;
        }

        public Criteria andIssueDescLessThan(String value) {
            addCriterion("issue_desc <", value, "issueDesc");
            return (Criteria) this;
        }

        public Criteria andIssueDescLessThanOrEqualTo(String value) {
            addCriterion("issue_desc <=", value, "issueDesc");
            return (Criteria) this;
        }

        public Criteria andIssueDescLike(String value) {
            addCriterion("issue_desc like", value, "issueDesc");
            return (Criteria) this;
        }

        public Criteria andIssueDescNotLike(String value) {
            addCriterion("issue_desc not like", value, "issueDesc");
            return (Criteria) this;
        }

        public Criteria andIssueDescIn(List<String> values) {
            addCriterion("issue_desc in", values, "issueDesc");
            return (Criteria) this;
        }

        public Criteria andIssueDescNotIn(List<String> values) {
            addCriterion("issue_desc not in", values, "issueDesc");
            return (Criteria) this;
        }

        public Criteria andIssueDescBetween(String value1, String value2) {
            addCriterion("issue_desc between", value1, value2, "issueDesc");
            return (Criteria) this;
        }

        public Criteria andIssueDescNotBetween(String value1, String value2) {
            addCriterion("issue_desc not between", value1, value2, "issueDesc");
            return (Criteria) this;
        }

        public Criteria andIsChoicesIsNull() {
            addCriterion("is_choices is null");
            return (Criteria) this;
        }

        public Criteria andIsChoicesIsNotNull() {
            addCriterion("is_choices is not null");
            return (Criteria) this;
        }

        public Criteria andIsChoicesEqualTo(Integer value) {
            addCriterion("is_choices =", value, "isChoices");
            return (Criteria) this;
        }

        public Criteria andIsChoicesNotEqualTo(Integer value) {
            addCriterion("is_choices <>", value, "isChoices");
            return (Criteria) this;
        }

        public Criteria andIsChoicesGreaterThan(Integer value) {
            addCriterion("is_choices >", value, "isChoices");
            return (Criteria) this;
        }

        public Criteria andIsChoicesGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_choices >=", value, "isChoices");
            return (Criteria) this;
        }

        public Criteria andIsChoicesLessThan(Integer value) {
            addCriterion("is_choices <", value, "isChoices");
            return (Criteria) this;
        }

        public Criteria andIsChoicesLessThanOrEqualTo(Integer value) {
            addCriterion("is_choices <=", value, "isChoices");
            return (Criteria) this;
        }

        public Criteria andIsChoicesIn(List<Integer> values) {
            addCriterion("is_choices in", values, "isChoices");
            return (Criteria) this;
        }

        public Criteria andIsChoicesNotIn(List<Integer> values) {
            addCriterion("is_choices not in", values, "isChoices");
            return (Criteria) this;
        }

        public Criteria andIsChoicesBetween(Integer value1, Integer value2) {
            addCriterion("is_choices between", value1, value2, "isChoices");
            return (Criteria) this;
        }

        public Criteria andIsChoicesNotBetween(Integer value1, Integer value2) {
            addCriterion("is_choices not between", value1, value2, "isChoices");
            return (Criteria) this;
        }

        public Criteria andAnswerIdsIsNull() {
            addCriterion("answer_ids is null");
            return (Criteria) this;
        }

        public Criteria andAnswerIdsIsNotNull() {
            addCriterion("answer_ids is not null");
            return (Criteria) this;
        }

        public Criteria andAnswerIdsEqualTo(String value) {
            addCriterion("answer_ids =", value, "answerIds");
            return (Criteria) this;
        }

        public Criteria andAnswerIdsNotEqualTo(String value) {
            addCriterion("answer_ids <>", value, "answerIds");
            return (Criteria) this;
        }

        public Criteria andAnswerIdsGreaterThan(String value) {
            addCriterion("answer_ids >", value, "answerIds");
            return (Criteria) this;
        }

        public Criteria andAnswerIdsGreaterThanOrEqualTo(String value) {
            addCriterion("answer_ids >=", value, "answerIds");
            return (Criteria) this;
        }

        public Criteria andAnswerIdsLessThan(String value) {
            addCriterion("answer_ids <", value, "answerIds");
            return (Criteria) this;
        }

        public Criteria andAnswerIdsLessThanOrEqualTo(String value) {
            addCriterion("answer_ids <=", value, "answerIds");
            return (Criteria) this;
        }

        public Criteria andAnswerIdsLike(String value) {
            addCriterion("answer_ids like", value, "answerIds");
            return (Criteria) this;
        }

        public Criteria andAnswerIdsNotLike(String value) {
            addCriterion("answer_ids not like", value, "answerIds");
            return (Criteria) this;
        }

        public Criteria andAnswerIdsIn(List<String> values) {
            addCriterion("answer_ids in", values, "answerIds");
            return (Criteria) this;
        }

        public Criteria andAnswerIdsNotIn(List<String> values) {
            addCriterion("answer_ids not in", values, "answerIds");
            return (Criteria) this;
        }

        public Criteria andAnswerIdsBetween(String value1, String value2) {
            addCriterion("answer_ids between", value1, value2, "answerIds");
            return (Criteria) this;
        }

        public Criteria andAnswerIdsNotBetween(String value1, String value2) {
            addCriterion("answer_ids not between", value1, value2, "answerIds");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("create_date is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("create_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("create_date =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("create_date <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("create_date >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("create_date >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("create_date <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("create_date <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("create_date in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("create_date not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("create_date between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("create_date not between", value1, value2, "createDate");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria implements Serializable{

        protected Criteria() {
            super();
        }
    }

    public static class Criterion  implements Serializable {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}