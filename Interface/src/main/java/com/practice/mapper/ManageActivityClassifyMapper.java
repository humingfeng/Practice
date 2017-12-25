package com.practice.mapper;

import com.practice.po.ManageActivityClassify;
import com.practice.po.ManageActivityClassifyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ManageActivityClassifyMapper {
    long countByExample(ManageActivityClassifyExample example);

    int deleteByExample(ManageActivityClassifyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ManageActivityClassify record);

    int insertSelective(ManageActivityClassify record);

    List<ManageActivityClassify> selectByExample(ManageActivityClassifyExample example);

    ManageActivityClassify selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ManageActivityClassify record, @Param("example") ManageActivityClassifyExample example);

    int updateByExample(@Param("record") ManageActivityClassify record, @Param("example") ManageActivityClassifyExample example);

    int updateByPrimaryKeySelective(ManageActivityClassify record);

    int updateByPrimaryKey(ManageActivityClassify record);
}