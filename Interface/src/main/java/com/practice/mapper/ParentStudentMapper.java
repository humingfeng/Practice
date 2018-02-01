package com.practice.mapper;

import com.practice.po.ParentStudent;
import com.practice.po.ParentStudentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ParentStudentMapper {
    long countByExample(ParentStudentExample example);

    int deleteByExample(ParentStudentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ParentStudent record);

    int insertSelective(ParentStudent record);

    List<ParentStudent> selectByExample(ParentStudentExample example);

    ParentStudent selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ParentStudent record, @Param("example") ParentStudentExample example);

    int updateByExample(@Param("record") ParentStudent record, @Param("example") ParentStudentExample example);

    int updateByPrimaryKeySelective(ParentStudent record);

    int updateByPrimaryKey(ParentStudent record);
}