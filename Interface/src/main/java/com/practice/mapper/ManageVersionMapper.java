package com.practice.mapper;

import com.practice.po.ManageVersion;
import com.practice.po.ManageVersionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ManageVersionMapper {
    long countByExample(ManageVersionExample example);

    int deleteByExample(ManageVersionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ManageVersion record);

    int insertSelective(ManageVersion record);

    List<ManageVersion> selectByExample(ManageVersionExample example);

    ManageVersion selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ManageVersion record, @Param("example") ManageVersionExample example);

    int updateByExample(@Param("record") ManageVersion record, @Param("example") ManageVersionExample example);

    int updateByPrimaryKeySelective(ManageVersion record);

    int updateByPrimaryKey(ManageVersion record);
}