package com.tecode.service.serviceImpl;

import com.tecode.dao.ManagerMapper;
import com.tecode.model.Manager;
import com.tecode.model.ManagerExample;
import com.tecode.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerMapper managerMapper;

    @Override
    public List<Manager> selectCheck(String username, String password) {
        ManagerExample managerExample = new ManagerExample();
        managerExample.createCriteria().andUsernameEqualTo(username).andPasswordEqualTo(password);
        return managerMapper.selectByExample(managerExample);
    }

    @Override
    public List<Manager> selectCheckName(String username) {
        ManagerExample managerExample = new ManagerExample();
        managerExample.createCriteria().andUsernameEqualTo(username);
        return managerMapper.selectByExample(managerExample);
    }

    @Override
    public void updateAvater(Manager manager) {
        managerMapper.updateByPrimaryKey(manager);
    }

    @Override
    public Manager selectById(Integer id) {
        return managerMapper.selectByPrimaryKey(id);
    }
}
