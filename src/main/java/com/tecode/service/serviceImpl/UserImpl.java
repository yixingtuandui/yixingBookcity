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
    public List<User> findByRole(Integer pageNum, Integer pageSize, String role,String orders) {
        PageHelper.startPage(pageNum, pageSize);
        UserExample userExample = new UserExample();
        userExample.createCriteria().andRoleEqualTo(role);
        userExample.setOrderByClause(orders);
        return userMapper.selectByExample(userExample);
    }

    //根据角色分页
    @Override
    public Long countPage(String role) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andRoleEqualTo(role);
        return userMapper.countByExample(userExample);
    }

    //根据username条件查询
    @Override
    public List<User> findByUsername(String username) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andNameEqualTo(username);
        return userMapper.selectByExample(userExample);
    }

    @Override
    public List<User> findByUsername(String username, String orders) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andNameLike("%"+username+"%");
        userExample.setOrderByClause(orders);
        return userMapper.selectByExample(userExample);
    }

    //修改user数据
    @Override
    public void updateById(User user) {
        userMapper.updateByPrimaryKey(user);
    }

    @Override
    public List<User> findByStatus(Integer pageNum, Integer pageSize,String orders) {
        PageHelper.startPage(pageNum, pageSize);
        UserExample userExample = new UserExample();
        userExample.createCriteria().andStatusEqualTo("审核中");
        userExample.setOrderByClause(orders);
        return userMapper.selectByExample(userExample);
    }

    @Override
    public Long countStatus(String status) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andStatusEqualTo(status);
        return userMapper.countByExample(userExample);
    }

    @Override
    public Long countByUsername(String username) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andNameLike("%" + username + "%");
        return userMapper.countByExample(userExample);
    }
    @Override//成为作者
    public boolean applyAuthor(int id, String penname, String phone, String sex) {
        User user = findById(id);
        user.setPenName(penname);
        user.setPhone(phone);
        user.setSex(sex);
        user.setStatus("审核中");
        return userMapper.updateByPrimaryKey(user) > 0;
    }

    @Override//性别修改
    public User sexUpdate(int id, String sex) {
        User user = findById(id);
        user.setSex(sex);
        if (userMapper.updateByPrimaryKey(user) > 0) {
            return findById(id);
        }
        return null;
    }

    @Override//手机修改
    public User phoneUpdate(int id, String phone) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andPhoneEqualTo(phone);
        if (!(userMapper.selectByExample(userExample).size() > 0)) {
            User user = findById(id);
            user.setPhone(phone);
            if (userMapper.updateByPrimaryKey(user) > 0) {
                return findById(id);
            }
        }
        return null;
    }

    @Override//验证别名
    public boolean inspectPenName(String penname) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andPenNameEqualTo(penname);
        return !(userMapper.selectByExample(userExample).size() > 0);
    }

    @Override//验证手机
    public boolean inspectPhone(String phone) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andPhoneEqualTo(phone);
        return !(userMapper.selectByExample(userExample).size() > 0);
    }
    //按签到排序
    @Override
    public List<User> leaderboard() {
        //System.out.println("545");
        UserExample userExample = new UserExample();
        PageHelper.startPage(1, 50);
        userExample.setOrderByClause("day DESC");
        return userMapper.selectByExample(userExample);
    }

}
