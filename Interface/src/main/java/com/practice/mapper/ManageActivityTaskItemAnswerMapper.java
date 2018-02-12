package com.practice.mapper;

import com.practice.po.ManageActivityTaskItemAnswer;
import com.practice.po.ManageActivityTaskItemAnswerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ManageActivityTaskItemAnswerMapper {
    long countByExample(ManageActivityTaskItemAnswerExample example);

    int deleteByExample(ManageActivityTaskItemAnswerExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ManageActivityTaskItemAnswer record);

    int insertSelective(ManageActivityTaskItemAnswer record);

    List<ManageActivityTaskItemAnswer> selectByExample(ManageActivityTaskItemAnswerExample example);

    ManageActivityTaskItemAnswer selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ManageActivityTaskItemAnswer record, @Param("example") ManageActivityTaskItemAnswerExample example);

    int updateByExample(@Param("record") ManageActivityTaskItemAnswer record, @Param("example") ManageActivityTaskItemAnswerExample example);

    int updateByPrimaryKeySelective(ManageActivityTaskItemAnswer record);

    int updateByPrimaryKey(ManageActivityTaskItemAnswer record);
}