package com.practice.mapper;

import com.practice.po.ManageActivityTaskItem;
import com.practice.po.ManageActivityTaskItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ManageActivityTaskItemMapper {
    long countByExample(ManageActivityTaskItemExample example);

    int deleteByExample(ManageActivityTaskItemExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ManageActivityTaskItem record);

    int insertSelective(ManageActivityTaskItem record);

    List<ManageActivityTaskItem> selectByExample(ManageActivityTaskItemExample example);

    ManageActivityTaskItem selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ManageActivityTaskItem record, @Param("example") ManageActivityTaskItemExample example);

    int updateByExample(@Param("record") ManageActivityTaskItem record, @Param("example") ManageActivityTaskItemExample example);

    int updateByPrimaryKeySelective(ManageActivityTaskItem record);

    int updateByPrimaryKey(ManageActivityTaskItem record);

    List<Long> selectQuestionIds(@Param("activityId") Long activityId, @Param("taskId") Long taskId);
}