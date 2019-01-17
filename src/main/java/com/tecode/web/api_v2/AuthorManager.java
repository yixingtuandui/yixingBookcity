package com.tecode.web.api_v2;

import com.alibaba.fastjson.JSON;
import com.tecode.service.BookTypeService;
import com.tecode.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AuthorManager {

    @Autowired
    private BooksService booksService;
    @Autowired
    private BookTypeService bookTypeService;

    //首页查询
    @RequestMapping(value = "/home_page", method = RequestMethod.GET)
    @ResponseBody
    public Object homePageData(String type, int pages) {
        return JSON.toJSON(booksService.homePageData(type, pages, 5));
    }

    //作者书籍查询
    @RequestMapping(value = "/bookdetails", method = RequestMethod.GET)
    @ResponseBody
    public Object bookdetails(String author, String type) {
        return JSON.toJSON(booksService.bookdetails(author, type));
    }

    //书籍类型查询
    @RequestMapping(value = "/booktype", method = RequestMethod.GET)
    @ResponseBody
    public Object bookType(int id) {
        return JSON.toJSON(bookTypeService.findById(id));
    }

    //作者书籍修改
    @RequestMapping(value = "/author_update", method = RequestMethod.GET)
    @ResponseBody
    public boolean bookUpdate(int id, double price, String status) {
        return booksService.bookUpdate(id, price, status);
    }

    //书籍章节更新
    @RequestMapping(value = "/bookUpdate", method = RequestMethod.POST)
    @ResponseBody
    public boolean chapterUpdate(String address, int id, int chapter, String title, String content) {
        return booksService.chapterUpdate(address, id, chapter, title, content);
    }

    //书籍最新章节
    @RequestMapping(value = "/chapter", method = RequestMethod.GET)
    @ResponseBody
    public int chapter(int bid) {
        return booksService.chapter(bid);
    }

    //书籍类型
    @RequestMapping(value = "/booktypes", method = RequestMethod.GET)
    @ResponseBody
    public Object bookTypes() {
        return JSON.toJSON(bookTypeService.findAll());
    }

    //添加书籍
    @RequestMapping(value = "/addbook", method = RequestMethod.POST)
    @ResponseBody
    public Object addBook(String author, String bookname, int lxint, String lb, String jj, String title, String content) {
        return JSON.toJSON(booksService.addBook(author, bookname, lxint, lb, jj, title, content));
    }

    //上传图片
    @RequestMapping(value = "/uploadimg", method = RequestMethod.POST)
    @ResponseBody
    public boolean uploadImg(HttpServletRequest req, HttpServletResponse res) {
        return booksService.uploadImg(req, res);
    }
    //作者书籍删除
    @RequestMapping(value = "/bookremove")
    @ResponseBody
    public boolean bookRemove(int id, String addr) {
        return booksService.bookRemove(id, addr);
    }

    //作者修改书籍图片
    @RequestMapping(value = "/updateimg", method = RequestMethod.POST)
    @ResponseBody
    public String updateImg(HttpServletRequest req, HttpServletResponse res) {
        return booksService.updateImg(req, res);
    }
}
