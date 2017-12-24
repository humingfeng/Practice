package com.practice.mapper;

import com.practice.po.ManageUserRole;
import com.practice.po.ManageUserRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ManageUserRoleMapper {
    long countByExample(ManageUserRoleExample example);

    int deleteByExample(ManageUserRoleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ManageUserRole record);

    int insertSelective(ManageUserRole record);

    List<ManageUserRole> selectByExample(ManageUserRoleExample example);

    ManageUserRole selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ManageUserRole record, @Param("example") ManageUserRoleExample example);

    int updateByExample(@Param("record") ManageUserRole record, @Param("example") ManageUserRoleExample example);

    int updateByPrimaryKeySelective(ManageUserRole record);

    int updateByPrimaryKey(ManageUserRole record);

    List<Long> selectByUserId(Long userId);

    int deleteByUserId(Long id);
}