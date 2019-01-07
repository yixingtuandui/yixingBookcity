package com.tecode.web.api_v2;

import com.alibaba.fastjson.JSON;
import com.tecode.model.History;
import com.tecode.service.serviceImpl.BookServiceImpl;
import com.tecode.service.serviceImpl.BookShelfImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class BookShelfController {
    @Autowired
    private BookShelfImpl bookShelf;
    @Autowired
    private BookServiceImpl bookService;

    // 书架 (显示添加到书架的书)
    @RequestMapping(value = "/bookshelf", method = RequestMethod.POST)
    @ResponseBody
    public Object BookShelf(Integer uid) {
        List list = new ArrayList();
        List<History> history = bookShelf.BookShelf(uid);//根据userID获取history中所有书籍
        for (History history1 : history) {
            System.out.println(history1);
            if (history1.getAddbookshelf()) {
                Object books = bookService.selectByBookId(history1.getBookid());//根据书籍ID查书
                list.add(books);
            }
        }
        return JSON.toJSON(list);
    }

    // 最近阅读 (只显示最近一周的书)
    @RequestMapping(value = "/recently", method = RequestMethod.POST)
    @ResponseBody
    public Object Recently(Integer uid) throws ParseException {
        //装获取到的书籍
        List list = new ArrayList();
        //获取当前时间
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = dateFormat.parse(dateFormat.format(date));//当前时间
        //得到之前第七天时间
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -6);
        Date date2 = dateFormat.parse(dateFormat.format(calendar.getTime()));//结束时间
        List<History> history = bookShelf.BookShelf(uid);
        for (History history1 : history) {
            if (history1.getReadingTime().equals(date1) || history1.getReadingTime().equals(date2)) {
                list.add(bookService.selectByBookId(history1.getBookid()));
            }
            //阅读时间
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(history1.getReadingTime());
            //开始时间
            Calendar begin = Calendar.getInstance();
            begin.setTime(date2);
            //结束时间
            Calendar end = Calendar.getInstance();
            end.setTime(date1);

            if (calendar1.after(begin) && calendar1.before(end)) {
                list.add(bookService.selectByBookId(history1.getBookid()));
            }
        }
        return JSON.toJSON(list);
    }
}
