package com.tecode.web.api_v1;

import com.alibaba.fastjson.JSON;
import com.tecode.model.Message;
import com.tecode.service.serviceImpl.BookServiceImpl;
import com.tecode.service.serviceImpl.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AdminManager {
    @Autowired
    private UserImpl userimpl;
    @Autowired
    private BookServiceImpl bookService;
    private Integer pageSize=10;
    private List list=null;
    @RequestMapping("/index")//管理员登录核查用户名
    public String fiestpage(){
        return "/index";
    }
    @RequestMapping("/check")
    @ResponseBody
    public Object checkname(String username){
        boolean status=false;
        Object user= userimpl.findByUsername(username);
        if(user==null){
            status=true;
        }
        return JSON.toJSON(status);
    }
//    @RequestMapping("/login")
//    public ModelAndView login(String username, HttpServletRequest re){
//        ModelAndView model=new ModelAndView();
//        re.getSession().setAttribute("user",user);
//        model.addObject(user);
//        model.setViewName("manager");
//        return model;
//    }
    @RequestMapping("/login")//管理员登录
    public String login(HttpSession session){
        int pages=1;
        Long count= Long.valueOf(10);
        Message message=new Message();
        message.setStatus(true);
        message.setMasg("人气书籍排行榜");
        session.removeAttribute("books");
        session.removeAttribute("users");
        session.setAttribute("message",message);
        session.setAttribute("count",count);
        session.setAttribute("pages",1);
        session.setAttribute("book",bookService.homePageData("排行",pages));
        return "manager";
    }

}
