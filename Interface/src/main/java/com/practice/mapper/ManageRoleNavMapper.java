package com.practice.mapper;

import com.practice.po.ManageRoleNav;
import com.practice.po.ManageRoleNavExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ManageRoleNavMapper {
    long countByExample(ManageRoleNavExample example);

    int deleteByExample(ManageRoleNavExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ManageRoleNav record);

    int insertSelective(ManageRoleNav record);

    List<ManageRoleNav> selectByExample(ManageRoleNavExample example);

    ManageRoleNav selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ManageRoleNav record, @Param("example") ManageRoleNavExample example);

    int updateByExample(@Param("record") ManageRoleNav record, @Param("example") ManageRoleNavExample example);

    int updateByPrimaryKeySelective(ManageRoleNav record);

    int updateByPrimaryKey(ManageRoleNav record);

    List<Long> selectByRoleId(Long id);

    int deleteByRoleId(Long id);
}