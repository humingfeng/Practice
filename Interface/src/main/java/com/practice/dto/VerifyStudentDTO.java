package com.practice.dto;

import java.io.Serializable;

/**
 * @author Xushd  2018/1/16 22:22
 */
public class VerifyStudentDTO implements Serializable{

    private Long sid;
    private Long periodId;
    private Long classId;
    private String name;

    public VerifyStudentDTO() {
    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public Long getPeriodId() {
        return periodId;
    }

    public void setPeriodId(Long periodId) {
        this.periodId = periodId;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
