package com.practice.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ManageActivityExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ManageActivityExample() {
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

        public Criteria andTypeIdIsNull() {
            addCriterion("type_id is null");
            return (Criteria) this;
        }

        public Criteria andTypeIdIsNotNull() {
            addCriterion("type_id is not null");
            return (Criteria) this;
        }

        public Criteria andTypeIdEqualTo(Long value) {
            addCriterion("type_id =", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotEqualTo(Long value) {
            addCriterion("type_id <>", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdGreaterThan(Long value) {
            addCriterion("type_id >", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("type_id >=", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdLessThan(Long value) {
            addCriterion("type_id <", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdLessThanOrEqualTo(Long value) {
            addCriterion("type_id <=", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdIn(List<Long> values) {
            addCriterion("type_id in", values, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotIn(List<Long> values) {
            addCriterion("type_id not in", values, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdBetween(Long value1, Long value2) {
            addCriterion("type_id between", value1, value2, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotBetween(Long value1, Long value2) {
            addCriterion("type_id not between", value1, value2, "typeId");
            return (Criteria) this;
        }

        public Criteria andClassifyIdIsNull() {
            addCriterion("classify_id is null");
            return (Criteria) this;
        }

        public Criteria andClassifyIdIsNotNull() {
            addCriterion("classify_id is not null");
            return (Criteria) this;
        }

        public Criteria andClassifyIdEqualTo(Long value) {
            addCriterion("classify_id =", value, "classifyId");
            return (Criteria) this;
        }

        public Criteria andClassifyIdNotEqualTo(Long value) {
            addCriterion("classify_id <>", value, "classifyId");
            return (Criteria) this;
        }

        public Criteria andClassifyIdGreaterThan(Long value) {
            addCriterion("classify_id >", value, "classifyId");
            return (Criteria) this;
        }

        public Criteria andClassifyIdGreaterThanOrEqualTo(Long value) {
            addCriterion("classify_id >=", value, "classifyId");
            return (Criteria) this;
        }

        public Criteria andClassifyIdLessThan(Long value) {
            addCriterion("classify_id <", value, "classifyId");
            return (Criteria) this;
        }

        public Criteria andClassifyIdLessThanOrEqualTo(Long value) {
            addCriterion("classify_id <=", value, "classifyId");
            return (Criteria) this;
        }

        public Criteria andClassifyIdIn(List<Long> values) {
            addCriterion("classify_id in", values, "classifyId");
            return (Criteria) this;
        }

        public Criteria andClassifyIdNotIn(List<Long> values) {
            addCriterion("classify_id not in", values, "classifyId");
            return (Criteria) this;
        }

        public Criteria andClassifyIdBetween(Long value1, Long value2) {
            addCriterion("classify_id between", value1, value2, "classifyId");
            return (Criteria) this;
        }

        public Criteria andClassifyIdNotBetween(Long value1, Long value2) {
            addCriterion("classify_id not between", value1, value2, "classifyId");
            return (Criteria) this;
        }

        public Criteria andThemeIdIsNull() {
            addCriterion("theme_id is null");
            return (Criteria) this;
        }

        public Criteria andThemeIdIsNotNull() {
            addCriterion("theme_id is not null");
            return (Criteria) this;
        }

        public Criteria andThemeIdEqualTo(Long value) {
            addCriterion("theme_id =", value, "themeId");
            return (Criteria) this;
        }

        public Criteria andThemeIdNotEqualTo(Long value) {
            addCriterion("theme_id <>", value, "themeId");
            return (Criteria) this;
        }

        public Criteria andThemeIdGreaterThan(Long value) {
            addCriterion("theme_id >", value, "themeId");
            return (Criteria) this;
        }

        public Criteria andThemeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("theme_id >=", value, "themeId");
            return (Criteria) this;
        }

        public Criteria andThemeIdLessThan(Long value) {
            addCriterion("theme_id <", value, "themeId");
            return (Criteria) this;
        }

        public Criteria andThemeIdLessThanOrEqualTo(Long value) {
            addCriterion("theme_id <=", value, "themeId");
            return (Criteria) this;
        }

        public Criteria andThemeIdIn(List<Long> values) {
            addCriterion("theme_id in", values, "themeId");
            return (Criteria) this;
        }

        public Criteria andThemeIdNotIn(List<Long> values) {
            addCriterion("theme_id not in", values, "themeId");
            return (Criteria) this;
        }

        public Criteria andThemeIdBetween(Long value1, Long value2) {
            addCriterion("theme_id between", value1, value2, "themeId");
            return (Criteria) this;
        }

        public Criteria andThemeIdNotBetween(Long value1, Long value2) {
            addCriterion("theme_id not between", value1, value2, "themeId");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andOrganizeIdIsNull() {
            addCriterion("organize_id is null");
            return (Criteria) this;
        }

        public Criteria andOrganizeIdIsNotNull() {
            addCriterion("organize_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrganizeIdEqualTo(Long value) {
            addCriterion("organize_id =", value, "organizeId");
            return (Criteria) this;
        }

        public Criteria andOrganizeIdNotEqualTo(Long value) {
            addCriterion("organize_id <>", value, "organizeId");
            return (Criteria) this;
        }

        public Criteria andOrganizeIdGreaterThan(Long value) {
            addCriterion("organize_id >", value, "organizeId");
            return (Criteria) this;
        }

        public Criteria andOrganizeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("organize_id >=", value, "organizeId");
            return (Criteria) this;
        }

        public Criteria andOrganizeIdLessThan(Long value) {
            addCriterion("organize_id <", value, "organizeId");
            return (Criteria) this;
        }

        public Criteria andOrganizeIdLessThanOrEqualTo(Long value) {
            addCriterion("organize_id <=", value, "organizeId");
            return (Criteria) this;
        }

        public Criteria andOrganizeIdIn(List<Long> values) {
            addCriterion("organize_id in", values, "organizeId");
            return (Criteria) this;
        }

        public Criteria andOrganizeIdNotIn(List<Long> values) {
            addCriterion("organize_id not in", values, "organizeId");
            return (Criteria) this;
        }

        public Criteria andOrganizeIdBetween(Long value1, Long value2) {
            addCriterion("organize_id between", value1, value2, "organizeId");
            return (Criteria) this;
        }

        public Criteria andOrganizeIdNotBetween(Long value1, Long value2) {
            addCriterion("organize_id not between", value1, value2, "organizeId");
            return (Criteria) this;
        }

        public Criteria andBaseIdIsNull() {
            addCriterion("base_id is null");
            return (Criteria) this;
        }

        public Criteria andBaseIdIsNotNull() {
            addCriterion("base_id is not null");
            return (Criteria) this;
        }

        public Criteria andBaseIdEqualTo(Long value) {
            addCriterion("base_id =", value, "baseId");
            return (Criteria) this;
        }

        public Criteria andBaseIdNotEqualTo(Long value) {
            addCriterion("base_id <>", value, "baseId");
            return (Criteria) this;
        }

        public Criteria andBaseIdGreaterThan(Long value) {
            addCriterion("base_id >", value, "baseId");
            return (Criteria) this;
        }

        public Criteria andBaseIdGreaterThanOrEqualTo(Long value) {
            addCriterion("base_id >=", value, "baseId");
            return (Criteria) this;
        }

        public Criteria andBaseIdLessThan(Long value) {
            addCriterion("base_id <", value, "baseId");
            return (Criteria) this;
        }

        public Criteria andBaseIdLessThanOrEqualTo(Long value) {
            addCriterion("base_id <=", value, "baseId");
            return (Criteria) this;
        }

        public Criteria andBaseIdIn(List<Long> values) {
            addCriterion("base_id in", values, "baseId");
            return (Criteria) this;
        }

        public Criteria andBaseIdNotIn(List<Long> values) {
            addCriterion("base_id not in", values, "baseId");
            return (Criteria) this;
        }

        public Criteria andBaseIdBetween(Long value1, Long value2) {
            addCriterion("base_id between", value1, value2, "baseId");
            return (Criteria) this;
        }

        public Criteria andBaseIdNotBetween(Long value1, Long value2) {
            addCriterion("base_id not between", value1, value2, "baseId");
            return (Criteria) this;
        }

        public Criteria andDurationIsNull() {
            addCriterion("duration is null");
            return (Criteria) this;
        }

        public Criteria andDurationIsNotNull() {
            addCriterion("duration is not null");
            return (Criteria) this;
        }

        public Criteria andDurationEqualTo(Integer value) {
            addCriterion("duration =", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotEqualTo(Integer value) {
            addCriterion("duration <>", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationGreaterThan(Integer value) {
            addCriterion("duration >", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationGreaterThanOrEqualTo(Integer value) {
            addCriterion("duration >=", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationLessThan(Integer value) {
            addCriterion("duration <", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationLessThanOrEqualTo(Integer value) {
            addCriterion("duration <=", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationIn(List<Integer> values) {
            addCriterion("duration in", values, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotIn(List<Integer> values) {
            addCriterion("duration not in", values, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationBetween(Integer value1, Integer value2) {
            addCriterion("duration between", value1, value2, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotBetween(Integer value1, Integer value2) {
            addCriterion("duration not between", value1, value2, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationTypeIsNull() {
            addCriterion("duration_type is null");
            return (Criteria) this;
        }

        public Criteria andDurationTypeIsNotNull() {
            addCriterion("duration_type is not null");
            return (Criteria) this;
        }

        public Criteria andDurationTypeEqualTo(Integer value) {
            addCriterion("duration_type =", value, "durationType");
            return (Criteria) this;
        }

        public Criteria andDurationTypeNotEqualTo(Integer value) {
            addCriterion("duration_type <>", value, "durationType");
            return (Criteria) this;
        }

        public Criteria andDurationTypeGreaterThan(Integer value) {
            addCriterion("duration_type >", value, "durationType");
            return (Criteria) this;
        }

        public Criteria andDurationTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("duration_type >=", value, "durationType");
            return (Criteria) this;
        }

        public Criteria andDurationTypeLessThan(Integer value) {
            addCriterion("duration_type <", value, "durationType");
            return (Criteria) this;
        }

        public Criteria andDurationTypeLessThanOrEqualTo(Integer value) {
            addCriterion("duration_type <=", value, "durationType");
            return (Criteria) this;
        }

        public Criteria andDurationTypeIn(List<Integer> values) {
            addCriterion("duration_type in", values, "durationType");
            return (Criteria) this;
        }

        public Criteria andDurationTypeNotIn(List<Integer> values) {
            addCriterion("duration_type not in", values, "durationType");
            return (Criteria) this;
        }

        public Criteria andDurationTypeBetween(Integer value1, Integer value2) {
            addCriterion("duration_type between", value1, value2, "durationType");
            return (Criteria) this;
        }

        public Criteria andDurationTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("duration_type not between", value1, value2, "durationType");
            return (Criteria) this;
        }

        public Criteria andValidTimeIsNull() {
            addCriterion("valid_time is null");
            return (Criteria) this;
        }

        public Criteria andValidTimeIsNotNull() {
            addCriterion("valid_time is not null");
            return (Criteria) this;
        }

        public Criteria andValidTimeEqualTo(String value) {
            addCriterion("valid_time =", value, "validTime");
            return (Criteria) this;
        }

        public Criteria andValidTimeNotEqualTo(String value) {
            addCriterion("valid_time <>", value, "validTime");
            return (Criteria) this;
        }

        public Criteria andValidTimeGreaterThan(String value) {
            addCriterion("valid_time >", value, "validTime");
            return (Criteria) this;
        }

        public Criteria andValidTimeGreaterThanOrEqualTo(String value) {
            addCriterion("valid_time >=", value, "validTime");
            return (Criteria) this;
        }

        public Criteria andValidTimeLessThan(String value) {
            addCriterion("valid_time <", value, "validTime");
            return (Criteria) this;
        }

        public Criteria andValidTimeLessThanOrEqualTo(String value) {
            addCriterion("valid_time <=", value, "validTime");
            return (Criteria) this;
        }

        public Criteria andValidTimeLike(String value) {
            addCriterion("valid_time like", value, "validTime");
            return (Criteria) this;
        }

        public Criteria andValidTimeNotLike(String value) {
            addCriterion("valid_time not like", value, "validTime");
            return (Criteria) this;
        }

        public Criteria andValidTimeIn(List<String> values) {
            addCriterion("valid_time in", values, "validTime");
            return (Criteria) this;
        }

        public Criteria andValidTimeNotIn(List<String> values) {
            addCriterion("valid_time not in", values, "validTime");
            return (Criteria) this;
        }

        public Criteria andValidTimeBetween(String value1, String value2) {
            addCriterion("valid_time between", value1, value2, "validTime");
            return (Criteria) this;
        }

        public Criteria andValidTimeNotBetween(String value1, String value2) {
            addCriterion("valid_time not between", value1, value2, "validTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIsNull() {
            addCriterion("begin_time is null");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIsNotNull() {
            addCriterion("begin_time is not null");
            return (Criteria) this;
        }

        public Criteria andBeginTimeEqualTo(Date value) {
            addCriterion("begin_time =", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotEqualTo(Date value) {
            addCriterion("begin_time <>", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeGreaterThan(Date value) {
            addCriterion("begin_time >", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("begin_time >=", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeLessThan(Date value) {
            addCriterion("begin_time <", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeLessThanOrEqualTo(Date value) {
            addCriterion("begin_time <=", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIn(List<Date> values) {
            addCriterion("begin_time in", values, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotIn(List<Date> values) {
            addCriterion("begin_time not in", values, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeBetween(Date value1, Date value2) {
            addCriterion("begin_time between", value1, value2, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotBetween(Date value1, Date value2) {
            addCriterion("begin_time not between", value1, value2, "beginTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("end_time is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Date value) {
            addCriterion("end_time =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Date value) {
            addCriterion("end_time <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Date value) {
            addCriterion("end_time >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("end_time >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Date value) {
            addCriterion("end_time <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("end_time <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Date> values) {
            addCriterion("end_time in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Date> values) {
            addCriterion("end_time not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterion("end_time between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("end_time not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andSelfIsNull() {
            addCriterion("self is null");
            return (Criteria) this;
        }

        public Criteria andSelfIsNotNull() {
            addCriterion("self is not null");
            return (Criteria) this;
        }

        public Criteria andSelfEqualTo(Integer value) {
            addCriterion("self =", value, "self");
            return (Criteria) this;
        }

        public Criteria andSelfNotEqualTo(Integer value) {
            addCriterion("self <>", value, "self");
            return (Criteria) this;
        }

        public Criteria andSelfGreaterThan(Integer value) {
            addCriterion("self >", value, "self");
            return (Criteria) this;
        }

        public Criteria andSelfGreaterThanOrEqualTo(Integer value) {
            addCriterion("self >=", value, "self");
            return (Criteria) this;
        }

        public Criteria andSelfLessThan(Integer value) {
            addCriterion("self <", value, "self");
            return (Criteria) this;
        }

        public Criteria andSelfLessThanOrEqualTo(Integer value) {
            addCriterion("self <=", value, "self");
            return (Criteria) this;
        }

        public Criteria andSelfIn(List<Integer> values) {
            addCriterion("self in", values, "self");
            return (Criteria) this;
        }

        public Criteria andSelfNotIn(List<Integer> values) {
            addCriterion("self not in", values, "self");
            return (Criteria) this;
        }

        public Criteria andSelfBetween(Integer value1, Integer value2) {
            addCriterion("self between", value1, value2, "self");
            return (Criteria) this;
        }

        public Criteria andSelfNotBetween(Integer value1, Integer value2) {
            addCriterion("self not between", value1, value2, "self");
            return (Criteria) this;
        }

        public Criteria andSignIsNull() {
            addCriterion("sign is null");
            return (Criteria) this;
        }

        public Criteria andSignIsNotNull() {
            addCriterion("sign is not null");
            return (Criteria) this;
        }

        public Criteria andSignEqualTo(Integer value) {
            addCriterion("sign =", value, "sign");
            return (Criteria) this;
        }

        public Criteria andSignNotEqualTo(Integer value) {
            addCriterion("sign <>", value, "sign");
            return (Criteria) this;
        }

        public Criteria andSignGreaterThan(Integer value) {
            addCriterion("sign >", value, "sign");
            return (Criteria) this;
        }

        public Criteria andSignGreaterThanOrEqualTo(Integer value) {
            addCriterion("sign >=", value, "sign");
            return (Criteria) this;
        }

        public Criteria andSignLessThan(Integer value) {
            addCriterion("sign <", value, "sign");
            return (Criteria) this;
        }

        public Criteria andSignLessThanOrEqualTo(Integer value) {
            addCriterion("sign <=", value, "sign");
            return (Criteria) this;
        }

        public Criteria andSignIn(List<Integer> values) {
            addCriterion("sign in", values, "sign");
            return (Criteria) this;
        }

        public Criteria andSignNotIn(List<Integer> values) {
            addCriterion("sign not in", values, "sign");
            return (Criteria) this;
        }

        public Criteria andSignBetween(Integer value1, Integer value2) {
            addCriterion("sign between", value1, value2, "sign");
            return (Criteria) this;
        }

        public Criteria andSignNotBetween(Integer value1, Integer value2) {
            addCriterion("sign not between", value1, value2, "sign");
            return (Criteria) this;
        }

        public Criteria andNumberIsNull() {
            addCriterion("number is null");
            return (Criteria) this;
        }

        public Criteria andNumberIsNotNull() {
            addCriterion("number is not null");
            return (Criteria) this;
        }

        public Criteria andNumberEqualTo(Integer value) {
            addCriterion("number =", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotEqualTo(Integer value) {
            addCriterion("number <>", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThan(Integer value) {
            addCriterion("number >", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("number >=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThan(Integer value) {
            addCriterion("number <", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThanOrEqualTo(Integer value) {
            addCriterion("number <=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberIn(List<Integer> values) {
            addCriterion("number in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotIn(List<Integer> values) {
            addCriterion("number not in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberBetween(Integer value1, Integer value2) {
            addCriterion("number between", value1, value2, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("number not between", value1, value2, "number");
            return (Criteria) this;
        }

        public Criteria andStockIsNull() {
            addCriterion("stock is null");
            return (Criteria) this;
        }

        public Criteria andStockIsNotNull() {
            addCriterion("stock is not null");
            return (Criteria) this;
        }

        public Criteria andStockEqualTo(Integer value) {
            addCriterion("stock =", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockNotEqualTo(Integer value) {
            addCriterion("stock <>", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockGreaterThan(Integer value) {
            addCriterion("stock >", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockGreaterThanOrEqualTo(Integer value) {
            addCriterion("stock >=", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockLessThan(Integer value) {
            addCriterion("stock <", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockLessThanOrEqualTo(Integer value) {
            addCriterion("stock <=", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockIn(List<Integer> values) {
            addCriterion("stock in", values, "stock");
            return (Criteria) this;
        }

        public Criteria andStockNotIn(List<Integer> values) {
            addCriterion("stock not in", values, "stock");
            return (Criteria) this;
        }

        public Criteria andStockBetween(Integer value1, Integer value2) {
            addCriterion("stock between", value1, value2, "stock");
            return (Criteria) this;
        }

        public Criteria andStockNotBetween(Integer value1, Integer value2) {
            addCriterion("stock not between", value1, value2, "stock");
            return (Criteria) this;
        }

        public Criteria andCloseTypeIsNull() {
            addCriterion("close_type is null");
            return (Criteria) this;
        }

        public Criteria andCloseTypeIsNotNull() {
            addCriterion("close_type is not null");
            return (Criteria) this;
        }

        public Criteria andCloseTypeEqualTo(Integer value) {
            addCriterion("close_type =", value, "closeType");
            return (Criteria) this;
        }

        public Criteria andCloseTypeNotEqualTo(Integer value) {
            addCriterion("close_type <>", value, "closeType");
            return (Criteria) this;
        }

        public Criteria andCloseTypeGreaterThan(Integer value) {
            addCriterion("close_type >", value, "closeType");
            return (Criteria) this;
        }

        public Criteria andCloseTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("close_type >=", value, "closeType");
            return (Criteria) this;
        }

        public Criteria andCloseTypeLessThan(Integer value) {
            addCriterion("close_type <", value, "closeType");
            return (Criteria) this;
        }

        public Criteria andCloseTypeLessThanOrEqualTo(Integer value) {
            addCriterion("close_type <=", value, "closeType");
            return (Criteria) this;
        }

        public Criteria andCloseTypeIn(List<Integer> values) {
            addCriterion("close_type in", values, "closeType");
            return (Criteria) this;
        }

        public Criteria andCloseTypeNotIn(List<Integer> values) {
            addCriterion("close_type not in", values, "closeType");
            return (Criteria) this;
        }

        public Criteria andCloseTypeBetween(Integer value1, Integer value2) {
            addCriterion("close_type between", value1, value2, "closeType");
            return (Criteria) this;
        }

        public Criteria andCloseTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("close_type not between", value1, value2, "closeType");
            return (Criteria) this;
        }

        public Criteria andCloseTimeIsNull() {
            addCriterion("close_time is null");
            return (Criteria) this;
        }

        public Criteria andCloseTimeIsNotNull() {
            addCriterion("close_time is not null");
            return (Criteria) this;
        }

        public Criteria andCloseTimeEqualTo(Date value) {
            addCriterion("close_time =", value, "closeTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeNotEqualTo(Date value) {
            addCriterion("close_time <>", value, "closeTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeGreaterThan(Date value) {
            addCriterion("close_time >", value, "closeTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("close_time >=", value, "closeTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeLessThan(Date value) {
            addCriterion("close_time <", value, "closeTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeLessThanOrEqualTo(Date value) {
            addCriterion("close_time <=", value, "closeTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeIn(List<Date> values) {
            addCriterion("close_time in", values, "closeTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeNotIn(List<Date> values) {
            addCriterion("close_time not in", values, "closeTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeBetween(Date value1, Date value2) {
            addCriterion("close_time between", value1, value2, "closeTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeNotBetween(Date value1, Date value2) {
            addCriterion("close_time not between", value1, value2, "closeTime");
            return (Criteria) this;
        }

        public Criteria andMoneyIsNull() {
            addCriterion("money is null");
            return (Criteria) this;
        }

        public Criteria andMoneyIsNotNull() {
            addCriterion("money is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyEqualTo(Integer value) {
            addCriterion("money =", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotEqualTo(Integer value) {
            addCriterion("money <>", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThan(Integer value) {
            addCriterion("money >", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThanOrEqualTo(Integer value) {
            addCriterion("money >=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThan(Integer value) {
            addCriterion("money <", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThanOrEqualTo(Integer value) {
            addCriterion("money <=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyIn(List<Integer> values) {
            addCriterion("money in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotIn(List<Integer> values) {
            addCriterion("money not in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyBetween(Integer value1, Integer value2) {
            addCriterion("money between", value1, value2, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotBetween(Integer value1, Integer value2) {
            addCriterion("money not between", value1, value2, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyDescIsNull() {
            addCriterion("money_desc is null");
            return (Criteria) this;
        }

        public Criteria andMoneyDescIsNotNull() {
            addCriterion("money_desc is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyDescEqualTo(String value) {
            addCriterion("money_desc =", value, "moneyDesc");
            return (Criteria) this;
        }

        public Criteria andMoneyDescNotEqualTo(String value) {
            addCriterion("money_desc <>", value, "moneyDesc");
            return (Criteria) this;
        }

        public Criteria andMoneyDescGreaterThan(String value) {
            addCriterion("money_desc >", value, "moneyDesc");
            return (Criteria) this;
        }

        public Criteria andMoneyDescGreaterThanOrEqualTo(String value) {
            addCriterion("money_desc >=", value, "moneyDesc");
            return (Criteria) this;
        }

        public Criteria andMoneyDescLessThan(String value) {
            addCriterion("money_desc <", value, "moneyDesc");
            return (Criteria) this;
        }

        public Criteria andMoneyDescLessThanOrEqualTo(String value) {
            addCriterion("money_desc <=", value, "moneyDesc");
            return (Criteria) this;
        }

        public Criteria andMoneyDescLike(String value) {
            addCriterion("money_desc like", value, "moneyDesc");
            return (Criteria) this;
        }

        public Criteria andMoneyDescNotLike(String value) {
            addCriterion("money_desc not like", value, "moneyDesc");
            return (Criteria) this;
        }

        public Criteria andMoneyDescIn(List<String> values) {
            addCriterion("money_desc in", values, "moneyDesc");
            return (Criteria) this;
        }

        public Criteria andMoneyDescNotIn(List<String> values) {
            addCriterion("money_desc not in", values, "moneyDesc");
            return (Criteria) this;
        }

        public Criteria andMoneyDescBetween(String value1, String value2) {
            addCriterion("money_desc between", value1, value2, "moneyDesc");
            return (Criteria) this;
        }

        public Criteria andMoneyDescNotBetween(String value1, String value2) {
            addCriterion("money_desc not between", value1, value2, "moneyDesc");
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

        public Criteria andCheckLeaderIsNull() {
            addCriterion("check_leader is null");
            return (Criteria) this;
        }

        public Criteria andCheckLeaderIsNotNull() {
            addCriterion("check_leader is not null");
            return (Criteria) this;
        }

        public Criteria andCheckLeaderEqualTo(Integer value) {
            addCriterion("check_leader =", value, "checkLeader");
            return (Criteria) this;
        }

        public Criteria andCheckLeaderNotEqualTo(Integer value) {
            addCriterion("check_leader <>", value, "checkLeader");
            return (Criteria) this;
        }

        public Criteria andCheckLeaderGreaterThan(Integer value) {
            addCriterion("check_leader >", value, "checkLeader");
            return (Criteria) this;
        }

        public Criteria andCheckLeaderGreaterThanOrEqualTo(Integer value) {
            addCriterion("check_leader >=", value, "checkLeader");
            return (Criteria) this;
        }

        public Criteria andCheckLeaderLessThan(Integer value) {
            addCriterion("check_leader <", value, "checkLeader");
            return (Criteria) this;
        }

        public Criteria andCheckLeaderLessThanOrEqualTo(Integer value) {
            addCriterion("check_leader <=", value, "checkLeader");
            return (Criteria) this;
        }

        public Criteria andCheckLeaderIn(List<Integer> values) {
            addCriterion("check_leader in", values, "checkLeader");
            return (Criteria) this;
        }

        public Criteria andCheckLeaderNotIn(List<Integer> values) {
            addCriterion("check_leader not in", values, "checkLeader");
            return (Criteria) this;
        }

        public Criteria andCheckLeaderBetween(Integer value1, Integer value2) {
            addCriterion("check_leader between", value1, value2, "checkLeader");
            return (Criteria) this;
        }

        public Criteria andCheckLeaderNotBetween(Integer value1, Integer value2) {
            addCriterion("check_leader not between", value1, value2, "checkLeader");
            return (Criteria) this;
        }

        public Criteria andCheckApplyIsNull() {
            addCriterion("check_apply is null");
            return (Criteria) this;
        }

        public Criteria andCheckApplyIsNotNull() {
            addCriterion("check_apply is not null");
            return (Criteria) this;
        }

        public Criteria andCheckApplyEqualTo(Integer value) {
            addCriterion("check_apply =", value, "checkApply");
            return (Criteria) this;
        }

        public Criteria andCheckApplyNotEqualTo(Integer value) {
            addCriterion("check_apply <>", value, "checkApply");
            return (Criteria) this;
        }

        public Criteria andCheckApplyGreaterThan(Integer value) {
            addCriterion("check_apply >", value, "checkApply");
            return (Criteria) this;
        }

        public Criteria andCheckApplyGreaterThanOrEqualTo(Integer value) {
            addCriterion("check_apply >=", value, "checkApply");
            return (Criteria) this;
        }

        public Criteria andCheckApplyLessThan(Integer value) {
            addCriterion("check_apply <", value, "checkApply");
            return (Criteria) this;
        }

        public Criteria andCheckApplyLessThanOrEqualTo(Integer value) {
            addCriterion("check_apply <=", value, "checkApply");
            return (Criteria) this;
        }

        public Criteria andCheckApplyIn(List<Integer> values) {
            addCriterion("check_apply in", values, "checkApply");
            return (Criteria) this;
        }

        public Criteria andCheckApplyNotIn(List<Integer> values) {
            addCriterion("check_apply not in", values, "checkApply");
            return (Criteria) this;
        }

        public Criteria andCheckApplyBetween(Integer value1, Integer value2) {
            addCriterion("check_apply between", value1, value2, "checkApply");
            return (Criteria) this;
        }

        public Criteria andCheckApplyNotBetween(Integer value1, Integer value2) {
            addCriterion("check_apply not between", value1, value2, "checkApply");
            return (Criteria) this;
        }

        public Criteria andCheckSignIsNull() {
            addCriterion("check_sign is null");
            return (Criteria) this;
        }

        public Criteria andCheckSignIsNotNull() {
            addCriterion("check_sign is not null");
            return (Criteria) this;
        }

        public Criteria andCheckSignEqualTo(Integer value) {
            addCriterion("check_sign =", value, "checkSign");
            return (Criteria) this;
        }

        public Criteria andCheckSignNotEqualTo(Integer value) {
            addCriterion("check_sign <>", value, "checkSign");
            return (Criteria) this;
        }

        public Criteria andCheckSignGreaterThan(Integer value) {
            addCriterion("check_sign >", value, "checkSign");
            return (Criteria) this;
        }

        public Criteria andCheckSignGreaterThanOrEqualTo(Integer value) {
            addCriterion("check_sign >=", value, "checkSign");
            return (Criteria) this;
        }

        public Criteria andCheckSignLessThan(Integer value) {
            addCriterion("check_sign <", value, "checkSign");
            return (Criteria) this;
        }

        public Criteria andCheckSignLessThanOrEqualTo(Integer value) {
            addCriterion("check_sign <=", value, "checkSign");
            return (Criteria) this;
        }

        public Criteria andCheckSignIn(List<Integer> values) {
            addCriterion("check_sign in", values, "checkSign");
            return (Criteria) this;
        }

        public Criteria andCheckSignNotIn(List<Integer> values) {
            addCriterion("check_sign not in", values, "checkSign");
            return (Criteria) this;
        }

        public Criteria andCheckSignBetween(Integer value1, Integer value2) {
            addCriterion("check_sign between", value1, value2, "checkSign");
            return (Criteria) this;
        }

        public Criteria andCheckSignNotBetween(Integer value1, Integer value2) {
            addCriterion("check_sign not between", value1, value2, "checkSign");
            return (Criteria) this;
        }

        public Criteria andCheckSuperviseIsNull() {
            addCriterion("check_supervise is null");
            return (Criteria) this;
        }

        public Criteria andCheckSuperviseIsNotNull() {
            addCriterion("check_supervise is not null");
            return (Criteria) this;
        }

        public Criteria andCheckSuperviseEqualTo(Integer value) {
            addCriterion("check_supervise =", value, "checkSupervise");
            return (Criteria) this;
        }

        public Criteria andCheckSuperviseNotEqualTo(Integer value) {
            addCriterion("check_supervise <>", value, "checkSupervise");
            return (Criteria) this;
        }

        public Criteria andCheckSuperviseGreaterThan(Integer value) {
            addCriterion("check_supervise >", value, "checkSupervise");
            return (Criteria) this;
        }

        public Criteria andCheckSuperviseGreaterThanOrEqualTo(Integer value) {
            addCriterion("check_supervise >=", value, "checkSupervise");
            return (Criteria) this;
        }

        public Criteria andCheckSuperviseLessThan(Integer value) {
            addCriterion("check_supervise <", value, "checkSupervise");
            return (Criteria) this;
        }

        public Criteria andCheckSuperviseLessThanOrEqualTo(Integer value) {
            addCriterion("check_supervise <=", value, "checkSupervise");
            return (Criteria) this;
        }

        public Criteria andCheckSuperviseIn(List<Integer> values) {
            addCriterion("check_supervise in", values, "checkSupervise");
            return (Criteria) this;
        }

        public Criteria andCheckSuperviseNotIn(List<Integer> values) {
            addCriterion("check_supervise not in", values, "checkSupervise");
            return (Criteria) this;
        }

        public Criteria andCheckSuperviseBetween(Integer value1, Integer value2) {
            addCriterion("check_supervise between", value1, value2, "checkSupervise");
            return (Criteria) this;
        }

        public Criteria andCheckSuperviseNotBetween(Integer value1, Integer value2) {
            addCriterion("check_supervise not between", value1, value2, "checkSupervise");
            return (Criteria) this;
        }

        public Criteria andCheckTaskIsNull() {
            addCriterion("check_task is null");
            return (Criteria) this;
        }

        public Criteria andCheckTaskIsNotNull() {
            addCriterion("check_task is not null");
            return (Criteria) this;
        }

        public Criteria andCheckTaskEqualTo(Integer value) {
            addCriterion("check_task =", value, "checkTask");
            return (Criteria) this;
        }

        public Criteria andCheckTaskNotEqualTo(Integer value) {
            addCriterion("check_task <>", value, "checkTask");
            return (Criteria) this;
        }

        public Criteria andCheckTaskGreaterThan(Integer value) {
            addCriterion("check_task >", value, "checkTask");
            return (Criteria) this;
        }

        public Criteria andCheckTaskGreaterThanOrEqualTo(Integer value) {
            addCriterion("check_task >=", value, "checkTask");
            return (Criteria) this;
        }

        public Criteria andCheckTaskLessThan(Integer value) {
            addCriterion("check_task <", value, "checkTask");
            return (Criteria) this;
        }

        public Criteria andCheckTaskLessThanOrEqualTo(Integer value) {
            addCriterion("check_task <=", value, "checkTask");
            return (Criteria) this;
        }

        public Criteria andCheckTaskIn(List<Integer> values) {
            addCriterion("check_task in", values, "checkTask");
            return (Criteria) this;
        }

        public Criteria andCheckTaskNotIn(List<Integer> values) {
            addCriterion("check_task not in", values, "checkTask");
            return (Criteria) this;
        }

        public Criteria andCheckTaskBetween(Integer value1, Integer value2) {
            addCriterion("check_task between", value1, value2, "checkTask");
            return (Criteria) this;
        }

        public Criteria andCheckTaskNotBetween(Integer value1, Integer value2) {
            addCriterion("check_task not between", value1, value2, "checkTask");
            return (Criteria) this;
        }

        public Criteria andCheckIntroduceIsNull() {
            addCriterion("check_introduce is null");
            return (Criteria) this;
        }

        public Criteria andCheckIntroduceIsNotNull() {
            addCriterion("check_introduce is not null");
            return (Criteria) this;
        }

        public Criteria andCheckIntroduceEqualTo(Integer value) {
            addCriterion("check_introduce =", value, "checkIntroduce");
            return (Criteria) this;
        }

        public Criteria andCheckIntroduceNotEqualTo(Integer value) {
            addCriterion("check_introduce <>", value, "checkIntroduce");
            return (Criteria) this;
        }

        public Criteria andCheckIntroduceGreaterThan(Integer value) {
            addCriterion("check_introduce >", value, "checkIntroduce");
            return (Criteria) this;
        }

        public Criteria andCheckIntroduceGreaterThanOrEqualTo(Integer value) {
            addCriterion("check_introduce >=", value, "checkIntroduce");
            return (Criteria) this;
        }

        public Criteria andCheckIntroduceLessThan(Integer value) {
            addCriterion("check_introduce <", value, "checkIntroduce");
            return (Criteria) this;
        }

        public Criteria andCheckIntroduceLessThanOrEqualTo(Integer value) {
            addCriterion("check_introduce <=", value, "checkIntroduce");
            return (Criteria) this;
        }

        public Criteria andCheckIntroduceIn(List<Integer> values) {
            addCriterion("check_introduce in", values, "checkIntroduce");
            return (Criteria) this;
        }

        public Criteria andCheckIntroduceNotIn(List<Integer> values) {
            addCriterion("check_introduce not in", values, "checkIntroduce");
            return (Criteria) this;
        }

        public Criteria andCheckIntroduceBetween(Integer value1, Integer value2) {
            addCriterion("check_introduce between", value1, value2, "checkIntroduce");
            return (Criteria) this;
        }

        public Criteria andCheckIntroduceNotBetween(Integer value1, Integer value2) {
            addCriterion("check_introduce not between", value1, value2, "checkIntroduce");
            return (Criteria) this;
        }

        public Criteria andCheckEnrollIsNull() {
            addCriterion("check_enroll is null");
            return (Criteria) this;
        }

        public Criteria andCheckEnrollIsNotNull() {
            addCriterion("check_enroll is not null");
            return (Criteria) this;
        }

        public Criteria andCheckEnrollEqualTo(Integer value) {
            addCriterion("check_enroll =", value, "checkEnroll");
            return (Criteria) this;
        }

        public Criteria andCheckEnrollNotEqualTo(Integer value) {
            addCriterion("check_enroll <>", value, "checkEnroll");
            return (Criteria) this;
        }

        public Criteria andCheckEnrollGreaterThan(Integer value) {
            addCriterion("check_enroll >", value, "checkEnroll");
            return (Criteria) this;
        }

        public Criteria andCheckEnrollGreaterThanOrEqualTo(Integer value) {
            addCriterion("check_enroll >=", value, "checkEnroll");
            return (Criteria) this;
        }

        public Criteria andCheckEnrollLessThan(Integer value) {
            addCriterion("check_enroll <", value, "checkEnroll");
            return (Criteria) this;
        }

        public Criteria andCheckEnrollLessThanOrEqualTo(Integer value) {
            addCriterion("check_enroll <=", value, "checkEnroll");
            return (Criteria) this;
        }

        public Criteria andCheckEnrollIn(List<Integer> values) {
            addCriterion("check_enroll in", values, "checkEnroll");
            return (Criteria) this;
        }

        public Criteria andCheckEnrollNotIn(List<Integer> values) {
            addCriterion("check_enroll not in", values, "checkEnroll");
            return (Criteria) this;
        }

        public Criteria andCheckEnrollBetween(Integer value1, Integer value2) {
            addCriterion("check_enroll between", value1, value2, "checkEnroll");
            return (Criteria) this;
        }

        public Criteria andCheckEnrollNotBetween(Integer value1, Integer value2) {
            addCriterion("check_enroll not between", value1, value2, "checkEnroll");
            return (Criteria) this;
        }

        public Criteria andCheckEvaluateIsNull() {
            addCriterion("check_evaluate is null");
            return (Criteria) this;
        }

        public Criteria andCheckEvaluateIsNotNull() {
            addCriterion("check_evaluate is not null");
            return (Criteria) this;
        }

        public Criteria andCheckEvaluateEqualTo(Integer value) {
            addCriterion("check_evaluate =", value, "checkEvaluate");
            return (Criteria) this;
        }

        public Criteria andCheckEvaluateNotEqualTo(Integer value) {
            addCriterion("check_evaluate <>", value, "checkEvaluate");
            return (Criteria) this;
        }

        public Criteria andCheckEvaluateGreaterThan(Integer value) {
            addCriterion("check_evaluate >", value, "checkEvaluate");
            return (Criteria) this;
        }

        public Criteria andCheckEvaluateGreaterThanOrEqualTo(Integer value) {
            addCriterion("check_evaluate >=", value, "checkEvaluate");
            return (Criteria) this;
        }

        public Criteria andCheckEvaluateLessThan(Integer value) {
            addCriterion("check_evaluate <", value, "checkEvaluate");
            return (Criteria) this;
        }

        public Criteria andCheckEvaluateLessThanOrEqualTo(Integer value) {
            addCriterion("check_evaluate <=", value, "checkEvaluate");
            return (Criteria) this;
        }

        public Criteria andCheckEvaluateIn(List<Integer> values) {
            addCriterion("check_evaluate in", values, "checkEvaluate");
            return (Criteria) this;
        }

        public Criteria andCheckEvaluateNotIn(List<Integer> values) {
            addCriterion("check_evaluate not in", values, "checkEvaluate");
            return (Criteria) this;
        }

        public Criteria andCheckEvaluateBetween(Integer value1, Integer value2) {
            addCriterion("check_evaluate between", value1, value2, "checkEvaluate");
            return (Criteria) this;
        }

        public Criteria andCheckEvaluateNotBetween(Integer value1, Integer value2) {
            addCriterion("check_evaluate not between", value1, value2, "checkEvaluate");
            return (Criteria) this;
        }

        public Criteria andDelflagIsNull() {
            addCriterion("delflag is null");
            return (Criteria) this;
        }

        public Criteria andDelflagIsNotNull() {
            addCriterion("delflag is not null");
            return (Criteria) this;
        }

        public Criteria andDelflagEqualTo(Integer value) {
            addCriterion("delflag =", value, "delflag");
            return (Criteria) this;
        }

        public Criteria andDelflagNotEqualTo(Integer value) {
            addCriterion("delflag <>", value, "delflag");
            return (Criteria) this;
        }

        public Criteria andDelflagGreaterThan(Integer value) {
            addCriterion("delflag >", value, "delflag");
            return (Criteria) this;
        }

        public Criteria andDelflagGreaterThanOrEqualTo(Integer value) {
            addCriterion("delflag >=", value, "delflag");
            return (Criteria) this;
        }

        public Criteria andDelflagLessThan(Integer value) {
            addCriterion("delflag <", value, "delflag");
            return (Criteria) this;
        }

        public Criteria andDelflagLessThanOrEqualTo(Integer value) {
            addCriterion("delflag <=", value, "delflag");
            return (Criteria) this;
        }

        public Criteria andDelflagIn(List<Integer> values) {
            addCriterion("delflag in", values, "delflag");
            return (Criteria) this;
        }

        public Criteria andDelflagNotIn(List<Integer> values) {
            addCriterion("delflag not in", values, "delflag");
            return (Criteria) this;
        }

        public Criteria andDelflagBetween(Integer value1, Integer value2) {
            addCriterion("delflag between", value1, value2, "delflag");
            return (Criteria) this;
        }

        public Criteria andDelflagNotBetween(Integer value1, Integer value2) {
            addCriterion("delflag not between", value1, value2, "delflag");
            return (Criteria) this;
        }

        public Criteria andReasonIsNull() {
            addCriterion("reason is null");
            return (Criteria) this;
        }

        public Criteria andReasonIsNotNull() {
            addCriterion("reason is not null");
            return (Criteria) this;
        }

        public Criteria andReasonEqualTo(String value) {
            addCriterion("reason =", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotEqualTo(String value) {
            addCriterion("reason <>", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThan(String value) {
            addCriterion("reason >", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThanOrEqualTo(String value) {
            addCriterion("reason >=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThan(String value) {
            addCriterion("reason <", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThanOrEqualTo(String value) {
            addCriterion("reason <=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLike(String value) {
            addCriterion("reason like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotLike(String value) {
            addCriterion("reason not like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonIn(List<String> values) {
            addCriterion("reason in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotIn(List<String> values) {
            addCriterion("reason not in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonBetween(String value1, String value2) {
            addCriterion("reason between", value1, value2, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotBetween(String value1, String value2) {
            addCriterion("reason not between", value1, value2, "reason");
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