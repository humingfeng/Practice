package com.practice.dto;

import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Xushd on 2018/2/2 15:11
 */
public class OrderPayDelayMessage implements Serializable{

    private Long activityId;

    private String orderNum;

    private Date createDate;

    private Date updateDate;

    private Integer status;

    public OrderPayDelayMessage() {
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "OrderPayDelayMessage{" +
                "activityId=" + activityId +
                ", orderNum='" + orderNum + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", status=" + status +
                '}';
    }
}
