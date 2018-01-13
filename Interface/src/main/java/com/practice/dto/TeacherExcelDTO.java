package com.practice.dto;

import java.io.Serializable;

/**
 * @author Xushd  2018/1/12 21:46
 */
public class TeacherExcelDTO implements Serializable{

    private String name;

    private String sex;

    private String phone;

    private String idNum;

    public TeacherExcelDTO() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }
}
