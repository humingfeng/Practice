package com.practice.po;

import java.io.Serializable;

/**
 *
 * @author Xushd  2017/12/22 21:04
 */
public class ManageRolePermission implements Serializable {
    private Long id;

    private Long roleId;

    private Long permissionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }
}