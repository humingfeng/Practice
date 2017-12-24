package com.practice.mapper;

import com.practice.po.ManagePermission;
import com.practice.po.ManagePermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ManagePermissionMapper {
    long countByExample(ManagePermissionExample example);

    int deleteByExample(ManagePermissionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ManagePermission record);

    int insertSelective(ManagePermission record);

    List<ManagePermission> selectByExample(ManagePermissionExample example);

    ManagePermission selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ManagePermission record, @Param("example") ManagePermissionExample example);

    int updateByExample(@Param("record") ManagePermission record, @Param("example") ManagePermissionExample example);

    int updateByPrimaryKeySelective(ManagePermission record);

    int updateByPrimaryKey(ManagePermission record);
}