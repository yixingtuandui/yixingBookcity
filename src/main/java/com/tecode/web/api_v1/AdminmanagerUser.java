package com.tecode.web.api_v1;

import com.alibaba.fastjson.JSON;
import com.tecode.model.User;
import com.tecode.service.serviceImpl.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
@Controller
public class AdminmanagerUser {
    @Autowired
    private UserImpl userimpl;
    private List list=null;
    private Integer pageSize=10;
    @RequestMapping("/userall")//查看所有普通用户
    @ResponseBody
    public Object allUser(Integer pageNum){
        list=new ArrayList();
        if(pageNum==0){
            pageNum=1;
        }
        Long count=userimpl.countPage("普通用户");
        List<User> user= userimpl.findByRole(pageNum,pageSize,"普通用户");
        list.add(user);
        list.add(count);
        return JSON.toJSON(list);
    }
}
