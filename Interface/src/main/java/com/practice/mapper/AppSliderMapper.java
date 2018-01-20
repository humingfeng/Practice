package com.practice.mapper;

import com.practice.po.AppSlider;
import com.practice.po.AppSliderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AppSliderMapper {
    long countByExample(AppSliderExample example);

    int deleteByExample(AppSliderExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AppSlider record);

    int insertSelective(AppSlider record);

    List<AppSlider> selectByExample(AppSliderExample example);

    AppSlider selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AppSlider record, @Param("example") AppSliderExample example);

    int updateByExample(@Param("record") AppSlider record, @Param("example") AppSliderExample example);

    int updateByPrimaryKeySelective(AppSlider record);

    int updateByPrimaryKey(AppSlider record);
}