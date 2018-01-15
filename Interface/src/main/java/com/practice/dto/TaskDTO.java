package com.practice.dto;

import com.practice.po.ManageActivityQuestion;
import com.practice.po.ManageActivityTask;

import java.io.Serializable;
import java.util.List;

/**
 * @author Xushd  2018/1/13 22:24
 */
public class TaskDTO implements Serializable{

    private ManageActivityTask task;

    private List<ManageActivityQuestion> questionList;

    public TaskDTO() {
    }

    public ManageActivityTask getTask() {
        return task;
    }

    public void setTask(ManageActivityTask task) {
        this.task = task;
    }

    public List<ManageActivityQuestion> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<ManageActivityQuestion> questionList) {
        this.questionList = questionList;
    }
}
