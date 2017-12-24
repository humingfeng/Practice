package com.practice.mapper;

import com.practice.po.ManageVersionItem;
import com.practice.po.ManageVersionItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ManageVersionItemMapper {
    long countByExample(ManageVersionItemExample example);

    int deleteByExample(ManageVersionItemExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ManageVersionItem record);

    int insertSelective(ManageVersionItem record);

    List<ManageVersionItem> selectByExample(ManageVersionItemExample example);

    ManageVersionItem selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ManageVersionItem record, @Param("example") ManageVersionItemExample example);

    int updateByExample(@Param("record") ManageVersionItem record, @Param("example") ManageVersionItemExample example);

    int updateByPrimaryKeySelective(ManageVersionItem record);

    int updateByPrimaryKey(ManageVersionItem record);
}