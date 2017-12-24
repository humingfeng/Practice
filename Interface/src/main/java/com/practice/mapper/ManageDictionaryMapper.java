package com.practice.mapper;

import com.practice.po.ManageDictionary;
import com.practice.po.ManageDictionaryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ManageDictionaryMapper {
    long countByExample(ManageDictionaryExample example);

    int deleteByExample(ManageDictionaryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ManageDictionary record);

    int insertSelective(ManageDictionary record);

    List<ManageDictionary> selectByExample(ManageDictionaryExample example);

    ManageDictionary selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ManageDictionary record, @Param("example") ManageDictionaryExample example);

    int updateByExample(@Param("record") ManageDictionary record, @Param("example") ManageDictionaryExample example);

    int updateByPrimaryKeySelective(ManageDictionary record);

    int updateByPrimaryKey(ManageDictionary record);

    List<ManageDictionary> selectByParentId(Long dicParentEnumId);
}