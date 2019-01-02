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
    private UserExample userExample=new UserExample();
    //新增数据
    @Override
    public void add(User user) {
        userMapper.insert(user);
    }
    //根据id查询user
    @Override
    public Object findById(Integer id) {

        return userMapper.selectByPrimaryKey(id);
    }

    //根据角色查询user
    @Override
    public List<User> findByRole(Integer pageNum,Integer pageSize,String role) {
        PageHelper.startPage(pageNum, pageSize);
        userExample.createCriteria().andRoleEqualTo(role);
        return userMapper.selectByExample(userExample);
    }
    //根据角色分页
    @Override
    public Long countPage(String role) {
        userExample.createCriteria().andRoleEqualTo(role);
        return userMapper.countByExample(userExample);
    }
    //根据username条件查询
    @Override
    public Object findByUsername(String username) {
        userExample.createCriteria().andNameEqualTo(username);
        return userMapper.selectByExample(userExample);
    }
    //修改user数据
    @Override
    public void updateById(User user) {
        userMapper.updateByPrimaryKey(user);
    }
}
