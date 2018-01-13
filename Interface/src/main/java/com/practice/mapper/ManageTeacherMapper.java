package com.practice.mapper;

import com.practice.po.ManageTeacher;
import com.practice.po.ManageTeacherExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ManageTeacherMapper {
    long countByExample(ManageTeacherExample example);

    int deleteByExample(ManageTeacherExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ManageTeacher record);

    int insertSelective(ManageTeacher record);

    List<ManageTeacher> selectByExample(ManageTeacherExample example);

    ManageTeacher selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ManageTeacher record, @Param("example") ManageTeacherExample example);

    int updateByExample(@Param("record") ManageTeacher record, @Param("example") ManageTeacherExample example);

    int updateByPrimaryKeySelective(ManageTeacher record);

    int updateByPrimaryKey(ManageTeacher record);
}