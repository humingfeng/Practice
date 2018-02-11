package com.practice.dto;

import java.io.Serializable;

/**
 * @author Xushd  2018/2/11 21:06
 */
public class ActivityTaskDTO implements Serializable {

    private Long acivityId;

    private Long taskId;

    private String taskName;

    private String taskDescritption;

    private String imgCover;

    private Integer questionLimit;

    private Integer taskLimit;

    private Integer questionNum;

    public ActivityTaskDTO() {
    }

    public Integer getQuestionNum() {
        return questionNum;
    }

    public void setQuestionNum(Integer questionNum) {
        this.questionNum = questionNum;
    }

    public Long getAcivityId() {
        return acivityId;
    }

    public void setAcivityId(Long acivityId) {
        this.acivityId = acivityId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescritption() {
        return taskDescritption;
    }

    public void setTaskDescritption(String taskDescritption) {
        this.taskDescritption = taskDescritption;
    }

    public String getImgCover() {
        return imgCover;
    }

    public void setImgCover(String imgCover) {
        this.imgCover = imgCover;
    }

    public Integer getQuestionLimit() {
        return questionLimit;
    }

    public void setQuestionLimit(Integer questionLimit) {
        this.questionLimit = questionLimit;
    }

    public Integer getTaskLimit() {
        return taskLimit;
    }

    public void setTaskLimit(Integer taskLimit) {
        this.taskLimit = taskLimit;
    }
}
