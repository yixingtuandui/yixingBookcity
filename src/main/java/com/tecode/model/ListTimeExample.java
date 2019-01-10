package com.tecode.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ListTimeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ListTimeExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andWeekstarttimeIsNull() {
            addCriterion("WeekStartTime is null");
            return (Criteria) this;
        }

        public Criteria andWeekstarttimeIsNotNull() {
            addCriterion("WeekStartTime is not null");
            return (Criteria) this;
        }

        public Criteria andWeekstarttimeEqualTo(Date value) {
            addCriterionForJDBCDate("WeekStartTime =", value, "weekstarttime");
            return (Criteria) this;
        }

        public Criteria andWeekstarttimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("WeekStartTime <>", value, "weekstarttime");
            return (Criteria) this;
        }

        public Criteria andWeekstarttimeGreaterThan(Date value) {
            addCriterionForJDBCDate("WeekStartTime >", value, "weekstarttime");
            return (Criteria) this;
        }

        public Criteria andWeekstarttimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("WeekStartTime >=", value, "weekstarttime");
            return (Criteria) this;
        }

        public Criteria andWeekstarttimeLessThan(Date value) {
            addCriterionForJDBCDate("WeekStartTime <", value, "weekstarttime");
            return (Criteria) this;
        }

        public Criteria andWeekstarttimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("WeekStartTime <=", value, "weekstarttime");
            return (Criteria) this;
        }

        public Criteria andWeekstarttimeIn(List<Date> values) {
            addCriterionForJDBCDate("WeekStartTime in", values, "weekstarttime");
            return (Criteria) this;
        }

        public Criteria andWeekstarttimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("WeekStartTime not in", values, "weekstarttime");
            return (Criteria) this;
        }

        public Criteria andWeekstarttimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("WeekStartTime between", value1, value2, "weekstarttime");
            return (Criteria) this;
        }

        public Criteria andWeekstarttimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("WeekStartTime not between", value1, value2, "weekstarttime");
            return (Criteria) this;
        }

        public Criteria andWeekendtimeIsNull() {
            addCriterion("WeekEndTime is null");
            return (Criteria) this;
        }

        public Criteria andWeekendtimeIsNotNull() {
            addCriterion("WeekEndTime is not null");
            return (Criteria) this;
        }

        public Criteria andWeekendtimeEqualTo(Date value) {
            addCriterionForJDBCDate("WeekEndTime =", value, "weekendtime");
            return (Criteria) this;
        }

        public Criteria andWeekendtimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("WeekEndTime <>", value, "weekendtime");
            return (Criteria) this;
        }

        public Criteria andWeekendtimeGreaterThan(Date value) {
            addCriterionForJDBCDate("WeekEndTime >", value, "weekendtime");
            return (Criteria) this;
        }

        public Criteria andWeekendtimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("WeekEndTime >=", value, "weekendtime");
            return (Criteria) this;
        }

        public Criteria andWeekendtimeLessThan(Date value) {
            addCriterionForJDBCDate("WeekEndTime <", value, "weekendtime");
            return (Criteria) this;
        }

        public Criteria andWeekendtimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("WeekEndTime <=", value, "weekendtime");
            return (Criteria) this;
        }

        public Criteria andWeekendtimeIn(List<Date> values) {
            addCriterionForJDBCDate("WeekEndTime in", values, "weekendtime");
            return (Criteria) this;
        }

        public Criteria andWeekendtimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("WeekEndTime not in", values, "weekendtime");
            return (Criteria) this;
        }

        public Criteria andWeekendtimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("WeekEndTime between", value1, value2, "weekendtime");
            return (Criteria) this;
        }

        public Criteria andWeekendtimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("WeekEndTime not between", value1, value2, "weekendtime");
            return (Criteria) this;
        }

        public Criteria andMonthstarttimeIsNull() {
            addCriterion("MonthStartTime is null");
            return (Criteria) this;
        }

        public Criteria andMonthstarttimeIsNotNull() {
            addCriterion("MonthStartTime is not null");
            return (Criteria) this;
        }

        public Criteria andMonthstarttimeEqualTo(Date value) {
            addCriterionForJDBCDate("MonthStartTime =", value, "monthstarttime");
            return (Criteria) this;
        }

        public Criteria andMonthstarttimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("MonthStartTime <>", value, "monthstarttime");
            return (Criteria) this;
        }

        public Criteria andMonthstarttimeGreaterThan(Date value) {
            addCriterionForJDBCDate("MonthStartTime >", value, "monthstarttime");
            return (Criteria) this;
        }

        public Criteria andMonthstarttimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("MonthStartTime >=", value, "monthstarttime");
            return (Criteria) this;
        }

        public Criteria andMonthstarttimeLessThan(Date value) {
            addCriterionForJDBCDate("MonthStartTime <", value, "monthstarttime");
            return (Criteria) this;
        }

        public Criteria andMonthstarttimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("MonthStartTime <=", value, "monthstarttime");
            return (Criteria) this;
        }

        public Criteria andMonthstarttimeIn(List<Date> values) {
            addCriterionForJDBCDate("MonthStartTime in", values, "monthstarttime");
            return (Criteria) this;
        }

        public Criteria andMonthstarttimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("MonthStartTime not in", values, "monthstarttime");
            return (Criteria) this;
        }

        public Criteria andMonthstarttimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("MonthStartTime between", value1, value2, "monthstarttime");
            return (Criteria) this;
        }

        public Criteria andMonthstarttimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("MonthStartTime not between", value1, value2, "monthstarttime");
            return (Criteria) this;
        }

        public Criteria andMonthendtimeIsNull() {
            addCriterion("MonthEndTime is null");
            return (Criteria) this;
        }

        public Criteria andMonthendtimeIsNotNull() {
            addCriterion("MonthEndTime is not null");
            return (Criteria) this;
        }

        public Criteria andMonthendtimeEqualTo(Date value) {
            addCriterionForJDBCDate("MonthEndTime =", value, "monthendtime");
            return (Criteria) this;
        }

        public Criteria andMonthendtimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("MonthEndTime <>", value, "monthendtime");
            return (Criteria) this;
        }

        public Criteria andMonthendtimeGreaterThan(Date value) {
            addCriterionForJDBCDate("MonthEndTime >", value, "monthendtime");
            return (Criteria) this;
        }

        public Criteria andMonthendtimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("MonthEndTime >=", value, "monthendtime");
            return (Criteria) this;
        }

        public Criteria andMonthendtimeLessThan(Date value) {
            addCriterionForJDBCDate("MonthEndTime <", value, "monthendtime");
            return (Criteria) this;
        }

        public Criteria andMonthendtimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("MonthEndTime <=", value, "monthendtime");
            return (Criteria) this;
        }

        public Criteria andMonthendtimeIn(List<Date> values) {
            addCriterionForJDBCDate("MonthEndTime in", values, "monthendtime");
            return (Criteria) this;
        }

        public Criteria andMonthendtimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("MonthEndTime not in", values, "monthendtime");
            return (Criteria) this;
        }

        public Criteria andMonthendtimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("MonthEndTime between", value1, value2, "monthendtime");
            return (Criteria) this;
        }

        public Criteria andMonthendtimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("MonthEndTime not between", value1, value2, "monthendtime");
            return (Criteria) this;
        }

        public Criteria andBidIsNull() {
            addCriterion("bid is null");
            return (Criteria) this;
        }

        public Criteria andBidIsNotNull() {
            addCriterion("bid is not null");
            return (Criteria) this;
        }

        public Criteria andBidEqualTo(Integer value) {
            addCriterion("bid =", value, "bid");
            return (Criteria) this;
        }

        public Criteria andBidNotEqualTo(Integer value) {
            addCriterion("bid <>", value, "bid");
            return (Criteria) this;
        }

        public Criteria andBidGreaterThan(Integer value) {
            addCriterion("bid >", value, "bid");
            return (Criteria) this;
        }

        public Criteria andBidGreaterThanOrEqualTo(Integer value) {
            addCriterion("bid >=", value, "bid");
            return (Criteria) this;
        }

        public Criteria andBidLessThan(Integer value) {
            addCriterion("bid <", value, "bid");
            return (Criteria) this;
        }

        public Criteria andBidLessThanOrEqualTo(Integer value) {
            addCriterion("bid <=", value, "bid");
            return (Criteria) this;
        }

        public Criteria andBidIn(List<Integer> values) {
            addCriterion("bid in", values, "bid");
            return (Criteria) this;
        }

        public Criteria andBidNotIn(List<Integer> values) {
            addCriterion("bid not in", values, "bid");
            return (Criteria) this;
        }

        public Criteria andBidBetween(Integer value1, Integer value2) {
            addCriterion("bid between", value1, value2, "bid");
            return (Criteria) this;
        }

        public Criteria andBidNotBetween(Integer value1, Integer value2) {
            addCriterion("bid not between", value1, value2, "bid");
            return (Criteria) this;
        }

        public Criteria andCountIsNull() {
            addCriterion("count is null");
            return (Criteria) this;
        }

        public Criteria andCountIsNotNull() {
            addCriterion("count is not null");
            return (Criteria) this;
        }

        public Criteria andCountEqualTo(Integer value) {
            addCriterion("count =", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountNotEqualTo(Integer value) {
            addCriterion("count <>", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountGreaterThan(Integer value) {
            addCriterion("count >", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("count >=", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountLessThan(Integer value) {
            addCriterion("count <", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountLessThanOrEqualTo(Integer value) {
            addCriterion("count <=", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountIn(List<Integer> values) {
            addCriterion("count in", values, "count");
            return (Criteria) this;
        }

        public Criteria andCountNotIn(List<Integer> values) {
            addCriterion("count not in", values, "count");
            return (Criteria) this;
        }

        public Criteria andCountBetween(Integer value1, Integer value2) {
            addCriterion("count between", value1, value2, "count");
            return (Criteria) this;
        }

        public Criteria andCountNotBetween(Integer value1, Integer value2) {
            addCriterion("count not between", value1, value2, "count");
            return (Criteria) this;
        }

        public Criteria andStatsIsNull() {
            addCriterion("stats is null");
            return (Criteria) this;
        }

        public Criteria andStatsIsNotNull() {
            addCriterion("stats is not null");
            return (Criteria) this;
        }

        public Criteria andStatsEqualTo(String value) {
            addCriterion("stats =", value, "stats");
            return (Criteria) this;
        }

        public Criteria andStatsNotEqualTo(String value) {
            addCriterion("stats <>", value, "stats");
            return (Criteria) this;
        }

        public Criteria andStatsGreaterThan(String value) {
            addCriterion("stats >", value, "stats");
            return (Criteria) this;
        }

        public Criteria andStatsGreaterThanOrEqualTo(String value) {
            addCriterion("stats >=", value, "stats");
            return (Criteria) this;
        }

        public Criteria andStatsLessThan(String value) {
            addCriterion("stats <", value, "stats");
            return (Criteria) this;
        }

        public Criteria andStatsLessThanOrEqualTo(String value) {
            addCriterion("stats <=", value, "stats");
            return (Criteria) this;
        }

        public Criteria andStatsLike(String value) {
            addCriterion("stats like", value, "stats");
            return (Criteria) this;
        }

        public Criteria andStatsNotLike(String value) {
            addCriterion("stats not like", value, "stats");
            return (Criteria) this;
        }

        public Criteria andStatsIn(List<String> values) {
            addCriterion("stats in", values, "stats");
            return (Criteria) this;
        }

        public Criteria andStatsNotIn(List<String> values) {
            addCriterion("stats not in", values, "stats");
            return (Criteria) this;
        }

        public Criteria andStatsBetween(String value1, String value2) {
            addCriterion("stats between", value1, value2, "stats");
            return (Criteria) this;
        }

        public Criteria andStatsNotBetween(String value1, String value2) {
            addCriterion("stats not between", value1, value2, "stats");
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