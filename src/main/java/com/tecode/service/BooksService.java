package com.tecode.service;

import com.tecode.model.Books;

import java.util.List;

public interface BooksService {
    List<Books> selectByAuthor(String author);
    List<Books> selectByBookname(String name);
    Books selectByBookId(Integer id);
    //首页查询
    List<Books> homePageData(String type, int pages);
    //作者书籍查询
    List<Books> bookdetails(String author, String type);
    //作者或书籍查询
    List<Books> booksAll(String author);
    //根据类型查询书籍
    List<Books> selectByType(Integer type,int pageNum);
    //查询书籍点击量
    List<Books> selectByNumber(int pageNum);
    //查询书籍购买量
    List<Books> selectByAmount(int pageNum);
}
