package com.practice.dto;

import java.io.Serializable;

/**
 * @author Xushd  2018/2/27 21:57
 */
public class TokenTeacherManageDTO implements Serializable{

    private Long id;

    private Long phone;

    private Long organizeId;

    private String name;

    public TokenTeacherManageDTO() {
    }

    public TokenTeacherManageDTO(Long id, Long phone, Long organizeId, String name) {
        this.id = id;
        this.phone = phone;
        this.organizeId = organizeId;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public Long getOrganizeId() {
        return organizeId;
    }

    public void setOrganizeId(Long organizeId) {
        this.organizeId = organizeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
