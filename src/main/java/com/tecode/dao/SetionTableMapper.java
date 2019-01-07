package com.tecode.dao;

import com.tecode.model.SetionTable;
import com.tecode.model.SetionTableExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SetionTableMapper {
    long countByExample(SetionTableExample example);

    int deleteByExample(SetionTableExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SetionTable record);

    int insertSelective(SetionTable record);

    List<SetionTable> selectByExample(SetionTableExample example);

    SetionTable selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SetionTable record, @Param("example") SetionTableExample example);

    int updateByExample(@Param("record") SetionTable record, @Param("example") SetionTableExample example);

    int updateByPrimaryKeySelective(SetionTable record);

    int updateByPrimaryKey(SetionTable record);
}