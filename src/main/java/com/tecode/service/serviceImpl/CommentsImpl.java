package com.tecode.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.tecode.dao.CommentMapper;
import com.tecode.model.Comment;
import com.tecode.model.CommentExample;
import com.tecode.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentsImpl implements CommentsService {
    @Autowired
    private CommentMapper commentMapper;
    @Override
    public void add(Comment com) {
        commentMapper.insert(com);
    }

    @Override
    public List findByBookId(Integer id) {
        CommentExample example=new CommentExample();
        example.createCriteria().andBookidEqualTo(id);
        return commentMapper.selectByExample(example);
    }

    @Override
    public List findByBookIdLess(Integer id) {
        PageHelper.startPage(1,10);
        CommentExample example=new CommentExample();
        example.createCriteria().andBookidEqualTo(id).andParentidEqualTo(0);
        example.setOrderByClause("time desc");
        return commentMapper.selectByExample(example);
    }
}
