package com.practice.dto;

import java.io.Serializable;

/**
 * @author Xushd on 2018/1/3 13:36
 */
public class ApplyDTO implements Serializable{

    private Long id;

    private Long gradeId;

    private String period;

    private String grade;

    public ApplyDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGradeId() {
        return gradeId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
