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
    public boolean addChapter(int bid, String filename, String addr) {
        SetionTable setionTable = new SetionTable();
        setionTable.setBid(bid);
        setionTable.setChapter(filename);
        setionTable.setContent(addr);
        return setionTableMapper.insert(setionTable) > 0;
    }
}
