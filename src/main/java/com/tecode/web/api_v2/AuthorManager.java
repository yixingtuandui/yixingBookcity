package com.tecode.web.api_v2;

import com.tecode.model.Books;
import com.tecode.model.SetionTable;
import com.tecode.service.BooksService;
import com.tecode.service.SetionService;
import com.tecode.service.serviceImpl.SetionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AuthorManager {
    @Autowired
    private BooksService booksService;
    @Autowired
    SetionService setionService;
    //首页查询
    @RequestMapping(value = "/home_page", method = RequestMethod.GET)
    @ResponseBody
    public List<Books> homePageData(String type, int pages) {
        return booksService.homePageData(type, pages);
    }

    //作者书籍查询
    @RequestMapping(value = "/bookdetails", method = RequestMethod.GET)
    @ResponseBody
    public List bookdetails(String author, String type){
        return booksService.bookdetails(author, type);
    }

    //作者书籍修改
    @RequestMapping(value = "/author_update", method = RequestMethod.GET)
    @ResponseBody
    public boolean authorUpdate(int id, double price, String status) {
        Books books=booksService.selectByBookId(id);
        books.setPrice(price);
        books.setStatus(status);
        booksService.authorUpdate(books);
        return true;
    }

    //书籍章节更新
    @RequestMapping(value = "/bookUpdate", method = RequestMethod.POST)
    @ResponseBody
    public boolean bookUpdate(String address, int bid, int chapter, String title, String content) {
        SetionTable setionTable=new SetionTable();
        setionTable.setBid(bid);
        setionTable.setChapter("第"+chapter+"章 "+title);
        setionTable.setContent(content);
        setionService.updateSetion(setionTable);
        return true;
    }

    //书籍最新章节
    @RequestMapping(value = "/chapter")
    @ResponseBody
    public List chapter(int bid) {
        //booksService.chapter(bid)
        return null;
    }

    //书籍类型
    @RequestMapping(value = "/booktype")
    @ResponseBody
    public List bookType(){
        //booksService.bookType()
        return null;
    }
}
