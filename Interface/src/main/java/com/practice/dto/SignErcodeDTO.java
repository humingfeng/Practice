package com.practice.dto;

import java.io.Serializable;

/**
 * @author Xushd on 2018/2/13 15:12
 */
public class SignErcodeDTO implements Serializable{

    private Long activityId;

    private Long signId;

    /**
     * 1 签到 2 签退
     */
    private Integer event;

    private String type;

    public SignErcodeDTO() {
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Long getSignId() {
        return signId;
    }

    public void setSignId(Long signId) {
        this.signId = signId;
    }

    public Integer getEvent() {
        return event;
    }

    public void setEvent(Integer event) {
        this.event = event;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
