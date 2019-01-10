package com.tecode.web.api_v1;

import com.tecode.model.Books;
import com.tecode.model.Message;
import com.tecode.service.BookTypeService;
import com.tecode.service.serviceImpl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AdminManagerBook {
    @Autowired
    private BookServiceImpl bookService;
    @Autowired
    private BookTypeService bookTypeService;
    //书籍查询
    @RequestMapping(value = "/booksall", method = RequestMethod.GET)
    public String search(Integer pageNum, HttpSession session) {
        Long count=bookService.countBooks("审核通过");
        if (pageNum<1){ pageNum=1; };
        if (pageNum>count/10){pageNum=pageNum-1; };
        List<Books> list=bookService.bookAll(pageNum,"审核通过");
        Message message=new Message();
        message.setStatus(true);
        message.setMasg("所有书籍");
        session.removeAttribute("book");
        session.removeAttribute("users");
        session.setAttribute("message",message);
        session.setAttribute("booknumber",count);
        session.setAttribute("pages",pageNum);
        session.setAttribute("books",list);
        session.setAttribute("all","booksall");
        return "manager";
    }
    //更新章节
    @RequestMapping(value = "/updatesetion", method = RequestMethod.GET)
    public String updatesetion(Integer pageNum, HttpSession session) {
        return "manager";
    }
    //审核新书
    @RequestMapping(value = "/bookscheck", method = RequestMethod.GET)
    public String bookscheck(Integer pageNum, HttpSession session) {
        Long count=bookService.countBooks("审核中");
        List<Books> list=bookService.bookAll(pageNum,"审核中");
        if (pageNum<1){ pageNum=1; };
        if (pageNum>count/10){pageNum=pageNum-1; };
        Message message=new Message();
        message.setStatus(true);
        message.setMasg("最近新书");
        session.removeAttribute("book");
        session.removeAttribute("users");
        session.setAttribute("message",message);
        session.setAttribute("count",count);
        session.setAttribute("pages",pageNum);
        session.setAttribute("books",list);
        session.setAttribute("all","bookscheck");
        return "manager";
    }
    //审核新书通过
    @RequestMapping(value = "/bookscheckYes", method = RequestMethod.GET)
    public String bookscheckYes(int bid, HttpSession session) {
        Books books=bookService.selectByBookId(bid);
        books.setAuditing("审核通过");
        bookService.deletebooks(books);
        Integer pageNum=1;
        Long count=bookService.countBooks("审核中");
        List<Books> list=bookService.bookAll(pageNum,"审核中");
        session.removeAttribute("book");
        session.removeAttribute("users");
        session.setAttribute("count",count);
        session.setAttribute("pages",pageNum);
        session.setAttribute("books",list);
        session.setAttribute("all","bookscheck");
        return "manager";
    }
    //审核新书未通过
    @RequestMapping(value = "/bookscheckNot", method = RequestMethod.GET)
    public String bookscheckNot(int bid, HttpSession session) {
        Books books=bookService.selectByBookId(bid);
        books.setAuditing("审核未通过");
        bookService.deletebooks(books);
        Integer pageNum=1;
        Long count=bookService.countBooks("审核中");
        List<Books> list=bookService.bookAll(pageNum,"审核中");
        session.removeAttribute("book");
        session.removeAttribute("users");
        session.setAttribute("count",count);
        session.setAttribute("pages",pageNum);
        session.setAttribute("books",list);
        session.setAttribute("all","bookscheck");
        return "manager";
    }
    //未通过书籍查询
    @RequestMapping(value = "/bookscheckNots", method = RequestMethod.GET)
    public String bookscheckNots(Integer pageNum, HttpSession session) {
        Long count=bookService.countBooks("审核未通过");
        List<Books> list=bookService.bookAll(pageNum,"审核未通过");
        if (pageNum<1){ pageNum=1; };
        if (pageNum>count/10){pageNum=pageNum-1; };
        Message message=new Message();
        message.setStatus(true);
        message.setMasg("所有审核未通过的书籍");
        session.removeAttribute("book");
        session.removeAttribute("users");
        session.setAttribute("message",message);
        session.setAttribute("count",count);
        session.setAttribute("pages",pageNum);
        session.setAttribute("books",list);
        session.setAttribute("all","bookscheckNots");
        return "manager";
    }
    //下架书籍页面
    @RequestMapping(value = "/deletebooks", method = RequestMethod.GET)
    public String deletebooks(Integer pageNum, HttpSession session) {
        Long count=bookService.countBooks("审核通过");
        List<Books> list=bookService.bookAll(pageNum,"审核通过");
        if (pageNum<1){ pageNum=1; };
        if (pageNum>count/10){pageNum=pageNum-1; };
        Message message=new Message();
        message.setStatus(true);
        message.setMasg("所有书籍");
        session.removeAttribute("book");
        session.removeAttribute("users");
        session.setAttribute("message",message);
        session.setAttribute("count",count);
        session.setAttribute("pages",pageNum);
        session.setAttribute("books",list);
        session.setAttribute("all","deletebooks");
        return "manager";
    }
    //下架书籍功能
    @RequestMapping(value = "/deletebooksnew", method = RequestMethod.GET)
    public String deletebooksnew(int bid, HttpSession session) {
        Books books=bookService.selectByBookId(bid);
        books.setAuditing("已下架");
        bookService.deletebooks(books);
        Integer pageNum=1;
        Long count=bookService.countBooks("审核通过");
        List<Books> list=bookService.bookAll(pageNum,"审核通过");
        if (pageNum>count/10){pageNum=pageNum-1; };
        Message message=new Message();
        message.setStatus(true);
        message.setMasg("所有书籍");
        session.removeAttribute("book");
        session.removeAttribute("users");
        session.setAttribute("message",message);
        session.setAttribute("count",count);
        session.setAttribute("pages",pageNum);
        session.setAttribute("books",list);
        session.setAttribute("all","deletebooks");
        return "manager";
    }
    //所有已下架书籍查询
    @RequestMapping(value = "/booksdeletenew", method = RequestMethod.GET)
    public String booksdeletenew(Integer pageNum, HttpSession session) {
        Long count=bookService.countBooks("已下架");
        List<Books> list=bookService.bookAll(pageNum,"已下架");
        if (pageNum<1){ pageNum=1; };
        if (pageNum>count/10){pageNum=pageNum-1; };
        Message message=new Message();
        message.setStatus(true);
        message.setMasg("所有审核未通过的书籍");
        session.removeAttribute("book");
        session.removeAttribute("users");
        session.setAttribute("message",message);
        session.setAttribute("count",count);
        session.setAttribute("pages",pageNum);
        session.setAttribute("books",list);
        session.setAttribute("all","booksdeletenew");
        return "manager";
    }
    @RequestMapping(value = "/searchbook", method = RequestMethod.POST)
    public String serchbookname(String pageNums,String namesbook, HttpSession session){
        Integer pageNum=Integer.valueOf(pageNums);
        Long count=bookService.countBooksname(namesbook);
        List<Books> list=bookService.selectByBookname(pageNum,namesbook);
        if (pageNum<1){ pageNum=1; };
        if (pageNum>count/10){pageNum=pageNum-1; };
        Message message=new Message();
        message.setStatus(true);
        message.setMasg("搜索书籍结果");
        session.setAttribute("message",message);
        session.setAttribute("searchcount",count);
        session.setAttribute("pages",pageNum);
        session.setAttribute("books",list);
        session.setAttribute("names",namesbook);
        session.setAttribute("all","searchbook");
        return "manager";
    }
    @RequestMapping("bookstypes")
    public String bookstypes(HttpSession session){
        Message message=new Message();
        message.setStatus(true);
        message.setMasg("搜索书籍结果");
        session.setAttribute("message",message);
        session.setAttribute("types",bookTypeService.findAll());
        session.setAttribute("all","bookstypes");
        return "manager";
    }
}
