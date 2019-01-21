package com.tecode.web.api_v2;

import com.alibaba.fastjson.JSON;
import com.tecode.model.Books;
import com.tecode.model.History;
import com.tecode.model.User;
import com.tecode.service.serviceImpl.BookServiceImpl;
import com.tecode.service.serviceImpl.BookShelfImpl;
import com.tecode.service.serviceImpl.UserImpl;
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
    @Autowired
    private UserImpl user;

    // 书架 (显示添加到书架的书)
    @RequestMapping(value = "/bookshelf", method = RequestMethod.POST)
    @ResponseBody
    public Object BookShelf(Integer uid) {
        List list = new ArrayList();
        List<History> history = bookShelf.BookShelf(uid);//根据userID获取history中所有书籍
        for (History history1 : history) {
//            System.out.println(history1);
            if (history1.getAddbookshelf()) {
                Books books = bookService.selectByBookId(history1.getBookid());//根据书籍ID查书
                if(books.getAuditing().equals("已下架")){
                    continue;
                }
                list.add(books);
            }
        }
        return JSON.toJSON(list);
    }
    //添加书籍到书架
    @RequestMapping(value = "/addshelf",method = RequestMethod.POST)
    @ResponseBody//返回数据
    private Object addBookShelf(String name,Integer bookid){
        System.out.println(name);
        System.out.println(bookid);

        User user1=  user.findByUsername(name).get(0);
        History history= bookShelf.ByHistory(bookid,user1.getId()).get(0);
        if(!history.getAddbookshelf()){
            history.setAddbookshelf(true);
            bookShelf.updateById(history);
            return true;
        }
//        System.out.println(history);

        System.out.println("进");
        return false;
    }
    //删除书架书籍
    @ResponseBody
    @RequestMapping(value = "deletebook",method = RequestMethod.POST)
    public Object deletebook(String name,Integer bookid){
        System.out.println("删除");
        User user1=  user.findByUsername(name).get(0);
        History history=bookShelf.ByHistory(bookid,user1.getId()).get(0);
        history.setAddbookshelf(false);
        bookShelf.updateById(history);
        return BookShelf(user1.getId());
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
    //添加阅读记录
    @RequestMapping(value = "/addrecently",method = RequestMethod.POST)
    @ResponseBody
    private void addRecently(String uname,Integer bookid) throws ParseException {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = dateFormat.parse(dateFormat.format(date));//当前时间
        User user1=  user.findByUsername(uname).get(0);
        //判断是否存在
        if(bookShelf.ByHistory(bookid,user1.getId()).size()!=0){
            for (History history1:bookShelf.ByHistory(bookid,user1.getId())){
                history1.setReadingTime(date1);
                bookShelf.updateById(history1);
            }
        }else {
            History history= new History();
            history.setAddbookshelf(false);
            history.setBookid(bookid);
            history.setBuy("未购买");
            history.setReadingTime(date1);
            history.setUid(user1.getId());
            bookShelf.addRecently(history);
            System.out.println("添加阅读记录成功");
        }

    }
    //购买书籍
    @RequestMapping(value = "buybook")
    @ResponseBody
    public Object buybook(Integer bid,Integer uid){
        List<History> list=bookShelf.selectByCheck(bid,uid,"已购买");
        User user1=user.findById(uid);
        Books books=bookService.selectByBookId(bid);
        if(list.size()==0){
            if(books.getPrice()<=user1.getMoney()){
                user1.setMoney(user1.getMoney()-books.getPrice());
                user.updateById(user1);
                return JSON.toJSONString("购买成功");
            }else{
                return JSON.toJSONString("购买失败，您的余额不足，请充值");
            }
        }
            return JSON.toJSONString("您已购买此书");
    }
    //展示购买的购买书籍
    @RequestMapping(value = "mybooks")
    @ResponseBody
    public Object mybooks(Integer uid){
        List<History> histories=bookShelf.selectByBuy(uid,"已购买");
        List<Books> book=new ArrayList<Books>();
        Books books=null;
        if(histories.size()!=0){
            for(History hist:histories){
                books=bookService.selectByBookId(hist.getBookid());
                book.add(books);
            }
        }
        return JSON.toJSON(book);
    }
}
