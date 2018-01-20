package com.practice.dto;

import java.io.Serializable;

/**
 * @author Xushd  2018/1/18 21:41
 */
public class TokenParentDTO implements Serializable{

    private Long id;

    private Long phone;

    private String name;

    private Long studentId;

    public TokenParentDTO() {
    }

    public TokenParentDTO(Long id, Long phone, String name, Long studentId) {
        this.id = id;
        this.phone = phone;
        this.name = name;
        this.studentId = studentId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
}
