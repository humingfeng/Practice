package com.practice.mapper;

import com.practice.po.ManageActivitySupervise;
import com.practice.po.ManageActivitySuperviseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ManageActivitySuperviseMapper {
    long countByExample(ManageActivitySuperviseExample example);

    int deleteByExample(ManageActivitySuperviseExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ManageActivitySupervise record);

    int insertSelective(ManageActivitySupervise record);

    List<ManageActivitySupervise> selectByExample(ManageActivitySuperviseExample example);

    ManageActivitySupervise selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ManageActivitySupervise record, @Param("example") ManageActivitySuperviseExample example);

    int updateByExample(@Param("record") ManageActivitySupervise record, @Param("example") ManageActivitySuperviseExample example);

    int updateByPrimaryKeySelective(ManageActivitySupervise record);

    int updateByPrimaryKey(ManageActivitySupervise record);

    List<ManageActivitySupervise> selectByActivityId(@Param("activityId") Long activityId);
}