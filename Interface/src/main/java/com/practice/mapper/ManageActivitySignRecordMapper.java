package com.practice.mapper;

import com.practice.po.ManageActivitySignRecord;
import com.practice.po.ManageActivitySignRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ManageActivitySignRecordMapper {
    long countByExample(ManageActivitySignRecordExample example);

    int deleteByExample(ManageActivitySignRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ManageActivitySignRecord record);

    int insertSelective(ManageActivitySignRecord record);

    List<ManageActivitySignRecord> selectByExample(ManageActivitySignRecordExample example);

    ManageActivitySignRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ManageActivitySignRecord record, @Param("example") ManageActivitySignRecordExample example);

    int updateByExample(@Param("record") ManageActivitySignRecord record, @Param("example") ManageActivitySignRecordExample example);

    int updateByPrimaryKeySelective(ManageActivitySignRecord record);

    int updateByPrimaryKey(ManageActivitySignRecord record);
}