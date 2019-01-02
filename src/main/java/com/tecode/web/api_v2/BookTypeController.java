package com.tecode.web.api_v2;

import com.tecode.model.BookType;
import com.tecode.model.Books;
import com.tecode.service.serviceImpl.BookServiceImpl;
import com.tecode.service.serviceImpl.BookTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class BookTypeController {

    @Autowired
    private BookServiceImpl bookService;
    @Autowired
    private BookTypeServiceImpl bookTypeService;
//返回图书类型
    @RequestMapping(value = "showType",method = RequestMethod.POST)
    @ResponseBody
    public List<BookType> show(){
        return bookTypeService.findAll();
    }
//该类型的书籍
    @RequestMapping(value = "booksType",method = RequestMethod.POST)
    @ResponseBody
    public Object booksAll(int type){
        return bookService.selectByType(type);
    }
//该书籍的详情
    @RequestMapping(value = "bookx",method = RequestMethod.POST)
    @ResponseBody
    public Books booksXq(int bid){
        return bookService.selectByBookId(bid);
    }
//    返回类型
    @RequestMapping(value = "type",method = RequestMethod.POST)
    @ResponseBody
    public BookType booksType(int id){
        System.out.println(id);
        return bookTypeService.findById(id);
    }
//    畅销
    @RequestMapping(value = "shopp",method = RequestMethod.POST)
    public List<Books> shopp(){
        System.out.println("进入了");
        return bookService.homePageData("推荐",1);
    }
//    人气
    @RequestMapping(value = "heat",method = RequestMethod.POST)
    public List<Books> heat(){
        System.out.println("就是这儿");
        return bookService.homePageData("排行",1);
    }
    /**
     * 获得本周的第一天，周一
     *
     * @return
     */
    private final static SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");
    private final static SimpleDateFormat longHourSdf = new SimpleDateFormat("yyyy-MM-dd HH");;
    private final static SimpleDateFormat longSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");;
    public static Date getCurrentWeekDayStartTime() {
        Calendar c = Calendar.getInstance();
        try {
            int weekday = c.get(Calendar.DAY_OF_WEEK) - 2;
            c.add(Calendar.DATE, -weekday);
            c.setTime(longSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c.getTime();
    }

    /**
     * 获得本周的最后一天，周日
     *
     * @return
     */
    public static Date getCurrentWeekDayEndTime() {
        Calendar c = Calendar.getInstance();
        try {
            int weekday = c.get(Calendar.DAY_OF_WEEK);
            c.add(Calendar.DATE, 8 - weekday);
            c.setTime(longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(c.getTime());
        return c.getTime();
    }
}
