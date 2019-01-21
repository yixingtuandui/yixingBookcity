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
    private Long pageTotal;
    //书籍查询
    @RequestMapping(value = "/booksall", method = RequestMethod.GET)
    public String search(Integer pageNum,String orders, HttpSession session) {
        Long count=bookService.countBooks("审核通过");
        if (count%10==0&&count>0){
            pageTotal=count/10;
        }else {pageTotal=count/10+1;}
        if (pageNum<1){ pageNum=1; };
        List<Books> list=bookService.bookAll(pageNum,"审核通过",orders);
        Message message=new Message();
        message.setStatus(true);
        message.setMasg("所有书籍");
        session.removeAttribute("book");
        session.removeAttribute("users");
        session.setAttribute("message",message);
        session.setAttribute("booknumber",count);
        session.setAttribute("pages",pageNum);
        session.setAttribute("books",list);
        session.setAttribute("orderas",orders);
        session.setAttribute("total",pageTotal);
        session.setAttribute("all","booksall");
        return "manager";
    }
//    //更新章节
//    @RequestMapping(value = "/updatesetion", method = RequestMethod.GET)
//    public String updatesetion(Integer pageNum, String orders,HttpSession session) {
//        return "manager";
//    }
    //审核新书
    @RequestMapping(value = "/bookscheck", method = RequestMethod.GET)
    public String bookscheck(Integer pageNum, String orders,HttpSession session) {
        Long count=bookService.countBooks("审核中");
        List<Books> list=bookService.bookAll(pageNum,"审核中",orders);
        if (count%10==0&&count>0){
            pageTotal=count/10;
        }else {pageTotal=count/10+1;}
        if (pageNum<1){ pageNum=1; };
        Message message=new Message();
        message.setStatus(true);
        message.setMasg("最近新书");
        session.setAttribute("total",pageTotal);
        session.removeAttribute("book");
        session.removeAttribute("users");
        session.setAttribute("orderas",orders);
        session.setAttribute("message",message);
        session.setAttribute("count",count);
        session.setAttribute("pages",pageNum);
        session.setAttribute("books",list);
        session.setAttribute("all","bookscheck");
        return "manager";
    }
    //审核新书通过
    @RequestMapping(value = "/bookscheckYes", method = RequestMethod.GET)
    public String bookscheckYes(int bid, String orders,HttpSession session) {
        Books books=bookService.selectByBookId(bid);
        books.setAuditing("审核通过");
        bookService.deletebooks(books);
        Integer pageNum=1;
        Long count=bookService.countBooks("审核中");
        List<Books> list=bookService.bookAll(pageNum,"审核中",orders);
        if (count%10==0&&count>0){
            pageTotal=count/10;
        }else {pageTotal=count/10+1;}
        session.setAttribute("total",pageTotal);
        session.removeAttribute("book");
        session.removeAttribute("users");
        session.setAttribute("count",count);
        session.setAttribute("pages",pageNum);
        session.setAttribute("orderas",orders);
        session.setAttribute("books",list);
        session.setAttribute("all","bookscheck");
        return "manager";
    }
    //审核新书未通过
    @RequestMapping(value = "/bookscheckNot", method = RequestMethod.GET)
    public String bookscheckNot(int bid, String orders,HttpSession session) {
        Books books=bookService.selectByBookId(bid);
        books.setAuditing("审核未通过");
        bookService.deletebooks(books);
        Integer pageNum=1;
        Long count=bookService.countBooks("审核中");
        List<Books> list=bookService.bookAll(pageNum,"审核中",orders);
        if (count%10==0&&count>0){
            pageTotal=count/10;
        }else {pageTotal=count/10+1;}
        session.removeAttribute("book");
        session.removeAttribute("users");
        session.setAttribute("count",count);
        session.setAttribute("pages",pageNum);
        session.setAttribute("orderas",orders);
        session.setAttribute("books",list);
        session.setAttribute("total",pageTotal);
        session.setAttribute("all","bookscheck");
        return "manager";
    }
    //未通过书籍查询
    @RequestMapping(value = "/bookscheckNots", method = RequestMethod.GET)
    public String bookscheckNots(Integer pageNum,String orders, HttpSession session) {
        Long count=bookService.countBooks("审核未通过");
        List<Books> list=bookService.bookAll(pageNum,"审核未通过",orders);
        if (count%10==0&&count>0){
            pageTotal=count/10;
        }else {pageTotal=count/10+1;}
        if (pageNum<1){ pageNum=1; };
        Message message=new Message();
        message.setStatus(true);
        message.setMasg("所有审核未通过的书籍");
        session.setAttribute("total",pageTotal);
        session.removeAttribute("book");
        session.removeAttribute("users");
        session.setAttribute("message",message);
        session.setAttribute("count",count);
        session.setAttribute("pages",pageNum);
        session.setAttribute("orderas",orders);
        session.setAttribute("books",list);
        session.setAttribute("all","bookscheckNots");
        return "manager";
    }
    //下架书籍页面
    @RequestMapping(value = "/deletebooks", method = RequestMethod.GET)
    public String deletebooks(Integer pageNum,String orders, HttpSession session) {
        Long count=bookService.countBooks("审核通过");
        List<Books> list=bookService.bookAll(pageNum,"审核通过",orders);
        if (count%10==0&&count>0){
            pageTotal=count/10;
        }else {pageTotal=count/10+1;}
        if (pageNum<1){ pageNum=1; };
        Message message=new Message();
        message.setStatus(true);
        message.setMasg("所有书籍");
        session.removeAttribute("book");
        session.removeAttribute("users");
        session.setAttribute("message",message);
        session.setAttribute("count",count);
        session.setAttribute("pages",pageNum);
        session.setAttribute("orderas",orders);
        session.setAttribute("books",list);
        session.setAttribute("total",pageTotal);
        session.setAttribute("all","deletebooks");
        return "manager";
    }
    //下架书籍功能
    @RequestMapping(value = "/deletebooksnew", method = RequestMethod.GET)
    public String deletebooksnew(int bid,String orders, HttpSession session) {
        Books books=bookService.selectByBookId(bid);
        books.setAuditing("已下架");
        bookService.deletebooks(books);
        Integer pageNum=1;
        Long count=bookService.countBooks("审核通过");
        List<Books> list=bookService.bookAll(pageNum,"审核通过",orders);
        if (count%10==0&&count>0){
            pageTotal=count/10;
        }else {pageTotal=count/10+1;}
        Message message=new Message();
        message.setStatus(true);
        message.setMasg("所有书籍");
        session.removeAttribute("book");
        session.removeAttribute("users");
        session.setAttribute("message",message);
        session.setAttribute("count",count);
        session.setAttribute("pages",pageNum);
        session.setAttribute("books",list);
        session.setAttribute("orderas",orders);
        session.setAttribute("total",pageTotal);
        session.setAttribute("all","deletebooks");
        return "manager";
    }
    //所有已下架书籍查询
    @RequestMapping(value = "/booksdeletenew", method = RequestMethod.GET)
    public String booksdeletenew(Integer pageNum,String orders, HttpSession session) {
        Long count=bookService.countBooks("已下架");
        List<Books> list=bookService.bookAll(pageNum,"已下架",orders);
        if (pageNum<1){ pageNum=1; };
        if (count%10==0&&count>0){
            pageTotal=count/10;
        }else {pageTotal=count/10+1;}
        Message message=new Message();
        message.setStatus(true);
        message.setMasg("所有审核未通过的书籍");
        session.removeAttribute("book");
        session.removeAttribute("users");
        session.setAttribute("message",message);
        session.setAttribute("count",count);
        session.setAttribute("pages",pageNum);
        session.setAttribute("books",list);
        session.setAttribute("orderas",orders);
        session.setAttribute("total",pageTotal);
        session.setAttribute("all","booksdeletenew");
        return "manager";
    }
    //已下架书籍重新上架
    @RequestMapping(value = "/booksdeletenew_one", method = RequestMethod.GET)
    public String booksdeletenewone(Integer bid,String orders, HttpSession session) {
        Books books=bookService.selectByBookId(bid);
        books.setAuditing("审核通过");
        bookService.deletebooks(books);
        Integer pageNum=1;
        Long count=bookService.countBooks("已下架");
        List<Books> list=bookService.bookAll(pageNum,"已下架",orders);
        if (count%10==0&&count>0){
            pageTotal=count/10;
        }else {pageTotal=count/10+1;}
        session.setAttribute("count",count);
        session.setAttribute("pages",pageNum);
        session.setAttribute("books",list);
        session.setAttribute("orderas",orders);
        session.setAttribute("total",pageTotal);
        session.setAttribute("all","booksdeletenew");
        return "manager";
    }
    //书籍搜索
    @RequestMapping(value = "/searchbook", method = RequestMethod.POST)
    public String serchbookname(String pageNums,String namesbook,String orders, HttpSession session){
        Integer pageNum=Integer.valueOf(pageNums);
        Long count=bookService.countBooksname(namesbook);
        List<Books> list=bookService.selectByBooknameOrder(pageNum,namesbook,orders);
        if (pageNum<1){ pageNum=1; };
        if (count%10==0&&count>0){
            pageTotal=count/10;
        }else {pageTotal=count/10+1;}
        Message message=new Message();
        message.setStatus(true);
        message.setMasg("搜索书籍结果");
        session.setAttribute("message",message);
        session.setAttribute("searchcount",count);
        session.setAttribute("pages",pageNum);
        session.setAttribute("books",list);
        session.setAttribute("orderas",orders);
        session.setAttribute("names",namesbook);
        session.setAttribute("total",pageTotal);
        session.setAttribute("all","searchbook");
        return "manager";
    }
    //书籍类型查询
    @RequestMapping("bookstypes")
    public String bookstypes(HttpSession session){
        pageTotal= Long.valueOf(1);
        Message message=new Message();
        message.setStatus(true);
        message.setMasg("搜索书籍类型结果");
        session.setAttribute("message",message);
        session.setAttribute("types",bookTypeService.findAll());
        session.setAttribute("all","bookstypes");
        session.setAttribute("total",pageTotal);
        return "manager";
    }
}
