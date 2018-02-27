package com.practice.mapper;

import com.practice.po.ManageUserApp;
import com.practice.po.ManageUserAppExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ManageUserAppMapper {
    long countByExample(ManageUserAppExample example);

    int deleteByExample(ManageUserAppExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ManageUserApp record);

    int insertSelective(ManageUserApp record);

    List<ManageUserApp> selectByExample(ManageUserAppExample example);

    ManageUserApp selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ManageUserApp record, @Param("example") ManageUserAppExample example);

    int updateByExample(@Param("record") ManageUserApp record, @Param("example") ManageUserAppExample example);

    int updateByPrimaryKeySelective(ManageUserApp record);

    int updateByPrimaryKey(ManageUserApp record);
}