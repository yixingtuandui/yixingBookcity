package com.tecode.service.serviceImpl;

import com.tecode.dao.HistoryMapper;
import com.tecode.model.History;
import com.tecode.model.HistoryExample;
import com.tecode.service.BookShelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BookShelfImpl implements BookShelfService {
    @Autowired
    private HistoryMapper historyMapper;
    private HistoryExample historyExample=new HistoryExample();
    //根据用户id查询记录
    @Override
    public List<History> BookShelf(Integer uid) {
        historyExample.createCriteria().andUidEqualTo(uid);
        return historyMapper.selectByExample(historyExample);
    }

    @Override
    public List<History> Recently(Integer uid,Date... dates) {
        historyExample.createCriteria().andUidEqualTo(uid).andReadingTimeBetween(dates[0],dates[1]);
        return historyMapper.selectByExample(historyExample);
    }


}
