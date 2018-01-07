package com.practice.mapper;

import com.practice.po.ManageActivityQuestionOption;
import com.practice.po.ManageActivityQuestionOptionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ManageActivityQuestionOptionMapper {
    long countByExample(ManageActivityQuestionOptionExample example);

    int deleteByExample(ManageActivityQuestionOptionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ManageActivityQuestionOption record);

    int insertSelective(ManageActivityQuestionOption record);

    List<ManageActivityQuestionOption> selectByExample(ManageActivityQuestionOptionExample example);

    ManageActivityQuestionOption selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ManageActivityQuestionOption record, @Param("example") ManageActivityQuestionOptionExample example);

    int updateByExample(@Param("record") ManageActivityQuestionOption record, @Param("example") ManageActivityQuestionOptionExample example);

    int updateByPrimaryKeySelective(ManageActivityQuestionOption record);

    int updateByPrimaryKey(ManageActivityQuestionOption record);
}