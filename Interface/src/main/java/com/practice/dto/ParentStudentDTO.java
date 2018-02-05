package com.practice.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author Xushd on 2018/2/5 16:52
 */
public class ParentStudentDTO implements Serializable {

    private String parentName;

    private Long parentId;

    private Long phone;

    List<ParentStudentItemDTO> list;


    public ParentStudentDTO() {
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public List<ParentStudentItemDTO> getList() {
        return list;
    }

    public void setList(List<ParentStudentItemDTO> list) {
        this.list = list;
    }
}
