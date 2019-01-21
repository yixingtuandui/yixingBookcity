package com.tecode.service.serviceImpl;

import com.tecode.dao.BooksMapper;
import com.tecode.dao.HistoryMapper;
import com.tecode.model.Books;
import com.tecode.model.History;
import com.tecode.model.HistoryExample;
import com.tecode.service.BookShelfService;
import com.tecode.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookShelfImpl implements BookShelfService {
    @Autowired
    private HistoryMapper historyMapper;
    @Autowired
    private BooksMapper booksMapper;
    //根据用户id查询记录
    @Override
    public List<History> BookShelf(Integer uid) {
        HistoryExample historyExample=new HistoryExample();
        historyExample.createCriteria().andUidEqualTo(uid);
        return historyMapper.selectByExample(historyExample);
    }

    //新增阅读记录
    @Override
    public void addRecently(History history) {
        historyMapper.insert(history);
    }

    @Override
    public List<History> ByHistory(Integer bookid,Integer uid) {
        HistoryExample historyExample=new HistoryExample();
        historyExample.createCriteria().andBookidEqualTo(bookid).andUidEqualTo(uid);
        return historyMapper.selectByExample(historyExample);
    }
    //修改历史记录
    @Override
    public void updateById(History history) {
        historyMapper.updateByPrimaryKey(history);
    }
    //根据uid和是否购买查询
    @Override
    public List<History> selectByBuy(Integer uid, String buy) {
        HistoryExample historyExample=new HistoryExample();
        historyExample.createCriteria().andUidEqualTo(uid).andBuyEqualTo(buy);
        historyExample.setOrderByClause("Reading_time desc");
        return historyMapper.selectByExample(historyExample);
    }
    //ID查书
    @Override
    public Books selectByBookId(Integer id) {
        return booksMapper.selectByPrimaryKey(id);
    }
    //删除History记录
    @Override
    public void deleteHistory(Integer bid) {
        HistoryExample historyExample=new HistoryExample();
        historyExample.createCriteria().andBookidEqualTo(bid);
        historyMapper.deleteByExample(historyExample);
    }

    //通过书籍id和用户id查询是否购买
    @Override
    public List<History> selectByCheck(Integer bid, Integer uid,String buy) {
        HistoryExample historyExample=new HistoryExample();
        historyExample.createCriteria().andUidEqualTo(uid).andBookidEqualTo(bid).andBuyEqualTo(buy);
        return historyMapper.selectByExample(historyExample);
    }
}
