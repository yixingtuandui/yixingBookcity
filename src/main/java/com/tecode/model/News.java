package com.tecode.model;

public class News {
    private Integer id;

    private String booknews;

    private String membernews;

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

    public String getMembernews() {
        return membernews;
    }

    public void setMembernews(String membernews) {
        this.membernews = membernews == null ? null : membernews.trim();
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
}