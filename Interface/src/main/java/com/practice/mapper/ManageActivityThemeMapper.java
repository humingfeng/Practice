package com.practice.mapper;

import com.practice.po.ManageActivityTheme;
import com.practice.po.ManageActivityThemeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ManageActivityThemeMapper {
    long countByExample(ManageActivityThemeExample example);

    int deleteByExample(ManageActivityThemeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ManageActivityTheme record);

    int insertSelective(ManageActivityTheme record);

    List<ManageActivityTheme> selectByExample(ManageActivityThemeExample example);

    ManageActivityTheme selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ManageActivityTheme record, @Param("example") ManageActivityThemeExample example);

    int updateByExample(@Param("record") ManageActivityTheme record, @Param("example") ManageActivityThemeExample example);

    int updateByPrimaryKeySelective(ManageActivityTheme record);

    int updateByPrimaryKey(ManageActivityTheme record);
}