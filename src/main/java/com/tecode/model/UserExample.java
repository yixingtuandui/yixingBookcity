package com.tecode.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserExample() {
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andAvatorIsNull() {
            addCriterion("avator is null");
            return (Criteria) this;
        }

        public Criteria andAvatorIsNotNull() {
            addCriterion("avator is not null");
            return (Criteria) this;
        }

        public Criteria andAvatorEqualTo(String value) {
            addCriterion("avator =", value, "avator");
            return (Criteria) this;
        }

        public Criteria andAvatorNotEqualTo(String value) {
            addCriterion("avator <>", value, "avator");
            return (Criteria) this;
        }

        public Criteria andAvatorGreaterThan(String value) {
            addCriterion("avator >", value, "avator");
            return (Criteria) this;
        }

        public Criteria andAvatorGreaterThanOrEqualTo(String value) {
            addCriterion("avator >=", value, "avator");
            return (Criteria) this;
        }

        public Criteria andAvatorLessThan(String value) {
            addCriterion("avator <", value, "avator");
            return (Criteria) this;
        }

        public Criteria andAvatorLessThanOrEqualTo(String value) {
            addCriterion("avator <=", value, "avator");
            return (Criteria) this;
        }

        public Criteria andAvatorLike(String value) {
            addCriterion("avator like", value, "avator");
            return (Criteria) this;
        }

        public Criteria andAvatorNotLike(String value) {
            addCriterion("avator not like", value, "avator");
            return (Criteria) this;
        }

        public Criteria andAvatorIn(List<String> values) {
            addCriterion("avator in", values, "avator");
            return (Criteria) this;
        }

        public Criteria andAvatorNotIn(List<String> values) {
            addCriterion("avator not in", values, "avator");
            return (Criteria) this;
        }

        public Criteria andAvatorBetween(String value1, String value2) {
            addCriterion("avator between", value1, value2, "avator");
            return (Criteria) this;
        }

        public Criteria andAvatorNotBetween(String value1, String value2) {
            addCriterion("avator not between", value1, value2, "avator");
            return (Criteria) this;
        }

        public Criteria andDateRegIsNull() {
            addCriterion("date_reg is null");
            return (Criteria) this;
        }

        public Criteria andDateRegIsNotNull() {
            addCriterion("date_reg is not null");
            return (Criteria) this;
        }

        public Criteria andDateRegEqualTo(Date value) {
            addCriterion("date_reg =", value, "dateReg");
            return (Criteria) this;
        }

        public Criteria andDateRegNotEqualTo(Date value) {
            addCriterion("date_reg <>", value, "dateReg");
            return (Criteria) this;
        }

        public Criteria andDateRegGreaterThan(Date value) {
            addCriterion("date_reg >", value, "dateReg");
            return (Criteria) this;
        }

        public Criteria andDateRegGreaterThanOrEqualTo(Date value) {
            addCriterion("date_reg >=", value, "dateReg");
            return (Criteria) this;
        }

        public Criteria andDateRegLessThan(Date value) {
            addCriterion("date_reg <", value, "dateReg");
            return (Criteria) this;
        }

        public Criteria andDateRegLessThanOrEqualTo(Date value) {
            addCriterion("date_reg <=", value, "dateReg");
            return (Criteria) this;
        }

        public Criteria andDateRegIn(List<Date> values) {
            addCriterion("date_reg in", values, "dateReg");
            return (Criteria) this;
        }

        public Criteria andDateRegNotIn(List<Date> values) {
            addCriterion("date_reg not in", values, "dateReg");
            return (Criteria) this;
        }

        public Criteria andDateRegBetween(Date value1, Date value2) {
            addCriterion("date_reg between", value1, value2, "dateReg");
            return (Criteria) this;
        }

        public Criteria andDateRegNotBetween(Date value1, Date value2) {
            addCriterion("date_reg not between", value1, value2, "dateReg");
            return (Criteria) this;
        }

        public Criteria andDateSignIsNull() {
            addCriterion("date_sign is null");
            return (Criteria) this;
        }

        public Criteria andDateSignIsNotNull() {
            addCriterion("date_sign is not null");
            return (Criteria) this;
        }

        public Criteria andDateSignEqualTo(Date value) {
            addCriterion("date_sign =", value, "dateSign");
            return (Criteria) this;
        }

        public Criteria andDateSignNotEqualTo(Date value) {
            addCriterion("date_sign <>", value, "dateSign");
            return (Criteria) this;
        }

        public Criteria andDateSignGreaterThan(Date value) {
            addCriterion("date_sign >", value, "dateSign");
            return (Criteria) this;
        }

        public Criteria andDateSignGreaterThanOrEqualTo(Date value) {
            addCriterion("date_sign >=", value, "dateSign");
            return (Criteria) this;
        }

        public Criteria andDateSignLessThan(Date value) {
            addCriterion("date_sign <", value, "dateSign");
            return (Criteria) this;
        }

        public Criteria andDateSignLessThanOrEqualTo(Date value) {
            addCriterion("date_sign <=", value, "dateSign");
            return (Criteria) this;
        }

        public Criteria andDateSignIn(List<Date> values) {
            addCriterion("date_sign in", values, "dateSign");
            return (Criteria) this;
        }

        public Criteria andDateSignNotIn(List<Date> values) {
            addCriterion("date_sign not in", values, "dateSign");
            return (Criteria) this;
        }

        public Criteria andDateSignBetween(Date value1, Date value2) {
            addCriterion("date_sign between", value1, value2, "dateSign");
            return (Criteria) this;
        }

        public Criteria andDateSignNotBetween(Date value1, Date value2) {
            addCriterion("date_sign not between", value1, value2, "dateSign");
            return (Criteria) this;
        }

        public Criteria andDayIsNull() {
            addCriterion("day is null");
            return (Criteria) this;
        }

        public Criteria andDayIsNotNull() {
            addCriterion("day is not null");
            return (Criteria) this;
        }

        public Criteria andDayEqualTo(Integer value) {
            addCriterion("day =", value, "day");
            return (Criteria) this;
        }

        public Criteria andDayNotEqualTo(Integer value) {
            addCriterion("day <>", value, "day");
            return (Criteria) this;
        }

        public Criteria andDayGreaterThan(Integer value) {
            addCriterion("day >", value, "day");
            return (Criteria) this;
        }

        public Criteria andDayGreaterThanOrEqualTo(Integer value) {
            addCriterion("day >=", value, "day");
            return (Criteria) this;
        }

        public Criteria andDayLessThan(Integer value) {
            addCriterion("day <", value, "day");
            return (Criteria) this;
        }

        public Criteria andDayLessThanOrEqualTo(Integer value) {
            addCriterion("day <=", value, "day");
            return (Criteria) this;
        }

        public Criteria andDayIn(List<Integer> values) {
            addCriterion("day in", values, "day");
            return (Criteria) this;
        }

        public Criteria andDayNotIn(List<Integer> values) {
            addCriterion("day not in", values, "day");
            return (Criteria) this;
        }

        public Criteria andDayBetween(Integer value1, Integer value2) {
            addCriterion("day between", value1, value2, "day");
            return (Criteria) this;
        }

        public Criteria andDayNotBetween(Integer value1, Integer value2) {
            addCriterion("day not between", value1, value2, "day");
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

        public Criteria andMoneyEqualTo(Double value) {
            addCriterion("money =", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotEqualTo(Double value) {
            addCriterion("money <>", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThan(Double value) {
            addCriterion("money >", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThanOrEqualTo(Double value) {
            addCriterion("money >=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThan(Double value) {
            addCriterion("money <", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThanOrEqualTo(Double value) {
            addCriterion("money <=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyIn(List<Double> values) {
            addCriterion("money in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotIn(List<Double> values) {
            addCriterion("money not in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyBetween(Double value1, Double value2) {
            addCriterion("money between", value1, value2, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotBetween(Double value1, Double value2) {
            addCriterion("money not between", value1, value2, "money");
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

        public Criteria andPenNameIsNull() {
            addCriterion("pen_name is null");
            return (Criteria) this;
        }

        public Criteria andPenNameIsNotNull() {
            addCriterion("pen_name is not null");
            return (Criteria) this;
        }

        public Criteria andPenNameEqualTo(String value) {
            addCriterion("pen_name =", value, "penName");
            return (Criteria) this;
        }

        public Criteria andPenNameNotEqualTo(String value) {
            addCriterion("pen_name <>", value, "penName");
            return (Criteria) this;
        }

        public Criteria andPenNameGreaterThan(String value) {
            addCriterion("pen_name >", value, "penName");
            return (Criteria) this;
        }

        public Criteria andPenNameGreaterThanOrEqualTo(String value) {
            addCriterion("pen_name >=", value, "penName");
            return (Criteria) this;
        }

        public Criteria andPenNameLessThan(String value) {
            addCriterion("pen_name <", value, "penName");
            return (Criteria) this;
        }

        public Criteria andPenNameLessThanOrEqualTo(String value) {
            addCriterion("pen_name <=", value, "penName");
            return (Criteria) this;
        }

        public Criteria andPenNameLike(String value) {
            addCriterion("pen_name like", value, "penName");
            return (Criteria) this;
        }

        public Criteria andPenNameNotLike(String value) {
            addCriterion("pen_name not like", value, "penName");
            return (Criteria) this;
        }

        public Criteria andPenNameIn(List<String> values) {
            addCriterion("pen_name in", values, "penName");
            return (Criteria) this;
        }

        public Criteria andPenNameNotIn(List<String> values) {
            addCriterion("pen_name not in", values, "penName");
            return (Criteria) this;
        }

        public Criteria andPenNameBetween(String value1, String value2) {
            addCriterion("pen_name between", value1, value2, "penName");
            return (Criteria) this;
        }

        public Criteria andPenNameNotBetween(String value1, String value2) {
            addCriterion("pen_name not between", value1, value2, "penName");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andRoleIsNull() {
            addCriterion("role is null");
            return (Criteria) this;
        }

        public Criteria andRoleIsNotNull() {
            addCriterion("role is not null");
            return (Criteria) this;
        }

        public Criteria andRoleEqualTo(String value) {
            addCriterion("role =", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleNotEqualTo(String value) {
            addCriterion("role <>", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleGreaterThan(String value) {
            addCriterion("role >", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleGreaterThanOrEqualTo(String value) {
            addCriterion("role >=", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleLessThan(String value) {
            addCriterion("role <", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleLessThanOrEqualTo(String value) {
            addCriterion("role <=", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleLike(String value) {
            addCriterion("role like", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleNotLike(String value) {
            addCriterion("role not like", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleIn(List<String> values) {
            addCriterion("role in", values, "role");
            return (Criteria) this;
        }

        public Criteria andRoleNotIn(List<String> values) {
            addCriterion("role not in", values, "role");
            return (Criteria) this;
        }

        public Criteria andRoleBetween(String value1, String value2) {
            addCriterion("role between", value1, value2, "role");
            return (Criteria) this;
        }

        public Criteria andRoleNotBetween(String value1, String value2) {
            addCriterion("role not between", value1, value2, "role");
            return (Criteria) this;
        }

        public Criteria andSexIsNull() {
            addCriterion("sex is null");
            return (Criteria) this;
        }

        public Criteria andSexIsNotNull() {
            addCriterion("sex is not null");
            return (Criteria) this;
        }

        public Criteria andSexEqualTo(String value) {
            addCriterion("sex =", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotEqualTo(String value) {
            addCriterion("sex <>", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThan(String value) {
            addCriterion("sex >", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThanOrEqualTo(String value) {
            addCriterion("sex >=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThan(String value) {
            addCriterion("sex <", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThanOrEqualTo(String value) {
            addCriterion("sex <=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLike(String value) {
            addCriterion("sex like", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotLike(String value) {
            addCriterion("sex not like", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexIn(List<String> values) {
            addCriterion("sex in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotIn(List<String> values) {
            addCriterion("sex not in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexBetween(String value1, String value2) {
            addCriterion("sex between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotBetween(String value1, String value2) {
            addCriterion("sex not between", value1, value2, "sex");
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

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andIdayIsNull() {
            addCriterion("iday is null");
            return (Criteria) this;
        }

        public Criteria andIdayIsNotNull() {
            addCriterion("iday is not null");
            return (Criteria) this;
        }

        public Criteria andIdayEqualTo(Integer value) {
            addCriterion("iday =", value, "iday");
            return (Criteria) this;
        }

        public Criteria andIdayNotEqualTo(Integer value) {
            addCriterion("iday <>", value, "iday");
            return (Criteria) this;
        }

        public Criteria andIdayGreaterThan(Integer value) {
            addCriterion("iday >", value, "iday");
            return (Criteria) this;
        }

        public Criteria andIdayGreaterThanOrEqualTo(Integer value) {
            addCriterion("iday >=", value, "iday");
            return (Criteria) this;
        }

        public Criteria andIdayLessThan(Integer value) {
            addCriterion("iday <", value, "iday");
            return (Criteria) this;
        }

        public Criteria andIdayLessThanOrEqualTo(Integer value) {
            addCriterion("iday <=", value, "iday");
            return (Criteria) this;
        }

        public Criteria andIdayIn(List<Integer> values) {
            addCriterion("iday in", values, "iday");
            return (Criteria) this;
        }

        public Criteria andIdayNotIn(List<Integer> values) {
            addCriterion("iday not in", values, "iday");
            return (Criteria) this;
        }

        public Criteria andIdayBetween(Integer value1, Integer value2) {
            addCriterion("iday between", value1, value2, "iday");
            return (Criteria) this;
        }

        public Criteria andIdayNotBetween(Integer value1, Integer value2) {
            addCriterion("iday not between", value1, value2, "iday");
            return (Criteria) this;
        }

        public Criteria andRepIsNull() {
            addCriterion("rep is null");
            return (Criteria) this;
        }

        public Criteria andRepIsNotNull() {
            addCriterion("rep is not null");
            return (Criteria) this;
        }

        public Criteria andRepEqualTo(Integer value) {
            addCriterion("rep =", value, "rep");
            return (Criteria) this;
        }

        public Criteria andRepNotEqualTo(Integer value) {
            addCriterion("rep <>", value, "rep");
            return (Criteria) this;
        }

        public Criteria andRepGreaterThan(Integer value) {
            addCriterion("rep >", value, "rep");
            return (Criteria) this;
        }

        public Criteria andRepGreaterThanOrEqualTo(Integer value) {
            addCriterion("rep >=", value, "rep");
            return (Criteria) this;
        }

        public Criteria andRepLessThan(Integer value) {
            addCriterion("rep <", value, "rep");
            return (Criteria) this;
        }

        public Criteria andRepLessThanOrEqualTo(Integer value) {
            addCriterion("rep <=", value, "rep");
            return (Criteria) this;
        }

        public Criteria andRepIn(List<Integer> values) {
            addCriterion("rep in", values, "rep");
            return (Criteria) this;
        }

        public Criteria andRepNotIn(List<Integer> values) {
            addCriterion("rep not in", values, "rep");
            return (Criteria) this;
        }

        public Criteria andRepBetween(Integer value1, Integer value2) {
            addCriterion("rep between", value1, value2, "rep");
            return (Criteria) this;
        }

        public Criteria andRepNotBetween(Integer value1, Integer value2) {
            addCriterion("rep not between", value1, value2, "rep");
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