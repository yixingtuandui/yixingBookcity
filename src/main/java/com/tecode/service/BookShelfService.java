package com.tecode.service;

import com.tecode.model.History;

import java.util.Date;
import java.util.List;

public interface BookShelfService {
    List<History> BookShelf(Integer uid);
    Object Recently(Integer uid,Date...dates);
}
