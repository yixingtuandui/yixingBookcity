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
    @Override//我的消息
    public List<News> iNews(int uid) {
        NewsExample newsExample = new NewsExample();
        newsExample.createCriteria().andUidEqualTo(uid);
        return newsMapper.selectByExample(newsExample);
    }

    @Override//删除消息
    public boolean delateNews(int uid, int id) {
        if (id == -1) {
            NewsExample newsExample = new NewsExample();
            newsExample.createCriteria().andUidEqualTo(uid);
            return newsMapper.deleteByExample(newsExample) > 0;
        }
        return newsMapper.deleteByPrimaryKey(id) > 0;
    }
}
