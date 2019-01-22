package com.tecode.web.api_v2;

import com.alibaba.fastjson.JSON;
import com.tecode.model.Sign;
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = dateFormat.parse(dateFormat.format(date));//当前时间
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        for(User user:users){
            String week = sdf.format(user.getDateSign());
            List<Sign> signs=signimpl.findByUid(user.getId());
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
            list.put("sign",signs.get(0));
        }

        return JSON.toJSON(list);
    }

    //签到功能
    @ResponseBody
    @RequestMapping(value = "/signTo",method = RequestMethod.POST)
    public void Sign(String uname) throws ParseException {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
            String week = sdf.format(date1);
            Sign sign=signimpl.findByUid(user.getId()).get(0);
            if(sign!=null){
                switch (week){
                    case "星期一":
                        sign.setMonday(1);
                        break;
                    case "星期二":
                        sign.setTuesday(1);
                        break;
                    case "星期三":
                        sign.setWednesday(1);
                        break;
                    case "星期四":
                        sign.setThursday(1);
                        break;
                    case "星期五":
                        sign.setFriday(1);
                        break;
                    case "星期六":
                        sign.setSaturday(1);
                        break;
                    default:
                        sign.setSunday(1);
                        break;
                }
                signimpl.updateByUid(sign);
            }else {
                Sign sign1=new Sign();
                sign1.setUid(user.getId());
                switch (week){
                    case "星期一":
                        sign1.setMonday(1);
                        break;
                    case "星期二":
                        sign1.setTuesday(1);
                        break;
                    case "星期三":
                        sign1.setWednesday(1);
                        break;
                    case "星期四":
                        sign1.setThursday(1);
                        break;
                    case "星期五":
                        sign1.setFriday(1);
                        break;
                    case "星期六":
                        sign1.setSaturday(1);
                        break;
                    default:
                        sign1.setSunday(1);
                        break;
                }
                signimpl.insert(sign1);
            }
        }
    }
}
