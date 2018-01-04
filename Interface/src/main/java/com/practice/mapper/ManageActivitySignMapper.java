package com.practice.mapper;

import com.practice.po.ManageActivitySign;
import com.practice.po.ManageActivitySignExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ManageActivitySignMapper {
    long countByExample(ManageActivitySignExample example);

    int deleteByExample(ManageActivitySignExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ManageActivitySign record);

    int insertSelective(ManageActivitySign record);

    List<ManageActivitySign> selectByExample(ManageActivitySignExample example);

    ManageActivitySign selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ManageActivitySign record, @Param("example") ManageActivitySignExample example);

    int updateByExample(@Param("record") ManageActivitySign record, @Param("example") ManageActivitySignExample example);

    int updateByPrimaryKeySelective(ManageActivitySign record);

    int updateByPrimaryKey(ManageActivitySign record);
}