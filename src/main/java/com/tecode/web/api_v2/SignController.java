package com.tecode.web.api_v2;

import com.alibaba.fastjson.JSON;
import com.tecode.model.User;
import com.tecode.service.serviceImpl.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class SignController {
    @Autowired
    private UserImpl signimpl;
    //签到排行榜
    @ResponseBody
    @RequestMapping(value = "/leaderboard",method = RequestMethod.POST)
    public Object Member(){
        return JSON.toJSON(signimpl.leaderboard());
    }

    //判断是否签到
    @RequestMapping(value = "/sign",method = RequestMethod.POST)
    @ResponseBody
    public Object selectById(String uname) throws ParseException {
        List<User> users = (List) signimpl.findByUsername(uname);
        Map list = new HashMap() ;
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = dateFormat.parse(dateFormat.format(date));//当前时间
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        for(User user:users){
            String week = sdf.format(user.getDateSign());
            switch (week){
                case "星期一":
                    list.put("week",1);
                    break;
                case "星期二":
                    list.put("week",2);
                    break;
                case "星期三":
                    list.put("week",3);
                    break;
                case "星期四":
                    list.put("week",4);
                    break;
                case "星期五":
                    list.put("week",5);
                    break;
                case "星期六":
                    list.put("week",6);
                    break;
                default:
                    list.put("week",7);
                    break;
            }
            if (user.getDateSign().equals(date1) && user.getDateSign()!= null) {

                list.put("iday",user.getIday());
                list.put("boolean",true);
                list.put("money",user.getMoney());
            } else {
                list.put("iday",user.getIday());
                list.put("boolean",false);
                list.put("money",user.getMoney());
            }
        }
        return JSON.toJSON(list);
    }

    //签到功能
    @ResponseBody
    @RequestMapping(value = "/signTo",method = RequestMethod.POST)
    public void Sign(String uname) throws ParseException {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = dateFormat.parse(dateFormat.format(date));//当前时间
        List<User> list = (List) signimpl.findByUsername(uname);
        //前一天时间
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        Date date2 = dateFormat.parse(dateFormat.format(calendar.getTime()));
        for(User user:list){
            //判断本次签到是否是连续签到
            if(date2.equals(user.getDateSign())&& user.getDateSign()!= null){
                user.setDay(user.getDay() + 1);
                user.setDateSign(date1);
                user.setIday(user.getIday()+1);
                signimpl.updateById(user);
            }else {
                user.setDay(user.getDay() + 1);
                user.setDateSign(date1);
                user.setIday(1);
                signimpl.updateById(user);
            }
        }
    }
}
