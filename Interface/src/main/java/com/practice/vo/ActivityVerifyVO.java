package com.practice.vo;

import com.practice.dto.TaskDTO;
import com.practice.po.*;

import java.io.Serializable;
import java.util.List;

/**
 * @author Xushd  2018/1/13 21:21
 */
public class ActivityVerifyVO implements Serializable {

    private ManageActivity base;

    private ManageActivityIntroduce introduce;

    private List<ManageActivityApply> activityApplyList;

    private ManageActivityEnroll enroll;

    private List<ManageActivityLeader> leaderList;

    private ManageActivitySign sign;

    private List<ManageActivitySupervise> superviseList;

    private List<ManageActivityAttention> attentionList;

    private List<TaskDTO> taskList;

    public ActivityVerifyVO() {
    }

    public ManageActivity getBase() {
        return base;
    }

    public void setBase(ManageActivity base) {
        this.base = base;
    }

    public ManageActivityIntroduce getIntroduce() {
        return introduce;
    }

    public void setIntroduce(ManageActivityIntroduce introduce) {
        this.introduce = introduce;
    }

    public List<ManageActivityApply> getActivityApplyList() {
        return activityApplyList;
    }

    public void setActivityApplyList(List<ManageActivityApply> activityApplyList) {
        this.activityApplyList = activityApplyList;
    }

    public ManageActivityEnroll getEnroll() {
        return enroll;
    }

    public void setEnroll(ManageActivityEnroll enroll) {
        this.enroll = enroll;
    }

    public List<ManageActivityLeader> getLeaderList() {
        return leaderList;
    }

    public void setLeaderList(List<ManageActivityLeader> leaderList) {
        this.leaderList = leaderList;
    }

    public ManageActivitySign getSign() {
        return sign;
    }

    public void setSign(ManageActivitySign sign) {
        this.sign = sign;
    }

    public List<ManageActivitySupervise> getSuperviseList() {
        return superviseList;
    }

    public void setSuperviseList(List<ManageActivitySupervise> superviseList) {
        this.superviseList = superviseList;
    }

    public List<ManageActivityAttention> getAttentionList() {
        return attentionList;
    }

    public void setAttentionList(List<ManageActivityAttention> attentionList) {
        this.attentionList = attentionList;
    }

    public List<TaskDTO> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<TaskDTO> taskList) {
        this.taskList = taskList;
    }
}
