package com.tecode.service;

public interface SetionService {

    //作者添加章节信息
    boolean addChapter(int bid, String filename, String addr);
    //作者书籍章节删除
    boolean chapterRemove(int id);
}
