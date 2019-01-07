package com.tecode.service.serviceImpl;

import com.tecode.dao.SetionTableMapper;
import com.tecode.model.SetionTable;
import com.tecode.service.SetionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetionServiceImpl implements SetionService {
    @Autowired
    private SetionTableMapper setionTableMapper;
    @Override
    public void updateSetion(SetionTable setionTable) {
        setionTableMapper.insert(setionTable);
    }
}
