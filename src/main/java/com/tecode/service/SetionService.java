package com.tecode.service;

import com.tecode.model.SetionTable;

public interface SetionService {
    //作者添加章节信息
    boolean addChapter(int bid, String filename, String addr);
}
