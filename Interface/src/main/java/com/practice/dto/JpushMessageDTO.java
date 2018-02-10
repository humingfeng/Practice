package com.practice.dto;

import java.io.Serializable;

/**
 * @author Xushd  2018/2/10 18:06
 */
public class JpushMessageDTO implements Serializable{

    private String content;

    private String title;

    private Long msgId;

    private Long activityId;

    private Integer type;

    private String tag;

    private String cid;

    public JpushMessageDTO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getMsgId() {
        return msgId;
    }

    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    @Override
    public String toString() {
        return "JpushMessageDTO{" +
                "content='" + content + '\'' +
                ", msgId=" + msgId +
                ", activityId=" + activityId +
                ", type=" + type +
                '}';
    }
}
