package com.tecode.service;

import com.tecode.model.History;

import java.util.List;

public interface BookShelfService {
    List<History> BookShelf(Integer uid);
    List<History> selectByCheck(Integer bid, Integer uid);
    void updateByBuy(History history);
    void addRecently(History history);
    List<History> ByHistory(Integer bookid,Integer uid);
    void updateById(History history);
}
