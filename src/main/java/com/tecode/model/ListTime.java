package com.tecode.model;

import java.util.Date;

public class ListTime {
    private Integer id;

    private Date weekstarttime;

    private Date weekendtime;

    private Date monthstarttime;

    private Date monthendtime;

    private Integer bid;

    private Integer monthcount;

    private Integer weekcount;

    private String stats;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getWeekstarttime() {
        return weekstarttime;
    }

    public void setWeekstarttime(Date weekstarttime) {
        this.weekstarttime = weekstarttime;
    }

    public Date getWeekendtime() {
        return weekendtime;
    }

    public void setWeekendtime(Date weekendtime) {
        this.weekendtime = weekendtime;
    }

    public Date getMonthstarttime() {
        return monthstarttime;
    }

    public void setMonthstarttime(Date monthstarttime) {
        this.monthstarttime = monthstarttime;
    }

    public Date getMonthendtime() {
        return monthendtime;
    }

    public void setMonthendtime(Date monthendtime) {
        this.monthendtime = monthendtime;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public Integer getMonthcount() {
        return monthcount;
    }

    public void setMonthcount(Integer monthcount) {
        this.monthcount = monthcount;
    }

    public Integer getWeekcount() {
        return weekcount;
    }

    public void setWeekcount(Integer weekcount) {
        this.weekcount = weekcount;
    }

    public String getStats() {
        return stats;
    }

    public void setStats(String stats) {
        this.stats = stats == null ? null : stats.trim();
    }
}