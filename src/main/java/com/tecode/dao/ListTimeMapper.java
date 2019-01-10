package com.tecode.dao;

import com.tecode.model.ListTime;
import com.tecode.model.ListTimeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ListTimeMapper {
    long countByExample(ListTimeExample example);

    int deleteByExample(ListTimeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ListTime record);

    int insertSelective(ListTime record);

    List<ListTime> selectByExample(ListTimeExample example);

    ListTime selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ListTime record, @Param("example") ListTimeExample example);

    int updateByExample(@Param("record") ListTime record, @Param("example") ListTimeExample example);

    int updateByPrimaryKeySelective(ListTime record);

    int updateByPrimaryKey(ListTime record);
}