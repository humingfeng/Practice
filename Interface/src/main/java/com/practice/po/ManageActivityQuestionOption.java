package com.practice.po;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Xushd  2018/1/8 22:08
 */
public class ManageActivityQuestionOption implements Serializable{
    private Long id;

    private Long questionId;

    private String optionMark;

    private String text;

    private Date updateTime;

    private Long updateUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getOptionMark() {
        return optionMark;
    }

    public void setOptionMark(String optionMark) {
        this.optionMark = optionMark == null ? null : optionMark.trim();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
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