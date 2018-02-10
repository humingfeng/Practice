package com.practice.mapper;

import com.practice.po.ActivityMessage;
import com.practice.po.ActivityMessageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActivityMessageMapper {
    long countByExample(ActivityMessageExample example);

    int deleteByExample(ActivityMessageExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ActivityMessage record);

    int insertSelective(ActivityMessage record);

    List<ActivityMessage> selectByExample(ActivityMessageExample example);

    ActivityMessage selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ActivityMessage record, @Param("example") ActivityMessageExample example);

    int updateByExample(@Param("record") ActivityMessage record, @Param("example") ActivityMessageExample example);

    int updateByPrimaryKeySelective(ActivityMessage record);

    int updateByPrimaryKey(ActivityMessage record);
}