package com.practice.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author Xushd  2018/2/7 22:05
 */
public class OrderDetailDTO implements Serializable{

    private String orderNum;

    private String price;

    private String orderName;

    private String createTime;

    private String payTime;

    private String status;

    private String methodName;

    private String studentName;

    private String schoolName;

    private String periodName;

    private String className;

    private String parentName;

    private String phone;

    private String activityName;

    private String imgCover;

    private List<String> applys;

    private String type;

    private String classify;

    private String theme;

    private String beginEndTime;

    public OrderDetailDTO() {
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getPeriodName() {
        return periodName;
    }

    public void setPeriodName(String periodName) {
        this.periodName = periodName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public List<String> getApplys() {
        return applys;
    }

    public void setApplys(List<String> applys) {
        this.applys = applys;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getBeginEndTime() {
        return beginEndTime;
    }

    public void setBeginEndTime(String beginEndTime) {
        this.beginEndTime = beginEndTime;
    }
}
