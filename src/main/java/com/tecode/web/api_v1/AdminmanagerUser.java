package com.tecode.web.api_v1;

import com.tecode.model.Message;
import com.tecode.model.News;
import com.tecode.model.User;
import com.tecode.service.NewsService;
import com.tecode.service.serviceImpl.UserImpl;
import com.tecode.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AdminmanagerUser {
    @Autowired
    private UserImpl userimpl;
    @Autowired
    private NewsService newsService;
    @Autowired
    private DataUtil dataUtil;
    private Integer pageSize=10;
    private Long pageTotal;
    @RequestMapping("/userall")//查看所有普通用户
    public String allUser(Integer pageNum,String orders, HttpSession session){
        Long count=userimpl.countPage("普通用户");
        List<User> user= userimpl.findByRole(pageNum,pageSize,"普通用户",orders);
        if (count%10==0&&count>0){
            pageTotal=count/10;
        }else {pageTotal=count/10+1;}
        if (pageNum<1){ pageNum=1; };
        Message message=new Message();
        message.setStatus(true);
        message.setMasg("所有浏览用户");
        session.setAttribute("total",pageTotal);
        session.removeAttribute("books");
        session.removeAttribute("book");
        session.setAttribute("message",message);
        session.setAttribute("nomal",count);
        session.setAttribute("users",user);
        session.setAttribute("orderas",orders);
        session.setAttribute("pages",pageNum);
        session.setAttribute("all","userall");
        return "manager";
    }
//    @RequestMapping("/member")//查看所有会员
//    public String allUsermember(Integer pageNum, HttpSession session){
//        Long count=userimpl.countPage("会员");
//        List<User> user= userimpl.findByRole(pageNum,pageSize,"会员");
//        if (pageNum<1){ pageNum=1; };
//        if (pageNum>count/10){pageNum=pageNum-1; };
//        Message message=new Message();
//        message.setStatus(true);
//        message.setMasg("所有会员");
//        session.removeAttribute("books");
//        session.removeAttribute("book");
//        session.setAttribute("message",message);
//        session.setAttribute("member",count);
//        session.setAttribute("users",user);
//        session.setAttribute("pages",pageNum);
//        session.setAttribute("all","member");
//        return "manager";
//    }
    @RequestMapping("/author")//查看所有作者
    public String allUserauthor(Integer pageNum,String orders, HttpSession session){
        Long count=userimpl.countPage("作者");
        List<User> user= userimpl.findByRole(pageNum,pageSize,"作者",orders);
        if (count%10==0&&count>0){
            pageTotal=count/10;
        }else {pageTotal=count/10+1;}
        if (pageNum<1){ pageNum=1; };
        Message message=new Message();
        message.setStatus(true);
        message.setMasg("所有作者");
        session.removeAttribute("books");
        session.removeAttribute("book");
        session.setAttribute("message",message);
        session.setAttribute("author",count);
        session.setAttribute("total",pageTotal);
        session.setAttribute("users",user);
        session.setAttribute("orderas",orders);
        session.setAttribute("pages",pageNum);
        session.setAttribute("all","author");
        return "manager";
    }
    @RequestMapping("/authorOut")//撤销作者权限
    public String authorOut(int bid,String orders, HttpSession session){
        User users=userimpl.findById(bid);
        users.setRole("普通用户");
        userimpl.updateById(users);
        News news=new News();
        news.setBooknews("您的作者权限已被撤销");
        news.setUid(users.getId());
        news.setMasgtime(dataUtil.timeDay());
        newsService.addNew(news);
        Integer pageNum=1;
        Long count=userimpl.countPage("作者");
        List<User> user= userimpl.findByRole(pageNum,pageSize,"作者",orders);
        if (count%10==0&&count>0){
            pageTotal=count/10;
        }else {pageTotal=count/10+1;}
        Message message=new Message();
        message.setStatus(true);
        message.setMasg("所有作者");
        session.removeAttribute("books");
        session.removeAttribute("book");
        session.setAttribute("message",message);
        session.setAttribute("total",pageTotal);
        session.setAttribute("count",count);
        session.setAttribute("users",user);
        session.setAttribute("orderas",orders);
        session.setAttribute("pages",pageNum);
        session.setAttribute("all","author");
        return "manager";
    }
    @RequestMapping("/authorcheck")//审核新作者
    public String authorcheck(Integer pageNum,String orders, HttpSession session){
        Long count=userimpl.countStatus("审核中");
        List<User> user= userimpl.findByStatus(pageNum,pageSize,orders);
        if (pageNum<1){ pageNum=1; };
        if (count%10==0&&count>0){
            pageTotal=count/10;
        }else {pageTotal=count/10+1;}
        Message message=new Message();
        message.setStatus(true);
        message.setMasg("所有待审核的新作者");
        session.setAttribute("total",pageTotal);
        session.removeAttribute("books");
        session.removeAttribute("book");
        session.setAttribute("message",message);
        session.setAttribute("count",count);
        session.setAttribute("users",user);
        session.setAttribute("orderas",orders);
        session.setAttribute("pages",pageNum);
        session.setAttribute("all","authorcheck");
        return "manager";
    }
    @RequestMapping("/authorOk")//作者权限审核成功
    public String authorOk(int bid,String orders, HttpSession session){
        User users=userimpl.findById(bid);
        users.setRole("作者");
        users.setStatus("审核通过");
        userimpl.updateById(users);
        News news=new News();
        news.setBooknews("您已获得作者权限");
        news.setUid(users.getId());
        news.setMasgtime(dataUtil.timeDay());
        newsService.addNew(news);
        Integer pageNum=1;
        Long count=userimpl.countStatus("审核中");
        List<User> user= userimpl.findByStatus(pageNum,pageSize,orders);
        if (count%10==0&&count>0){
            pageTotal=count/10;
        }else {pageTotal=count/10+1;}
        Message message=new Message();
        message.setStatus(true);
        message.setMasg("所有待审核的新作者");
        session.setAttribute("total",pageTotal);
        session.removeAttribute("books");
        session.removeAttribute("book");
        session.setAttribute("message",message);
        session.setAttribute("count",count);
        session.setAttribute("users",user);
        session.setAttribute("orderas",orders);
        session.setAttribute("pages",pageNum);
        session.setAttribute("all","authorcheck");
        return "manager";
    }
    @RequestMapping("/authorNo")//作者权限审核失败
    public String authorNo(int bid,String orders, HttpSession session){
        User users=userimpl.findById(bid);
        users.setRole("普通用户");
        userimpl.updateById(users);
        News news=new News();
        news.setBooknews("您的作者权限获取失败");
        news.setUid(users.getId());
        news.setMasgtime(dataUtil.timeDay());
        newsService.addNew(news);
        Integer pageNum=1;
        Long count=userimpl.countStatus("审核中");
        List<User> user= userimpl.findByStatus(pageNum,pageSize,orders);
        if (count%10==0&&count>0){
            pageTotal=count/10;
        }else {pageTotal=count/10+1;}
        Message message=new Message();
        message.setStatus(true);
        message.setMasg("所有待审核的新作者");
        session.removeAttribute("books");
        session.removeAttribute("book");
        session.setAttribute("total",pageTotal);
        session.setAttribute("message",message);
        session.setAttribute("count",count);
        session.setAttribute("users",user);
        session.setAttribute("orderas",orders);
        session.setAttribute("pages",pageNum);
        session.setAttribute("all","authorcheck");
        return "manager";
    }
    //根据作者进行搜索
    @RequestMapping(value = "/searchuser", method = RequestMethod.POST)
    public String serchbookname(String pageNums,String namesbook,String orders, HttpSession session){
        Integer pageNum=Integer.valueOf(pageNums);
        Long count=userimpl.countByUsername(namesbook);
        List<User> list=userimpl.findByUsername(namesbook,orders);
        if (pageNum<1){ pageNum=1; };
        if (count%10==0){
            pageTotal=count/10;
        }else {pageTotal=count/10+1;}
        Message message=new Message();
        message.setStatus(true);
        message.setMasg("搜索结果书籍");
        session.setAttribute("total",pageTotal);
        session.setAttribute("message",message);
        session.setAttribute("usercount",count);
        session.setAttribute("pages",pageNum);
        session.setAttribute("users",list);
        session.setAttribute("orderas",orders);
        session.setAttribute("names",namesbook);
        session.setAttribute("all","searchuser");
        return "manager";
    }
}
