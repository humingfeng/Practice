package com.practice.mapper;

import com.practice.po.ManageActivityType;
import com.practice.po.ManageActivityTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ManageActivityTypeMapper {
    long countByExample(ManageActivityTypeExample example);

    int deleteByExample(ManageActivityTypeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ManageActivityType record);

    int insertSelective(ManageActivityType record);

    List<ManageActivityType> selectByExample(ManageActivityTypeExample example);

    ManageActivityType selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ManageActivityType record, @Param("example") ManageActivityTypeExample example);

    int updateByExample(@Param("record") ManageActivityType record, @Param("example") ManageActivityTypeExample example);

    int updateByPrimaryKeySelective(ManageActivityType record);

    int updateByPrimaryKey(ManageActivityType record);
}