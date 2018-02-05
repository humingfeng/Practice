package com.practice.dto;

import java.io.Serializable;

/**
 * @author Xushd  2018/2/2 23:02
 */
public class OrderPayInfoDTO implements Serializable {

    private String orderNum;

    private Long activiyId;

    private String orderName;

    private String studentName;

    private String schoolName;

    private String periodName;

    private String className;

    private String phone;

    private String createTime;

    private String overTime;

    private String price;

    public OrderPayInfoDTO() {

    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Long getActiviyId() {
        return activiyId;
    }

    public void setActiviyId(Long activiyId) {
        this.activiyId = activiyId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getOverTime() {
        return overTime;
    }

    public void setOverTime(String overTime) {
        this.overTime = overTime;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderPayInfoDTO{" +
                "orderNum='" + orderNum + '\'' +
                ", activiyId=" + activiyId +
                ", orderName='" + orderName + '\'' +
                ", studentName='" + studentName + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", periodName='" + periodName + '\'' +
                ", className='" + className + '\'' +
                ", phone='" + phone + '\'' +
                ", createTime='" + createTime + '\'' +
                ", overTime='" + overTime + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
