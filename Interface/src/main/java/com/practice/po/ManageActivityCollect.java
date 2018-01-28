package com.practice.po;

import java.io.Serializable;

/**
 *
 * @author Xushd  2018/1/28 16:39
 */
public class ManageActivityCollect implements Serializable{
    private Long id;

    private Long activityId;

    private Long parentId;

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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}