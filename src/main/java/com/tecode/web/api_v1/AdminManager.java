package com.tecode.web.api_v1;

import com.alibaba.fastjson.JSON;
import com.tecode.model.Manager;
import com.tecode.model.Message;
import com.tecode.model.User;
import com.tecode.service.ManagerService;
import com.tecode.service.serviceImpl.BookServiceImpl;
import com.tecode.service.serviceImpl.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
public class AdminManager {
    @Autowired
    private UserImpl userimpl;
    @Autowired
    private BookServiceImpl bookService;
    @Autowired
    private ManagerService managerService;
    @RequestMapping("/index")//管理员登录核查用户名
    public String fiestpage(){
        return "/index";
    }
    @RequestMapping("/check")
    @ResponseBody
    public Object checkname(String username){
        boolean status=false;
        List<Manager> user= managerService.selectCheckName(username);
        if(user.size()!=0){
            status=true;
        }
        return JSON.toJSON(status);
    }
    @RequestMapping("/login")//管理员登录
    public String login(HttpServletRequest req){
        HttpSession session=req.getSession();
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        List<Manager> list=managerService.selectCheck(username,password);
        if (list.size()==0){
            return "error";
        }
        int pages=1;
        Long count= Long.valueOf(10);
        Message message=new Message();
        message.setStatus(true);
        message.setMasg("人气书籍排行榜");
        session.removeAttribute("books");
        session.removeAttribute("users");
        session.setAttribute("admin",list.get(0));
        session.setAttribute("message",message);
        session.setAttribute("count",count);
        session.setAttribute("pages",1);
        session.setAttribute("total",1);
        session.setAttribute("nomal",userimpl.countPage("普通用户"));
        session.setAttribute("author",userimpl.countPage("作者"));
        session.setAttribute("booknumber",bookService.countBooks("审核通过"));
        session.setAttribute("book",bookService.homePageData("排行",pages,10));
        session.setAttribute("all","login");
        return "manager";
    }
    //退出
    @RequestMapping("/exit")
    public String exit(HttpSession session){
        session.invalidate();
        return "redirect:/index";
    }
    //返回登录页面
    @RequestMapping(value = {"index","","/"}, method = RequestMethod.GET)
    public String firstpage(){
        return "index";
    }
    //管理员修改头像
    @RequestMapping("updateavater")
    public String updateAvater(@RequestParam(value = "file") MultipartFile file, HttpServletRequest req) throws FileNotFoundException {
        System.out.println(file);
        Manager manager= (Manager) req.getSession().getAttribute("admin");
        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            String filePath = ResourceUtils.getURL("classpath:static").getPath() +"/images/"+ fileName;
            System.out.println(filePath.substring(1));
            File destFile = new File(filePath);
            destFile.getParentFile().mkdirs();
            try {
                file.transferTo(destFile);
                manager.setAvater(filePath.substring(1));
                managerService.updateAvater(manager);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        req.getSession().setAttribute("admin",manager);
        return "manager";
    }
}