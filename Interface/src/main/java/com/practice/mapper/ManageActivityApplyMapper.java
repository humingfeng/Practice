package com.practice.mapper;

import com.practice.po.ManageActivityApply;
import com.practice.po.ManageActivityApplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ManageActivityApplyMapper {
    long countByExample(ManageActivityApplyExample example);

    int deleteByExample(ManageActivityApplyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ManageActivityApply record);

    int insertSelective(ManageActivityApply record);

    List<ManageActivityApply> selectByExample(ManageActivityApplyExample example);

    ManageActivityApply selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ManageActivityApply record, @Param("example") ManageActivityApplyExample example);

    int updateByExample(@Param("record") ManageActivityApply record, @Param("example") ManageActivityApplyExample example);

    int updateByPrimaryKeySelective(ManageActivityApply record);

    int updateByPrimaryKey(ManageActivityApply record);

    List<ManageActivityApply> selectByActivityId(@Param("activityId")Long activityId);
}