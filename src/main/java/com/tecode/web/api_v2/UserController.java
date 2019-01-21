package com.tecode.web.api_v2;

import com.alibaba.fastjson.JSON;
import com.tecode.model.User;
import com.tecode.service.NewsService;
import com.tecode.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private NewsService newsService;

    @RequestMapping("userLogin")
    @ResponseBody
    public Object userLogin(String username, String sex, String avater, Date date_reg) {
//        System.out.println(username);
        User us = new User();
        List<User> user = userService.findByUsername(username);
        int sexs = Integer.valueOf(sex);
        if (user.size() == 0) {
            us.setName(username);
            us.setAvator(avater);
            us.setRole("普通用户");
            us.setDateReg(date_reg);
            if (sexs == 1) {
                us.setSex("男");
            } else {
                us.setSex("女");
            }
            userService.add(us);
            return JSON.toJSON(us);
        }
        return JSON.toJSON(user);
    }

    //成为作者
    @RequestMapping(value = "/applyauthor", method = RequestMethod.GET)
    @ResponseBody
    public boolean applyAuthor(int id, String penname, String phone, String sex) {
        return userService.applyAuthor(id, penname, phone, sex);
    }

    //修改性别
    @RequestMapping(value = "/sexupdate", method = RequestMethod.GET)
    @ResponseBody
    public User sexUpdate(int id, String sex) {
        return userService.sexUpdate(id, sex);
    }

    //修改手机
    @RequestMapping(value = "/phoneupdate", method = RequestMethod.GET)
    @ResponseBody
    public User sexPhone(int id, String phone) {
        return userService.phoneUpdate(id, phone);
    }

    //我的消息
    @RequestMapping(value = "/inews", method = RequestMethod.GET)
    @ResponseBody
    public Object iNews(int uid) {
        return JSON.toJSON(newsService.iNews(uid));
    }

    //消息删除
    @RequestMapping(value = "/delatenews")
    @ResponseBody
    public boolean delateNews(int uid, int id) {
        return newsService.delateNews(uid, id);
    }

    //别名验证
    @RequestMapping(value = "/inspectpenname")
    @ResponseBody
    public boolean inspectPenName(String penname) {
        return userService.inspectPenName(penname);
    }

    //手机验证
    @RequestMapping(value = "/inspectphone")
    @ResponseBody
    public boolean inspectPhone(String phone) {
        return userService.inspectPhone(phone);
    }
}
