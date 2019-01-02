package com.tecode.model;

import java.util.Date;

public class History {
    private Integer id;

    private Integer bookid;

    private String buy;

    private String section;

    private Integer uid;

    private Boolean addbookshelf;

    private Date readingTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }

    public String getBuy() {
        return buy;
    }

    public void setBuy(String buy) {
        this.buy = buy == null ? null : buy.trim();
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section == null ? null : section.trim();
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Boolean getAddbookshelf() {
        return addbookshelf;
    }

    public void setAddbookshelf(Boolean addbookshelf) {
        this.addbookshelf = addbookshelf;
    }

    public Date getReadingTime() {
        return readingTime;
    }

    public void setReadingTime(Date readingTime) {
        this.readingTime = readingTime;
    }
}