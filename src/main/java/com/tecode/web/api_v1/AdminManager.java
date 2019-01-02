package com.tecode.web.api_v1;

import com.alibaba.fastjson.JSON;
import com.tecode.model.User;
import com.tecode.service.serviceImpl.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
public class AdminManager {
    @Autowired
    private UserImpl userimpl;
    private Integer pageSize=10;
    private List list=null;
    @RequestMapping("/index")//管理员登录
    public String fiestpage(){
        return "/index";
    }
    @RequestMapping("/check")
    @ResponseBody
    public Object checkname(String username){
        boolean status=false;
        User user= (User) userimpl.findByUsername(username);
        System.out.println(user);
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
    public String login(){
        return "manager";
    }
    @RequestMapping("/member")//查看所有会员
    @ResponseBody
    public Object allUsermember(Integer pageNum,Integer pageSize){
        List<User> user= userimpl.findByRole(pageNum,pageSize,"会员");

        return JSON.toJSON(user);
    }
    @RequestMapping("/author")//查看所有作者
    @ResponseBody
    public Object allUserauthor(Integer pageNum,Integer pageSize){
        List<User> user= userimpl.findByRole(pageNum,pageSize,"作者");

        return JSON.toJSON(user);
    }
}
