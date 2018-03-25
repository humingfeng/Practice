package com.practice.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author Xushd  2018/3/10 16:35
 */
public class SignStudentRecordListDTO implements Serializable {

    private String groupDate;

    private List<SignStudentRecord> list;

    public SignStudentRecordListDTO() {
    }

    public String getGroupDate() {
        return groupDate;
    }

    public void setGroupDate(String groupDate) {
        this.groupDate = groupDate;
    }

    public List<SignStudentRecord> getList() {
        return list;
    }

    public void setList(List<SignStudentRecord> list) {
        this.list = list;
    }
}
