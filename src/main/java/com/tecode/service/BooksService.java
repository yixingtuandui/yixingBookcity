package com.tecode.service;

import com.tecode.model.Books;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface BooksService {

    List<Books> selectByAuthor(String author);
    //根据书名获取xinx
    List<Books> selectByBookname(Integer pages, String name);
    //根据书名获取信息并排序
    List<Books> selectByBooknameOrder(Integer pages, String name,String comds);
    Books selectByBookId(Integer id);

    //首页查询
    List<Books> homePageData(String type, int pages, int pageSize);

    //作者书籍查询
    List<Books> bookdetails(String author, String type);

    //作者或书籍查询
    List<Books> booksAll(String author, int pageNums);

    //根据类型查询书籍
    List<Books> selectByType(Integer type, int pageNum);

    //查询书籍点击量 总榜
    List<Books> selectByNumber(int pageNum);

    //查询书籍购买量 总榜
    List<Books> selectByAmount(int pageNum);

    //查询书籍点击量 月榜
    List<Books> selectByMonthNumber(int pageNum);

    //查询书籍购买量 月榜
    List<Books> selectByMonthAmount(int pageNum);

    //查询书籍点击量 周榜
    List<Books> selectByWeekNumber(int pageNum);

    //查询书籍购买量 周榜
    List<Books> selectByWeekAmount(int pageNum);

    //增加畅销榜
    void addCX(int id);

    //根据是否通过查询书籍
    List<Books> bookAll(int pages, String auditing);

    //查询通过或未通过书籍的数量
    Long countBooks(String auditing);

    //下架书籍
    void deletebooks(Books books);

    //成为作者
    void authorUpdate(Books books);

    //根据书名查询数量
    Long countBooksname(String bookname);

    //作者修改书籍信息
    boolean bookUpdate(int id, double price, String status);

    //书籍章节
    int chapter(int bid);

    //作者书籍章节更新
    boolean chapterUpdate(String address, int bid, int chapter, String title, String content);

    //作者添加书籍
    List<Books> addBook(String author, String bookname, int lxint, String lb, String jj, String title, String content);

    //上传图片
    boolean uploadImg(HttpServletRequest request, HttpServletResponse response);
}
