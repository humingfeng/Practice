package com.practice.dto;

import java.io.Serializable;

/**
 * Token User Info
 * @author Xushd  2017/12/22 0:07
 */
public class TokenUserDTO implements Serializable{

    private Long id;

    private Long roleId;

    private String nickName;

    public TokenUserDTO() {
    }

    public TokenUserDTO(Long id, Long roleId, String nickName) {
        this.id = id;
        this.roleId = roleId;
        this.nickName = nickName;
    }

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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
