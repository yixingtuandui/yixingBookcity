package com.tecode.web.api_v2;

import com.alibaba.fastjson.JSON;
import com.tecode.model.User;
import com.tecode.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("userLogin")
    @ResponseBody
    public Object userLogin(String username, String sex, String avater, Date date_reg){
//        System.out.println(username);
        User us=new User();
        List<User> user= userService.findByUsername(username);
        int sexs=Integer.valueOf(sex);
        if(user.size()==0){
            us.setName(username);
            us.setAvator(avater);
            us.setRole("普通用户");
            us.setDateReg(date_reg);
            if(sexs==1){
                us.setSex("男");
            }else{
                us.setSex("女");
            }
            userService.add(us);
            return JSON.toJSON(us);
        }
        return JSON.toJSON(user);
    }
}
