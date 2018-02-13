package com.practice.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author Xushd on 2018/2/13 15:54
 */
public class SignResultDTO implements Serializable{

    private String activityName;

    private String imgCover;

    private String validTime;

    private String durationTime;

    private String studentName;

    private String studentDesc;

    private List<SignRecordDTO> list;

    public SignResultDTO() {
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getImgCover() {
        return imgCover;
    }

    public void setImgCover(String imgCover) {
        this.imgCover = imgCover;
    }

    public String getValidTime() {
        return validTime;
    }

    public void setValidTime(String validTime) {
        this.validTime = validTime;
    }

    public String getDurationTime() {
        return durationTime;
    }

    public void setDurationTime(String durationTime) {
        this.durationTime = durationTime;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentDesc() {
        return studentDesc;
    }

    public void setStudentDesc(String studentDesc) {
        this.studentDesc = studentDesc;
    }

    public List<SignRecordDTO> getList() {
        return list;
    }

    public void setList(List<SignRecordDTO> list) {
        this.list = list;
    }
}
