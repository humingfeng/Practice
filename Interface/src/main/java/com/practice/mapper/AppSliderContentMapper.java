package com.practice.mapper;

import com.practice.po.AppSliderContent;
import com.practice.po.AppSliderContentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AppSliderContentMapper {
    long countByExample(AppSliderContentExample example);

    int deleteByExample(AppSliderContentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AppSliderContent record);

    int insertSelective(AppSliderContent record);

    List<AppSliderContent> selectByExample(AppSliderContentExample example);

    AppSliderContent selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AppSliderContent record, @Param("example") AppSliderContentExample example);

    int updateByExample(@Param("record") AppSliderContent record, @Param("example") AppSliderContentExample example);

    int updateByPrimaryKeySelective(AppSliderContent record);

    int updateByPrimaryKey(AppSliderContent record);
}