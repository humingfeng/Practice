package com.practice.po;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Xushd on 2018/2/13 15:32
 */
public class ManageActivitySignRecord implements Serializable {
    private Long id;

    private Long activityId;

    private Long studentId;

    private Long signId;

    private String groupDate;

    private Integer type;

    private Date signTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getSignId() {
        return signId;
    }

    public void setSignId(Long signId) {
        this.signId = signId;
    }

    public String getGroupDate() {
        return groupDate;
    }

    public void setGroupDate(String groupDate) {
        this.groupDate = groupDate == null ? null : groupDate.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getSignTime() {
        return signTime;
    }

    public void setSignTime(Date signTime) {
        this.signTime = signTime;
    }
}