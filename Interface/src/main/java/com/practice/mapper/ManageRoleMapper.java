package com.practice.mapper;

import com.practice.po.ManageRole;
import com.practice.po.ManageRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ManageRoleMapper {
    long countByExample(ManageRoleExample example);

    int deleteByExample(ManageRoleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ManageRole record);

    int insertSelective(ManageRole record);

    List<ManageRole> selectByExample(ManageRoleExample example);

    ManageRole selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ManageRole record, @Param("example") ManageRoleExample example);

    int updateByExample(@Param("record") ManageRole record, @Param("example") ManageRoleExample example);

    int updateByPrimaryKeySelective(ManageRole record);

    int updateByPrimaryKey(ManageRole record);
}