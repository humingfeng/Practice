package com.practice.mapper;

import com.practice.po.ManageActivityIntroduce;
import com.practice.po.ManageActivityIntroduceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ManageActivityIntroduceMapper {
    long countByExample(ManageActivityIntroduceExample example);

    int deleteByExample(ManageActivityIntroduceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ManageActivityIntroduce record);

    int insertSelective(ManageActivityIntroduce record);

    List<ManageActivityIntroduce> selectByExample(ManageActivityIntroduceExample example);

    ManageActivityIntroduce selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ManageActivityIntroduce record, @Param("example") ManageActivityIntroduceExample example);

    int updateByExample(@Param("record") ManageActivityIntroduce record, @Param("example") ManageActivityIntroduceExample example);

    int updateByPrimaryKeySelective(ManageActivityIntroduce record);

    int updateByPrimaryKey(ManageActivityIntroduce record);

    List<ManageActivityIntroduce> selectByActivityId(@Param("activityId") Long activityId);
}