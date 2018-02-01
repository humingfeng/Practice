package com.practice.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author Xushd on 2018/2/1 14:39
 */
public class OrderPreviewDTO implements Serializable {


    private Long activityId;

    private String activityName;

    private String price;

    private String imgCover;

    private List<String> applys;

    private Integer sign;

    private String type;

    private String classify;

    private String theme;

    private String baseName;

    private String time;

    private String beginAndEnd;


    public OrderPreviewDTO() {
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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

    public Integer getSign() {
        return sign;
    }

    public void setSign(Integer sign) {
        this.sign = sign;
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

    public String getBaseName() {
        return baseName;
    }

    public void setBaseName(String baseName) {
        this.baseName = baseName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBeginAndEnd() {
        return beginAndEnd;
    }

    public void setBeginAndEnd(String beginAndEnd) {
        this.beginAndEnd = beginAndEnd;
    }
}
