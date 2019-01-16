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
    //管理员登录核实
    @Override
    public List<Manager> selectCheck(String username, String password) {
        ManagerExample managerExample = new ManagerExample();
        managerExample.createCriteria().andUsernameEqualTo(username).andPasswordEqualTo(password);
        return managerMapper.selectByExample(managerExample);
    }
    //管理员账号核实
    @Override
    public List<Manager> selectCheckName(String username) {
        ManagerExample managerExample = new ManagerExample();
        managerExample.createCriteria().andUsernameEqualTo(username);
        return managerMapper.selectByExample(managerExample);
    }
    //管理员修改头像
    @Override
    public void updateAvater(Manager manager) {
        managerMapper.updateByPrimaryKey(manager);
    }
    //根据id查询管理员
    @Override
    public Manager selectById(Integer id) {
        return managerMapper.selectByPrimaryKey(id);
    }
}
