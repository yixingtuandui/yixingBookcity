package com.tecode.model;

import java.util.Date;

public class News {
    private Integer id;

    private String booknews;

    private Date masgtime;

    private Integer uid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBooknews() {
        return booknews;
    }

    public void setBooknews(String booknews) {
        this.booknews = booknews == null ? null : booknews.trim();
    }

    public Date getMasgtime() {
        return masgtime;
    }

    public void setMasgtime(Date masgtime) {
        this.masgtime = masgtime;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
}