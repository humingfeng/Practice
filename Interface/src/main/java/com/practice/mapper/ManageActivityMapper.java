package com.practice.mapper;

import com.practice.po.ManageActivity;
import com.practice.po.ManageActivityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ManageActivityMapper {
    long countByExample(ManageActivityExample example);

    int deleteByExample(ManageActivityExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ManageActivity record);

    int insertSelective(ManageActivity record);

    List<ManageActivity> selectByExample(ManageActivityExample example);

    ManageActivity selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ManageActivity record, @Param("example") ManageActivityExample example);

    int updateByExample(@Param("record") ManageActivity record, @Param("example") ManageActivityExample example);

    int updateByPrimaryKeySelective(ManageActivity record);

    int updateByPrimaryKey(ManageActivity record);
}