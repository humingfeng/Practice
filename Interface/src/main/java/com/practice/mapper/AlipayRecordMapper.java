package com.practice.mapper;

import com.practice.po.AlipayRecord;
import com.practice.po.AlipayRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AlipayRecordMapper {
    long countByExample(AlipayRecordExample example);

    int deleteByExample(AlipayRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AlipayRecord record);

    int insertSelective(AlipayRecord record);

    List<AlipayRecord> selectByExample(AlipayRecordExample example);

    AlipayRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AlipayRecord record, @Param("example") AlipayRecordExample example);

    int updateByExample(@Param("record") AlipayRecord record, @Param("example") AlipayRecordExample example);

    int updateByPrimaryKeySelective(AlipayRecord record);

    int updateByPrimaryKey(AlipayRecord record);
}