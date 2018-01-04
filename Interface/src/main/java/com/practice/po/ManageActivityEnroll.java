package com.practice.po;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Xushd  2018/1/4 20:50
 */
public class ManageActivityEnroll implements Serializable{
    private Long id;

    private Long activityId;

    private Integer phone;

    private Integer name;

    private Integer parentName;

    private Integer idNum;

    private Date updateTime;

    private Long updateUser;

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

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public Integer getName() {
        return name;
    }

    public void setName(Integer name) {
        this.name = name;
    }

    public Integer getParentName() {
        return parentName;
    }

    public void setParentName(Integer parentName) {
        this.parentName = parentName;
    }

    public Integer getIdNum() {
        return idNum;
    }

    public void setIdNum(Integer idNum) {
        this.idNum = idNum;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }
}