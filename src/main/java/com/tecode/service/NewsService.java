package com.tecode.service;

import com.tecode.model.News;

import java.util.List;

public interface NewsService {
    void addNew(News news);
    List<News> selectByUid(Integer uid);
    //我的消息
    List<News> iNews(int id);

    //消息删除
    boolean delateNews(int uid, int id);
}
