package com.practice.mapper;

import com.practice.po.ManageActivityAttention;
import com.practice.po.ManageActivityAttentionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ManageActivityAttentionMapper {
    long countByExample(ManageActivityAttentionExample example);

    int deleteByExample(ManageActivityAttentionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ManageActivityAttention record);

    int insertSelective(ManageActivityAttention record);

    List<ManageActivityAttention> selectByExample(ManageActivityAttentionExample example);

    ManageActivityAttention selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ManageActivityAttention record, @Param("example") ManageActivityAttentionExample example);

    int updateByExample(@Param("record") ManageActivityAttention record, @Param("example") ManageActivityAttentionExample example);

    int updateByPrimaryKeySelective(ManageActivityAttention record);

    int updateByPrimaryKey(ManageActivityAttention record);

    List<ManageActivityAttention> selectByActivityId(@Param("activityId") Long activityId);
}