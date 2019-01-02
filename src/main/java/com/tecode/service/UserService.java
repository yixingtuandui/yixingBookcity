package com.tecode.service;

import com.tecode.model.User;

import java.util.List;

public interface UserService<T> {
    void add(User o);//新增
    T findById(Integer id);//根据id查询
    List<User> findByRole(Integer pageNum, Integer pageSize, String role);//分页
    Long countPage(String role);//根据角色查询
    T findByUsername(String username);//根据username查询
    void updateById(User user);
}
