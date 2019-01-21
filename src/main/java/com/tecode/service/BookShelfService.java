package com.tecode.service;

import com.tecode.model.Books;
import com.tecode.model.History;

import java.util.Date;
import java.util.List;

public interface BookShelfService {
    List<History> BookShelf(Integer uid);
    List<History> selectByCheck(Integer bid,Integer uid,String buy);
    void addRecently(History history);
    List<History> ByHistory(Integer bookid,Integer uid);
    void updateById(History history);
    List<History> selectByBuy(Integer uid,String buy);
    Books selectByBookId(Integer id); //ID查Books
    void deleteHistory(Integer bid);//删除History记录
}
