package com.practice.dto;

import java.io.Serializable;

/**
 * @author Xushd  2018/2/12 21:38
 */
public class TaskQuestionAnswerDTO implements Serializable {

    private Long taskId;

    private Long questionId;

    private Integer type;

    private String answer;

    private Long activityId;

    public TaskQuestionAnswerDTO() {
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
