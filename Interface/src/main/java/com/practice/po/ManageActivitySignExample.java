package com.practice.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ManageActivitySignExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ManageActivitySignExample() {
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

        public Criteria andActivityIdIsNull() {
            addCriterion("activity_id is null");
            return (Criteria) this;
        }

        public Criteria andActivityIdIsNotNull() {
            addCriterion("activity_id is not null");
            return (Criteria) this;
        }

        public Criteria andActivityIdEqualTo(Long value) {
            addCriterion("activity_id =", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdNotEqualTo(Long value) {
            addCriterion("activity_id <>", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdGreaterThan(Long value) {
            addCriterion("activity_id >", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdGreaterThanOrEqualTo(Long value) {
            addCriterion("activity_id >=", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdLessThan(Long value) {
            addCriterion("activity_id <", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdLessThanOrEqualTo(Long value) {
            addCriterion("activity_id <=", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdIn(List<Long> values) {
            addCriterion("activity_id in", values, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdNotIn(List<Long> values) {
            addCriterion("activity_id not in", values, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdBetween(Long value1, Long value2) {
            addCriterion("activity_id between", value1, value2, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdNotBetween(Long value1, Long value2) {
            addCriterion("activity_id not between", value1, value2, "activityId");
            return (Criteria) this;
        }

        public Criteria andSignInIsNull() {
            addCriterion("sign_in is null");
            return (Criteria) this;
        }

        public Criteria andSignInIsNotNull() {
            addCriterion("sign_in is not null");
            return (Criteria) this;
        }

        public Criteria andSignInEqualTo(Integer value) {
            addCriterion("sign_in =", value, "signIn");
            return (Criteria) this;
        }

        public Criteria andSignInNotEqualTo(Integer value) {
            addCriterion("sign_in <>", value, "signIn");
            return (Criteria) this;
        }

        public Criteria andSignInGreaterThan(Integer value) {
            addCriterion("sign_in >", value, "signIn");
            return (Criteria) this;
        }

        public Criteria andSignInGreaterThanOrEqualTo(Integer value) {
            addCriterion("sign_in >=", value, "signIn");
            return (Criteria) this;
        }

        public Criteria andSignInLessThan(Integer value) {
            addCriterion("sign_in <", value, "signIn");
            return (Criteria) this;
        }

        public Criteria andSignInLessThanOrEqualTo(Integer value) {
            addCriterion("sign_in <=", value, "signIn");
            return (Criteria) this;
        }

        public Criteria andSignInIn(List<Integer> values) {
            addCriterion("sign_in in", values, "signIn");
            return (Criteria) this;
        }

        public Criteria andSignInNotIn(List<Integer> values) {
            addCriterion("sign_in not in", values, "signIn");
            return (Criteria) this;
        }

        public Criteria andSignInBetween(Integer value1, Integer value2) {
            addCriterion("sign_in between", value1, value2, "signIn");
            return (Criteria) this;
        }

        public Criteria andSignInNotBetween(Integer value1, Integer value2) {
            addCriterion("sign_in not between", value1, value2, "signIn");
            return (Criteria) this;
        }

        public Criteria andSignInErcodeIsNull() {
            addCriterion("sign_in_ercode is null");
            return (Criteria) this;
        }

        public Criteria andSignInErcodeIsNotNull() {
            addCriterion("sign_in_ercode is not null");
            return (Criteria) this;
        }

        public Criteria andSignInErcodeEqualTo(String value) {
            addCriterion("sign_in_ercode =", value, "signInErcode");
            return (Criteria) this;
        }

        public Criteria andSignInErcodeNotEqualTo(String value) {
            addCriterion("sign_in_ercode <>", value, "signInErcode");
            return (Criteria) this;
        }

        public Criteria andSignInErcodeGreaterThan(String value) {
            addCriterion("sign_in_ercode >", value, "signInErcode");
            return (Criteria) this;
        }

        public Criteria andSignInErcodeGreaterThanOrEqualTo(String value) {
            addCriterion("sign_in_ercode >=", value, "signInErcode");
            return (Criteria) this;
        }

        public Criteria andSignInErcodeLessThan(String value) {
            addCriterion("sign_in_ercode <", value, "signInErcode");
            return (Criteria) this;
        }

        public Criteria andSignInErcodeLessThanOrEqualTo(String value) {
            addCriterion("sign_in_ercode <=", value, "signInErcode");
            return (Criteria) this;
        }

        public Criteria andSignInErcodeLike(String value) {
            addCriterion("sign_in_ercode like", value, "signInErcode");
            return (Criteria) this;
        }

        public Criteria andSignInErcodeNotLike(String value) {
            addCriterion("sign_in_ercode not like", value, "signInErcode");
            return (Criteria) this;
        }

        public Criteria andSignInErcodeIn(List<String> values) {
            addCriterion("sign_in_ercode in", values, "signInErcode");
            return (Criteria) this;
        }

        public Criteria andSignInErcodeNotIn(List<String> values) {
            addCriterion("sign_in_ercode not in", values, "signInErcode");
            return (Criteria) this;
        }

        public Criteria andSignInErcodeBetween(String value1, String value2) {
            addCriterion("sign_in_ercode between", value1, value2, "signInErcode");
            return (Criteria) this;
        }

        public Criteria andSignInErcodeNotBetween(String value1, String value2) {
            addCriterion("sign_in_ercode not between", value1, value2, "signInErcode");
            return (Criteria) this;
        }

        public Criteria andSignOutIsNull() {
            addCriterion("sign_out is null");
            return (Criteria) this;
        }

        public Criteria andSignOutIsNotNull() {
            addCriterion("sign_out is not null");
            return (Criteria) this;
        }

        public Criteria andSignOutEqualTo(Integer value) {
            addCriterion("sign_out =", value, "signOut");
            return (Criteria) this;
        }

        public Criteria andSignOutNotEqualTo(Integer value) {
            addCriterion("sign_out <>", value, "signOut");
            return (Criteria) this;
        }

        public Criteria andSignOutGreaterThan(Integer value) {
            addCriterion("sign_out >", value, "signOut");
            return (Criteria) this;
        }

        public Criteria andSignOutGreaterThanOrEqualTo(Integer value) {
            addCriterion("sign_out >=", value, "signOut");
            return (Criteria) this;
        }

        public Criteria andSignOutLessThan(Integer value) {
            addCriterion("sign_out <", value, "signOut");
            return (Criteria) this;
        }

        public Criteria andSignOutLessThanOrEqualTo(Integer value) {
            addCriterion("sign_out <=", value, "signOut");
            return (Criteria) this;
        }

        public Criteria andSignOutIn(List<Integer> values) {
            addCriterion("sign_out in", values, "signOut");
            return (Criteria) this;
        }

        public Criteria andSignOutNotIn(List<Integer> values) {
            addCriterion("sign_out not in", values, "signOut");
            return (Criteria) this;
        }

        public Criteria andSignOutBetween(Integer value1, Integer value2) {
            addCriterion("sign_out between", value1, value2, "signOut");
            return (Criteria) this;
        }

        public Criteria andSignOutNotBetween(Integer value1, Integer value2) {
            addCriterion("sign_out not between", value1, value2, "signOut");
            return (Criteria) this;
        }

        public Criteria andSignOutErcodeIsNull() {
            addCriterion("sign_out_ercode is null");
            return (Criteria) this;
        }

        public Criteria andSignOutErcodeIsNotNull() {
            addCriterion("sign_out_ercode is not null");
            return (Criteria) this;
        }

        public Criteria andSignOutErcodeEqualTo(String value) {
            addCriterion("sign_out_ercode =", value, "signOutErcode");
            return (Criteria) this;
        }

        public Criteria andSignOutErcodeNotEqualTo(String value) {
            addCriterion("sign_out_ercode <>", value, "signOutErcode");
            return (Criteria) this;
        }

        public Criteria andSignOutErcodeGreaterThan(String value) {
            addCriterion("sign_out_ercode >", value, "signOutErcode");
            return (Criteria) this;
        }

        public Criteria andSignOutErcodeGreaterThanOrEqualTo(String value) {
            addCriterion("sign_out_ercode >=", value, "signOutErcode");
            return (Criteria) this;
        }

        public Criteria andSignOutErcodeLessThan(String value) {
            addCriterion("sign_out_ercode <", value, "signOutErcode");
            return (Criteria) this;
        }

        public Criteria andSignOutErcodeLessThanOrEqualTo(String value) {
            addCriterion("sign_out_ercode <=", value, "signOutErcode");
            return (Criteria) this;
        }

        public Criteria andSignOutErcodeLike(String value) {
            addCriterion("sign_out_ercode like", value, "signOutErcode");
            return (Criteria) this;
        }

        public Criteria andSignOutErcodeNotLike(String value) {
            addCriterion("sign_out_ercode not like", value, "signOutErcode");
            return (Criteria) this;
        }

        public Criteria andSignOutErcodeIn(List<String> values) {
            addCriterion("sign_out_ercode in", values, "signOutErcode");
            return (Criteria) this;
        }

        public Criteria andSignOutErcodeNotIn(List<String> values) {
            addCriterion("sign_out_ercode not in", values, "signOutErcode");
            return (Criteria) this;
        }

        public Criteria andSignOutErcodeBetween(String value1, String value2) {
            addCriterion("sign_out_ercode between", value1, value2, "signOutErcode");
            return (Criteria) this;
        }

        public Criteria andSignOutErcodeNotBetween(String value1, String value2) {
            addCriterion("sign_out_ercode not between", value1, value2, "signOutErcode");
            return (Criteria) this;
        }

        public Criteria andSignOutTimeIsNull() {
            addCriterion("sign_out_time is null");
            return (Criteria) this;
        }

        public Criteria andSignOutTimeIsNotNull() {
            addCriterion("sign_out_time is not null");
            return (Criteria) this;
        }

        public Criteria andSignOutTimeEqualTo(Long value) {
            addCriterion("sign_out_time =", value, "signOutTime");
            return (Criteria) this;
        }

        public Criteria andSignOutTimeNotEqualTo(Long value) {
            addCriterion("sign_out_time <>", value, "signOutTime");
            return (Criteria) this;
        }

        public Criteria andSignOutTimeGreaterThan(Long value) {
            addCriterion("sign_out_time >", value, "signOutTime");
            return (Criteria) this;
        }

        public Criteria andSignOutTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("sign_out_time >=", value, "signOutTime");
            return (Criteria) this;
        }

        public Criteria andSignOutTimeLessThan(Long value) {
            addCriterion("sign_out_time <", value, "signOutTime");
            return (Criteria) this;
        }

        public Criteria andSignOutTimeLessThanOrEqualTo(Long value) {
            addCriterion("sign_out_time <=", value, "signOutTime");
            return (Criteria) this;
        }

        public Criteria andSignOutTimeIn(List<Long> values) {
            addCriterion("sign_out_time in", values, "signOutTime");
            return (Criteria) this;
        }

        public Criteria andSignOutTimeNotIn(List<Long> values) {
            addCriterion("sign_out_time not in", values, "signOutTime");
            return (Criteria) this;
        }

        public Criteria andSignOutTimeBetween(Long value1, Long value2) {
            addCriterion("sign_out_time between", value1, value2, "signOutTime");
            return (Criteria) this;
        }

        public Criteria andSignOutTimeNotBetween(Long value1, Long value2) {
            addCriterion("sign_out_time not between", value1, value2, "signOutTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIsNull() {
            addCriterion("update_user is null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIsNotNull() {
            addCriterion("update_user is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserEqualTo(Long value) {
            addCriterion("update_user =", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotEqualTo(Long value) {
            addCriterion("update_user <>", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserGreaterThan(Long value) {
            addCriterion("update_user >", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserGreaterThanOrEqualTo(Long value) {
            addCriterion("update_user >=", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLessThan(Long value) {
            addCriterion("update_user <", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLessThanOrEqualTo(Long value) {
            addCriterion("update_user <=", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIn(List<Long> values) {
            addCriterion("update_user in", values, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotIn(List<Long> values) {
            addCriterion("update_user not in", values, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserBetween(Long value1, Long value2) {
            addCriterion("update_user between", value1, value2, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotBetween(Long value1, Long value2) {
            addCriterion("update_user not between", value1, value2, "updateUser");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
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