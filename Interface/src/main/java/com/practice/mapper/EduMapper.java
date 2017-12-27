package com.practice.mapper;

import com.practice.po.Edu;
import com.practice.po.EduExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EduMapper {
    long countByExample(EduExample example);

    int deleteByExample(EduExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Edu record);

    int insertSelective(Edu record);

    List<Edu> selectByExample(EduExample example);

    Edu selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Edu record, @Param("example") EduExample example);

    int updateByExample(@Param("record") Edu record, @Param("example") EduExample example);

    int updateByPrimaryKeySelective(Edu record);

    int updateByPrimaryKey(Edu record);
}