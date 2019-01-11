package com.tecode.service;

import com.tecode.model.Manager;

import java.util.List;

public interface ManagerService {
    List<Manager> selectCheck(String username,String password);
    List<Manager> selectCheckName(String username);
    void updateAvater(Manager manager);
    Manager selectById(Integer id);
}
