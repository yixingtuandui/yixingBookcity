package com.tecode.web.api_v1;

import com.tecode.model.Books;
import com.tecode.model.Message;
import com.tecode.model.User;
import com.tecode.service.serviceImpl.UserImpl;
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
    private Integer pageSize=10;
    @RequestMapping("/userall")//查看所有普通用户
    public String allUser(Integer pageNum, HttpSession session){
        int count=userimpl.countPage("普通用户").intValue();
        List<User> user= userimpl.findByRole(pageNum,pageSize,"普通用户");
        System.out.println(user);
        if (pageNum<1){ pageNum=1; };
        if (pageNum>count/10){pageNum=pageNum-1; };
        Message message=new Message();
        message.setStatus(true);
        message.setMasg("所有浏览用户");
        session.removeAttribute("books");
        session.removeAttribute("book");
        session.setAttribute("message",message);
        session.setAttribute("nomal",count);
        session.setAttribute("users",user);
        session.setAttribute("pages",pageNum);
        session.setAttribute("all","userall");
        return "manager";
    }
    @RequestMapping("/member")//查看所有会员
    public String allUsermember(Integer pageNum, HttpSession session){
        Long count=userimpl.countPage("会员");
        List<User> user= userimpl.findByRole(pageNum,pageSize,"会员");
        if (pageNum<1){ pageNum=1; };
        if (pageNum>count/10){pageNum=pageNum-1; };
        Message message=new Message();
        message.setStatus(true);
        message.setMasg("所有会员");
        session.removeAttribute("books");
        session.removeAttribute("book");
        session.setAttribute("message",message);
        session.setAttribute("member",count);
        session.setAttribute("users",user);
        session.setAttribute("pages",pageNum);
        session.setAttribute("all","member");
        return "manager";
    }
    @RequestMapping("/author")//查看所有作者
    public String allUserauthor(Integer pageNum, HttpSession session){
        Long count=userimpl.countPage("作者");
        List<User> user= userimpl.findByRole(pageNum,pageSize,"作者");
        if (pageNum<1){ pageNum=1; };
        if (pageNum>count/10){pageNum=pageNum-1; };
        Message message=new Message();
        message.setStatus(true);
        message.setMasg("所有作者");
        session.removeAttribute("books");
        session.removeAttribute("book");
        session.setAttribute("message",message);
        session.setAttribute("author",count);
        session.setAttribute("users",user);
        session.setAttribute("pages",pageNum);
        session.setAttribute("all","author");
        return "manager";
    }
    @RequestMapping("/authorOut")//撤销作者权限
    public String authorOut(int bid, HttpSession session){
        User users=userimpl.findById(bid);
        users.setRole("会员");
        userimpl.updateById(users);
        Integer pageNum=1;
        Long count=userimpl.countPage("作者");
        List<User> user= userimpl.findByRole(pageNum,pageSize,"作者");
        Message message=new Message();
        message.setStatus(true);
        message.setMasg("所有作者");
        session.removeAttribute("books");
        session.removeAttribute("book");
        session.setAttribute("message",message);
        session.setAttribute("count",count);
        session.setAttribute("users",user);
        session.setAttribute("pages",pageNum);
        session.setAttribute("all","author");
        return "manager";
    }
    @RequestMapping("/authorcheck")//审核新作者
    public String authorcheck(Integer pageNum, HttpSession session){
        Long count=userimpl.countStatus("审核中");
        List<User> user= userimpl.findByStatus(pageNum,pageSize);
        if (pageNum<1){ pageNum=1; };
        if (pageNum>count/10){pageNum=pageNum-1; };
        Message message=new Message();
        message.setStatus(true);
        message.setMasg("所有待审核的新作者");
        session.removeAttribute("books");
        session.removeAttribute("book");
        session.setAttribute("message",message);
        session.setAttribute("count",count);
        session.setAttribute("users",user);
        session.setAttribute("pages",pageNum);
        session.setAttribute("all","authorcheck");
        return "manager";
    }
    @RequestMapping("/authorOk")//作者权限审核成功
    public String authorOk(int bid, HttpSession session){
        User users=userimpl.findById(bid);
        users.setRole("作者");
        users.setStatus("审核通过");
        userimpl.updateById(users);
        Integer pageNum=1;
        Long count=userimpl.countStatus("审核中");
        List<User> user= userimpl.findByStatus(pageNum,pageSize);
        Message message=new Message();
        message.setStatus(true);
        message.setMasg("所有待审核的新作者");
        session.removeAttribute("books");
        session.removeAttribute("book");
        session.setAttribute("message",message);
        session.setAttribute("count",count);
        session.setAttribute("users",user);
        session.setAttribute("pages",pageNum);
        session.setAttribute("all","authorcheck");
        return "manager";
    }
    @RequestMapping("/authorNo")//作者权限审核失败
    public String authorNo(int bid, HttpSession session){
        User users=userimpl.findById(bid);
        users.setRole("会员");
        userimpl.updateById(users);
        Integer pageNum=1;
        Long count=userimpl.countStatus("审核中");
        List<User> user= userimpl.findByStatus(pageNum,pageSize);
        Message message=new Message();
        message.setStatus(true);
        message.setMasg("所有待审核的新作者");
        session.removeAttribute("books");
        session.removeAttribute("book");
        session.setAttribute("message",message);
        session.setAttribute("count",count);
        session.setAttribute("users",user);
        session.setAttribute("pages",pageNum);
        session.setAttribute("all","authorcheck");
        return "manager";
    }
    @RequestMapping(value = "/searchuser", method = RequestMethod.POST)
    public String serchbookname(String pageNums,String namesbook, HttpSession session){
        Integer pageNum=Integer.valueOf(pageNums);
        Long count=userimpl.countByUsername(namesbook);
        List<User> list=userimpl.findByUsername(namesbook);
        if (pageNum<1){ pageNum=1; };
        if (pageNum>count/10){pageNum=pageNum-1; };
        Message message=new Message();
        message.setStatus(true);
        message.setMasg("搜索结果书籍");
        session.setAttribute("message",message);
        session.setAttribute("usercount",count);
        session.setAttribute("pages",pageNum);
        session.setAttribute("books",list);
        session.setAttribute("names",namesbook);
        session.setAttribute("all","searchuser");
        return "manager";
    }
}
