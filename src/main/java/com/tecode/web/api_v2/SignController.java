package com.tecode.web.api_v2;

import com.alibaba.fastjson.JSON;
import com.tecode.model.User;
import com.tecode.service.serviceImpl.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
    @RequestMapping("/sign")
    @ResponseBody
    public Object selectById(Integer id) throws ParseException {
        User user = (User) signimpl.findById(3);
        List list = new ArrayList();
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = dateFormat.parse(dateFormat.format(date));//当前时间
        if (user.getDateSign().equals(date1) && user.getDateSign()!= null) {
            System.out.println("已签到");
            list.add(user.getDay());
            list.add(false);
        } else {
            user.setDay(user.getDay() + 1);
            user.setDateSign(date1);
            signimpl.updateById(user);
            System.out.println("未签到");
        }
        return JSON.toJSON(list);
    }
}
