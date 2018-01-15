package com.practice.mapper;

import com.practice.po.ManageActivityLeader;
import com.practice.po.ManageActivityLeaderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ManageActivityLeaderMapper {
    long countByExample(ManageActivityLeaderExample example);

    int deleteByExample(ManageActivityLeaderExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ManageActivityLeader record);

    int insertSelective(ManageActivityLeader record);

    List<ManageActivityLeader> selectByExample(ManageActivityLeaderExample example);

    ManageActivityLeader selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ManageActivityLeader record, @Param("example") ManageActivityLeaderExample example);

    int updateByExample(@Param("record") ManageActivityLeader record, @Param("example") ManageActivityLeaderExample example);

    int updateByPrimaryKeySelective(ManageActivityLeader record);

    int updateByPrimaryKey(ManageActivityLeader record);

    List<ManageActivityLeader> selectByActivityId(@Param("activityId") Long activityId);
}