package com.tecode.service.serviceImpl;

import com.tecode.dao.SetionTableMapper;
import com.tecode.model.SetionTable;
import com.tecode.model.SetionTableExample;
import com.tecode.service.SetionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetionServiceImpl implements SetionService {

    @Autowired
    private SetionTableMapper setionTableMapper;

    @Override//作者添加章节信息
    public boolean addChapter(int bid, String filename, String addr) {
        SetionTable setionTable = new SetionTable();
        setionTable.setBid(bid);
        setionTable.setChapter(filename);
        setionTable.setContent(addr);
        return setionTableMapper.insert(setionTable) > 0;
    }
    @Override//作者书籍章节删除
    public boolean chapterRemove(int id) {
        SetionTableExample setionTableExample = new SetionTableExample();
        setionTableExample.createCriteria().andBidEqualTo(id);
        return setionTableMapper.deleteByExample(setionTableExample) > 0;
    }
}
