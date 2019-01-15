package com.tecode.service;

import com.tecode.model.User;

import java.util.List;

public interface UserService {

    void add(User o);//新增

    User findById(Integer id);//根据id查询

    List<User> findByRole(Integer pageNum, Integer pageSize, String role);//分页

    Long countPage(String role);//根据角色查询

    List<User> findByUsername(String username);//根据username查询

    void updateById(User user);//根据id修改user信息

    List<User> findByStatus(Integer pageNum, Integer pageSize);

    Long countStatus(String status);//根据审核状态查询

    Long countByUsername(String username);//模糊查询用户
}
