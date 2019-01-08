package com.tecode.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.tecode.dao.UserMapper;
import com.tecode.model.User;
import com.tecode.model.UserExample;
import com.tecode.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    //新增数据
    @Override
    public void add(User user) {
        userMapper.insert(user);
    }
    //根据id查询user
    @Override
    public User findById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    //根据角色查询user
    @Override
    public List<User> findByRole(Integer pageNum,Integer pageSize,String role) {
        PageHelper.startPage(pageNum, pageSize);
        UserExample userExample=new UserExample();
        userExample.createCriteria().andRoleEqualTo(role);
        return userMapper.selectByExample(userExample);
    }
    //根据角色分页
    @Override
    public Long countPage(String role) {
        UserExample userExample=new UserExample();
        userExample.createCriteria().andRoleEqualTo(role);
        return userMapper.countByExample(userExample);
    }
    //根据username条件查询
    @Override
    public List<User> findByUsername(String username) {
        UserExample userExample=new UserExample();
        userExample.createCriteria().andNameEqualTo(username);
        return userMapper.selectByExample(userExample);
    }
    //修改user数据
    @Override
    public void updateById(User user) {
        userMapper.updateByPrimaryKey(user);
    }

    @Override
    public List<User> findByStatus(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        UserExample userExample=new UserExample();
        userExample.createCriteria().andStatusEqualTo("审核中");
        return userMapper.selectByExample(userExample);
    }

    @Override
    public Long countStatus(String status) {
        UserExample userExample=new UserExample();
        userExample.createCriteria().andStatusEqualTo(status);
        return userMapper.countByExample(userExample);
    }

    @Override
    public Long countByUsername(String username) {
        UserExample userExample=new UserExample();
        userExample.createCriteria().andNameLike("%"+username+"%");
        return userMapper.countByExample(userExample);
    }
}
