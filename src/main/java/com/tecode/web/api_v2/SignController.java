package com.tecode.web.api_v2;

import com.tecode.model.User;
import com.tecode.service.serviceImpl.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class SignController {
    @Autowired
    private UserImpl signimpl;
    @ResponseBody
    @RequestMapping(value = "/member",method = RequestMethod.POST)
    public String Member(Integer id){
        User user = (User) signimpl.findById(id);
        return user.getRole();
    }

    //判断是否签到
    @RequestMapping(value = "/sign",method = RequestMethod.POST)
    @ResponseBody
    public boolean selectById(Integer id) throws ParseException {
        User user = (User) signimpl.findById(id);
        List list = new ArrayList();
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = dateFormat.parse(dateFormat.format(date));//当前时间
        System.out.println(date1);
        if (user.getDateSign().equals(date1) && user.getDateSign()!= null) {
            System.out.println("已签到");
            list.add(user.getDay());
            list.add(true);
            return true;
        } else {
//            list.add(user.getDay());
//            list.add(false);
            System.out.println("未签到");
            return false;
        }
    }

    //签到功能
    @ResponseBody
    @RequestMapping(value = "/signTo",method = RequestMethod.POST)
    public void Sign(Integer id) throws ParseException {
        User user = (User) signimpl.findById(id);
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date2 = dateFormat.parse(dateFormat.format(date));//当前时间
        //判断是否连续签到
//        if(date2){
//
//        }
        user.setDay(user.getDay() + 1);
        user.setDateSign(date2);
        signimpl.updateById(user);
        System.out.println(user);
        System.out.println(date2);
    }
}
