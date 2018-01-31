package com.practice.mapper;

import com.practice.po.ParentActivityLink;
import com.practice.po.ParentActivityLinkExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ParentActivityLinkMapper {
    long countByExample(ParentActivityLinkExample example);

    int deleteByExample(ParentActivityLinkExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ParentActivityLink record);

    int insertSelective(ParentActivityLink record);

    List<ParentActivityLink> selectByExample(ParentActivityLinkExample example);

    ParentActivityLink selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ParentActivityLink record, @Param("example") ParentActivityLinkExample example);

    int updateByExample(@Param("record") ParentActivityLink record, @Param("example") ParentActivityLinkExample example);

    int updateByPrimaryKeySelective(ParentActivityLink record);

    int updateByPrimaryKey(ParentActivityLink record);
}