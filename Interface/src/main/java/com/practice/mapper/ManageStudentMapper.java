package com.practice.mapper;

import com.practice.po.ManageStudent;
import com.practice.po.ManageStudentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ManageStudentMapper {
    long countByExample(ManageStudentExample example);

    int deleteByExample(ManageStudentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ManageStudent record);

    int insertSelective(ManageStudent record);

    List<ManageStudent> selectByExample(ManageStudentExample example);

    ManageStudent selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ManageStudent record, @Param("example") ManageStudentExample example);

    int updateByExample(@Param("record") ManageStudent record, @Param("example") ManageStudentExample example);

    int updateByPrimaryKeySelective(ManageStudent record);

    int updateByPrimaryKey(ManageStudent record);
}