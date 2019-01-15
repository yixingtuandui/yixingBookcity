package com.tecode.service.serviceImpl;

import com.tecode.dao.NewsMapper;
import com.tecode.model.News;
import com.tecode.model.NewsExample;
import com.tecode.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsMapper newsMapper;
    @Override
    public void addNew(News news) {
        newsMapper.insert(news);
    }

    @Override
    public List<News> selectByUid(Integer uid) {
        NewsExample newsExample=new NewsExample();
        newsExample.createCriteria().andUidEqualTo(uid);
        return newsMapper.selectByExample(newsExample);
    }
}
