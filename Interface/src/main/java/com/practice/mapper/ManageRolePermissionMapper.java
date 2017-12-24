package com.practice.mapper;

import com.practice.po.ManageRolePermission;
import com.practice.po.ManageRolePermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ManageRolePermissionMapper {
    long countByExample(ManageRolePermissionExample example);

    int deleteByExample(ManageRolePermissionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ManageRolePermission record);

    int insertSelective(ManageRolePermission record);

    List<ManageRolePermission> selectByExample(ManageRolePermissionExample example);

    ManageRolePermission selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ManageRolePermission record, @Param("example") ManageRolePermissionExample example);

    int updateByExample(@Param("record") ManageRolePermission record, @Param("example") ManageRolePermissionExample example);

    int updateByPrimaryKeySelective(ManageRolePermission record);

    int updateByPrimaryKey(ManageRolePermission record);

    List<Long> selectByRoleId(Long id);

    int deleteByRoleId(Long id);
}