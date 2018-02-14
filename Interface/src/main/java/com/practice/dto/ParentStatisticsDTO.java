package com.practice.dto;

import java.io.Serializable;

/**
 * @author Xushd on 2018/2/14 10:17
 */
public class ParentStatisticsDTO implements Serializable{

    private Integer activityCount;

    private Integer themeCount;

    private Integer taskCount;

    public ParentStatisticsDTO() {
    }

    public Integer getActivityCount() {
        return activityCount;
    }

    public void setActivityCount(Integer activityCount) {
        this.activityCount = activityCount;
    }

    public Integer getThemeCount() {
        return themeCount;
    }

    public void setThemeCount(Integer themeCount) {
        this.themeCount = themeCount;
    }

    public Integer getTaskCount() {
        return taskCount;
    }

    public void setTaskCount(Integer taskCount) {
        this.taskCount = taskCount;
    }
}
