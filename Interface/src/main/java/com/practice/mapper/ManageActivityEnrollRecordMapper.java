package com.practice.mapper;

import com.practice.po.ManageActivityEnrollRecord;
import com.practice.po.ManageActivityEnrollRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ManageActivityEnrollRecordMapper {
    long countByExample(ManageActivityEnrollRecordExample example);

    int deleteByExample(ManageActivityEnrollRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ManageActivityEnrollRecord record);

    int insertSelective(ManageActivityEnrollRecord record);

    List<ManageActivityEnrollRecord> selectByExample(ManageActivityEnrollRecordExample example);

    ManageActivityEnrollRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ManageActivityEnrollRecord record, @Param("example") ManageActivityEnrollRecordExample example);

    int updateByExample(@Param("record") ManageActivityEnrollRecord record, @Param("example") ManageActivityEnrollRecordExample example);

    int updateByPrimaryKeySelective(ManageActivityEnrollRecord record);

    int updateByPrimaryKey(ManageActivityEnrollRecord record);

    long getEnrolledCount(@Param("activityId")Long activityId);
}