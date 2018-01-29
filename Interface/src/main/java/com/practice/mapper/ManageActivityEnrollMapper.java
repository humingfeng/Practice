package com.practice.mapper;

import com.practice.po.ManageActivityEnroll;
import com.practice.po.ManageActivityEnrollExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ManageActivityEnrollMapper {
    long countByExample(ManageActivityEnrollExample example);

    int deleteByExample(ManageActivityEnrollExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ManageActivityEnroll record);

    int insertSelective(ManageActivityEnroll record);

    List<ManageActivityEnroll> selectByExample(ManageActivityEnrollExample example);

    ManageActivityEnroll selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ManageActivityEnroll record, @Param("example") ManageActivityEnrollExample example);

    int updateByExample(@Param("record") ManageActivityEnroll record, @Param("example") ManageActivityEnrollExample example);

    int updateByPrimaryKeySelective(ManageActivityEnroll record);

    int updateByPrimaryKey(ManageActivityEnroll record);

    List<ManageActivityEnroll> selectByActivityId(@Param("activityId") Long activityId);



}