package com.tecode.service;

import com.tecode.model.News;

import java.util.List;

public interface NewsService {
    void addNew(News news);
    List<News> selectByUid(Integer uid);
}
