package com.tecode.web.api_v2;

import com.alibaba.fastjson.JSON;
import com.tecode.model.Comment;
import com.tecode.service.serviceImpl.CommentsImpl;
import com.tecode.service.serviceImpl.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class CommentsController {
    @Autowired
    private CommentsImpl comments;
    @Autowired
    private UserImpl user;
    @RequestMapping("/comment")//查询该书籍的所有评论
    @ResponseBody
    public Object allComments(String bid){
        return JSON.toJSON(comments.findByBookId(Integer.valueOf(bid)));
    }
    @RequestMapping("/setcomment")//用户留言评论
    @ResponseBody
    public void setComments(String bid, String uid, String belong,String context,String parentid, Date time){
        Comment comm=new Comment();
        comm.setBookid(Integer.valueOf(bid));
        comm.setUid(Integer.valueOf(uid));
        comm.setContent(context);
        comm.setBelong(Integer.valueOf(belong));
        comm.setParentid(Integer.valueOf(parentid));
        comm.setTime(time);
        comments.add(comm);
    }
    @RequestMapping(value = "/lesscomment",method = RequestMethod.POST)
    @ResponseBody
    public Object lesscomment(String bid){
        return comments.findByBookIdLess(Integer.valueOf(bid));
    }
}
