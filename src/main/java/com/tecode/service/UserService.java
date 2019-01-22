package com.tecode.service;

import com.tecode.model.Sign;
import com.tecode.model.User;

import java.util.List;

public interface UserService {

    void add(User o);//新增

    User findById(Integer id);//根据id查询

    List<User> findByRole(Integer pageNum, Integer pageSize, String role,String orders);//分页

    Long countPage(String role);//根据角色查询

    List<User> findByUsername(String username);//根据username查询
    List<User> findByUsername(String username,String orders);//根据username查询并排序

    void updateById(User user);//根据id修改user信息

    List<User> findByStatus(Integer pageNum, Integer pageSize,String orders);

    Long countStatus(String status);//根据审核状态查询

    Long countByUsername(String username);//模糊查询用户
    boolean applyAuthor(int id, String penname, String phone, String sex);//成为作者
    //修改性别
    User sexUpdate(int id, String sex);

    //修改手机
    User phoneUpdate(int id, String phone);
    //笔名验证
    boolean inspectPenName(String penname);

    //手机验证
    boolean inspectPhone(String phone);
    //按签到排序
    List<User> leaderboard();
    //根据笔名查询作者
    List<User> seleceByPenname(String penname);
    //签到查询
    List<Sign> findByUid(Integer uid);
    //签到
    void updateByUid(Sign sign);
    //新增sign
    void insert(Sign sign);
}
