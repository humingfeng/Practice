package com.practice.dto;

import java.io.Serializable;

/**
 * @author Xushd  2018/1/13 17:28
 */
public class ActivityCheckDTO implements Serializable{

    private Integer introduceStatus;

    private String introduceMessage;

    private Integer leaderStatus;

    private String leaderMessage;

    private Integer applyStatus;

    private String applyMessage;

    private Integer signStatus;

    private String signMessage;

    private Integer taskStatus;

    private String taskMessage;

    private Integer enrollStatus;

    private String enrollMessage;

    private Integer superviseStatus;

    private String superviseMessage;

    public ActivityCheckDTO() {
    }

    public Integer getIntroduceStatus() {
        return introduceStatus;
    }

    public void setIntroduceStatus(Integer introduceStatus) {
        this.introduceStatus = introduceStatus;
    }

    public String getIntroduceMessage() {
        return introduceMessage;
    }

    public void setIntroduceMessage(String introduceMessage) {
        this.introduceMessage = introduceMessage;
    }

    public Integer getLeaderStatus() {
        return leaderStatus;
    }

    public void setLeaderStatus(Integer leaderStatus) {
        this.leaderStatus = leaderStatus;
    }

    public String getLeaderMessage() {
        return leaderMessage;
    }

    public void setLeaderMessage(String leaderMessage) {
        this.leaderMessage = leaderMessage;
    }

    public Integer getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(Integer applyStatus) {
        this.applyStatus = applyStatus;
    }

    public String getApplyMessage() {
        return applyMessage;
    }

    public void setApplyMessage(String applyMessage) {
        this.applyMessage = applyMessage;
    }

    public Integer getSignStatus() {
        return signStatus;
    }

    public void setSignStatus(Integer signStatus) {
        this.signStatus = signStatus;
    }

    public String getSignMessage() {
        return signMessage;
    }

    public void setSignMessage(String signMessage) {
        this.signMessage = signMessage;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskMessage() {
        return taskMessage;
    }

    public void setTaskMessage(String taskMessage) {
        this.taskMessage = taskMessage;
    }

    public Integer getEnrollStatus() {
        return enrollStatus;
    }

    public void setEnrollStatus(Integer enrollStatus) {
        this.enrollStatus = enrollStatus;
    }

    public String getEnrollMessage() {
        return enrollMessage;
    }

    public void setEnrollMessage(String enrollMessage) {
        this.enrollMessage = enrollMessage;
    }

    public Integer getSuperviseStatus() {
        return superviseStatus;
    }

    public void setSuperviseStatus(Integer superviseStatus) {
        this.superviseStatus = superviseStatus;
    }

    public String getSuperviseMessage() {
        return superviseMessage;
    }

    public void setSuperviseMessage(String superviseMessage) {
        this.superviseMessage = superviseMessage;
    }
}
