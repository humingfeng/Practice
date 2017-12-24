package com.practice.mapper;

import com.practice.po.ManageNav;
import com.practice.po.ManageNavExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ManageNavMapper {
    long countByExample(ManageNavExample example);

    int deleteByExample(ManageNavExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ManageNav record);

    int insertSelective(ManageNav record);

    List<ManageNav> selectByExample(ManageNavExample example);

    ManageNav selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ManageNav record, @Param("example") ManageNavExample example);

    int updateByExample(@Param("record") ManageNav record, @Param("example") ManageNavExample example);

    int updateByPrimaryKeySelective(ManageNav record);

    int updateByPrimaryKey(ManageNav record);
}