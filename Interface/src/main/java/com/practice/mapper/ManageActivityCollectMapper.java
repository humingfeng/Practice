package com.practice.mapper;

import com.practice.po.ManageActivityCollect;
import com.practice.po.ManageActivityCollectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ManageActivityCollectMapper {
    long countByExample(ManageActivityCollectExample example);

    int deleteByExample(ManageActivityCollectExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ManageActivityCollect record);

    int insertSelective(ManageActivityCollect record);

    List<ManageActivityCollect> selectByExample(ManageActivityCollectExample example);

    ManageActivityCollect selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ManageActivityCollect record, @Param("example") ManageActivityCollectExample example);

    int updateByExample(@Param("record") ManageActivityCollect record, @Param("example") ManageActivityCollectExample example);

    int updateByPrimaryKeySelective(ManageActivityCollect record);

    int updateByPrimaryKey(ManageActivityCollect record);
}