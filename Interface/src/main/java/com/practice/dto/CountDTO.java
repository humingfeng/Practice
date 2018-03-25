package com.practice.dto;

import java.io.Serializable;

/**
 * @author Xushd  2018/3/13 23:10
 */
public class CountDTO implements Serializable {

    private Long activityCount;

    private Long themeCount;

    private Long classifyCount;

    private Long parentCount;

    private Long basesCount;

    private Long waitVerifyCount;

    public CountDTO() {
    }

    public Long getActivityCount() {
        return activityCount;
    }

    public void setActivityCount(Long activityCount) {
        this.activityCount = activityCount;
    }

    public Long getThemeCount() {
        return themeCount;
    }

    public void setThemeCount(Long themeCount) {
        this.themeCount = themeCount;
    }

    public Long getClassifyCount() {
        return classifyCount;
    }

    public void setClassifyCount(Long classifyCount) {
        this.classifyCount = classifyCount;
    }

    public Long getParentCount() {
        return parentCount;
    }

    public void setParentCount(Long parentCount) {
        this.parentCount = parentCount;
    }

    public Long getBasesCount() {
        return basesCount;
    }

    public void setBasesCount(Long basesCount) {
        this.basesCount = basesCount;
    }

    public Long getWaitVerifyCount() {
        return waitVerifyCount;
    }

    public void setWaitVerifyCount(Long waitVerifyCount) {
        this.waitVerifyCount = waitVerifyCount;
    }
}
