package com.practice.po;

import java.io.Serializable;

/**
 *
 * @author Xushd  2017/12/24 16:16
 */
public class ManageRoleNav implements Serializable{
    private Long id;

    private Long roleId;

    private Long navId;

    private Long navPid;

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

    public Long getNavId() {
        return navId;
    }

    public void setNavId(Long navId) {
        this.navId = navId;
    }

    public Long getNavPid() {
        return navPid;
    }

    public void setNavPid(Long navPid) {
        this.navPid = navPid;
    }
}