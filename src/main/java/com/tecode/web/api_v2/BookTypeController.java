package com.tecode.web.api_v2;

import com.tecode.model.BookType;
import com.tecode.model.Books;
import com.tecode.service.serviceImpl.BookServiceImpl;
import com.tecode.service.serviceImpl.BookTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RestController
public class BookTypeController {

    @Autowired
    private BookServiceImpl bookService;
    @Autowired
    private BookTypeServiceImpl bookTypeService;
//返回图书类型
    @RequestMapping(value = "showType",method = RequestMethod.POST)
    public List<BookType> show(){
        return bookTypeService.findAll();
    }
//该类型的书籍
    @RequestMapping(value = "booksType",method = RequestMethod.POST)
    public List<Books> booksAll(int type, int pageNum){
        return bookService.selectByType(type,pageNum);
    }
//该书籍的详情
    @RequestMapping(value = "bookx",method = RequestMethod.POST)
    public Books booksXq(int bid){
        return bookService.selectByBookId(bid);
    }
//    返回类型
    @RequestMapping(value = "type",method = RequestMethod.POST)
    public BookType booksType(int id){
        return bookTypeService.findById(id);
    }
//    畅销
    @RequestMapping(value = "shopp",method = RequestMethod.POST)
    public List<Books> shopp(int pageNum){
        return bookService.selectByAmount(pageNum);
    }
//    人气
    @RequestMapping(value = "heat",method = RequestMethod.POST)
    public List<Books> heat(int pageNum){
        return bookService.selectByNumber(pageNum);
    }
    //    畅销 月榜
    @RequestMapping(value = "monthCX",method = RequestMethod.POST)
    public List<Books> monthCX(int pageNum){
        return bookService.selectByMonthAmount(pageNum);
    }
    //    人气 月榜
    @RequestMapping(value = "monthRQ",method = RequestMethod.POST)
    public List<Books> monthRQ(int pageNum){
        return bookService.selectByMonthNumber(pageNum);
    }
    //    畅销 周榜
    @RequestMapping(value = "weekCX",method = RequestMethod.POST)
    public List<Books> weekCX(int pageNum){
        return bookService.selectByWeekAmount(pageNum);
    }
    //    人气 周榜
    @RequestMapping(value = "weekRQ",method = RequestMethod.POST)
    public List<Books> weekRQ(int pageNum){
        return bookService.selectByWeekNumber(pageNum);
    }
    //    增加畅销榜
    @RequestMapping(value = "join",method = RequestMethod.POST)
    public void addCX(int id){
        bookService.addCX(id);
    }

}
