package com.practice.mapper;

import com.practice.po.ManageBase;
import com.practice.po.ManageBaseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ManageBaseMapper {
    long countByExample(ManageBaseExample example);

    int deleteByExample(ManageBaseExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ManageBase record);

    int insertSelective(ManageBase record);

    List<ManageBase> selectByExample(ManageBaseExample example);

    ManageBase selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ManageBase record, @Param("example") ManageBaseExample example);

    int updateByExample(@Param("record") ManageBase record, @Param("example") ManageBaseExample example);

    int updateByPrimaryKeySelective(ManageBase record);

    int updateByPrimaryKey(ManageBase record);
}