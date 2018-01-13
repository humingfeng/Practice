package com.practice.dto;

import java.io.Serializable;

/**
 * @author Xushd  2018/1/13 11:46
 */
public class StudentExcelDTO implements Serializable {

    private String name;

    private String sex;

    private String enuNum;

    private String idNum;

    private String birthday;

    public StudentExcelDTO() {
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

    public String getEnuNum() {
        return enuNum;
    }

    public void setEnuNum(String enuNum) {
        this.enuNum = enuNum;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
