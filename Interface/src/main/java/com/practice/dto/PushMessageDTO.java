package com.practice.dto;

import java.io.Serializable;

/**
 * @author Xushd  2018/2/10 13:16
 */
public class PushMessageDTO implements Serializable {

    private Long msgId;

    private Integer status;

    /**
     * 1 活动消息 2 其他消息
     */
    private Integer type;

    private String createTime;

    private String updateTime;

    public PushMessageDTO() {
    }

    public PushMessageDTO(Long msgId, Integer status, Integer type, String createTime) {
        this.msgId = msgId;
        this.status = status;
        this.type = type;
        this.createTime = createTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getMsgId() {
        return msgId;
    }

    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "PushMessageDTO{" +
                "msgId=" + msgId +
                ", status=" + status +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
