package com.practice.mapper;

import com.practice.po.ManageActivityQuestion;
import com.practice.po.ManageActivityQuestionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ManageActivityQuestionMapper {
    long countByExample(ManageActivityQuestionExample example);

    int deleteByExample(ManageActivityQuestionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ManageActivityQuestion record);

    int insertSelective(ManageActivityQuestion record);

    List<ManageActivityQuestion> selectByExample(ManageActivityQuestionExample example);

    ManageActivityQuestion selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ManageActivityQuestion record, @Param("example") ManageActivityQuestionExample example);

    int updateByExample(@Param("record") ManageActivityQuestion record, @Param("example") ManageActivityQuestionExample example);

    int updateByPrimaryKeySelective(ManageActivityQuestion record);

    int updateByPrimaryKey(ManageActivityQuestion record);
}