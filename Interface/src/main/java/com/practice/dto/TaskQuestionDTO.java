package com.practice.dto;


import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Xushd on 2018/2/12 上午11:29
 */
public class TaskQuestionDTO implements Serializable{


    private Long activityId;

    private Long taskId;

    private Long questionId;

    private String questionStr;
    /**
     * 1 选择题 2 填空 3 拍照 4 简答 5 访谈 6 调研
     */
    private Long type;

    /**
     * 1 客观题 2 主观题
     */
    private Integer classify;

    private List<KeyValueDTO> options;

    private Integer photoNum;

    private String answer;

    private List<String> answers;

    public TaskQuestionDTO() {
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
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

    public String getQuestionStr() {
        return questionStr;
    }

    public void setQuestionStr(String questionStr) {
        this.questionStr = questionStr;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Integer getClassify() {
        return classify;
    }

    public void setClassify(Integer classify) {
        this.classify = classify;
    }

    public Integer getPhotoNum() {
        return photoNum;
    }

    public void setPhotoNum(Integer photoNum) {
        this.photoNum = photoNum;
    }

    public List<KeyValueDTO> getOptions() {
        return options;
    }

    public void setOptions(List<KeyValueDTO> options) {
        this.options = options;
    }
}
