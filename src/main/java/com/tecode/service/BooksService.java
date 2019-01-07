package com.tecode.service;

import com.tecode.model.Books;

import java.util.List;

public interface BooksService {
    List<Books> selectByAuthor(String author);
    List<Books> selectByBookname(Integer pages,String name);
    Books selectByBookId(Integer id);
    //首页查询
    List<Books> homePageData(String type, int pages);
    //作者书籍查询
    List<Books> bookdetails(String author, String type);
    //作者或书籍查询
    List<Books> booksAll(String author,int pageNums);
    //根据类型查询书籍
    List<Books> selectByType(Integer type,int pageNum);
    //查询书籍点击量
    List<Books> selectByNumber(int pageNum);
    //查询书籍购买量
    List<Books> selectByAmount(int pageNum);
    //根据是否通过查询书籍
    List<Books> bookAll(int pages,String auditing);
    //查询通过或未通过书籍的数量
    Long countBooks(String auditing);
    //下架书籍
    void deletebooks(Books books);
    //根据书名查询数量
    Long countBooksname(String bookname);
}
