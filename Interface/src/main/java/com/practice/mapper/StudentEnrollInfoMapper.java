package com.practice.mapper;

import com.practice.po.StudentEnrollInfo;
import com.practice.po.StudentEnrollInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StudentEnrollInfoMapper {
    long countByExample(StudentEnrollInfoExample example);

    int deleteByExample(StudentEnrollInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(StudentEnrollInfo record);

    int insertSelective(StudentEnrollInfo record);

    List<StudentEnrollInfo> selectByExample(StudentEnrollInfoExample example);

    StudentEnrollInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") StudentEnrollInfo record, @Param("example") StudentEnrollInfoExample example);

    int updateByExample(@Param("record") StudentEnrollInfo record, @Param("example") StudentEnrollInfoExample example);

    int updateByPrimaryKeySelective(StudentEnrollInfo record);

    int updateByPrimaryKey(StudentEnrollInfo record);
}