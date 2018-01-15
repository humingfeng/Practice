package com.practice.mapper;

import com.practice.po.ManageActivityTask;
import com.practice.po.ManageActivityTaskExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ManageActivityTaskMapper {
    long countByExample(ManageActivityTaskExample example);

    int deleteByExample(ManageActivityTaskExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ManageActivityTask record);

    int insertSelective(ManageActivityTask record);

    List<ManageActivityTask> selectByExample(ManageActivityTaskExample example);

    ManageActivityTask selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ManageActivityTask record, @Param("example") ManageActivityTaskExample example);

    int updateByExample(@Param("record") ManageActivityTask record, @Param("example") ManageActivityTaskExample example);

    int updateByPrimaryKeySelective(ManageActivityTask record);

    int updateByPrimaryKey(ManageActivityTask record);

    List<ManageActivityTask> selectByActivityId(@Param("activityId") Long activityId);
}