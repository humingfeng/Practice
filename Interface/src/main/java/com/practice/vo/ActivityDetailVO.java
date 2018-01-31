package com.practice.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @author Xushd  2018/1/28 15:52
 */
public class ActivityDetailVO implements Serializable {


    private Long id;

    private String imgCover;

    private String name;

    private String type;

    private String classify;

    private String theme;

    private Integer taskNum;

    private Integer number;

    private Integer enroll;

    private Integer collect;

    private String closeTime;

    private String beginTime;

    private String endTime;

    private Long baseId;

    private String baseName;

    private String baseCover;

    private String duration;

    private Integer durationType;

    private String address;

    private String introduce;

    private List<String> attentions;

    private String price;

    private Integer sign;

    private Integer closeType;

    private String time;

    private Integer myConllect;

    private Integer myEnroll;

    private List<String> applys;

    public ActivityDetailVO() {
    }

    public Integer getMyEnroll() {
        return myEnroll;
    }

    public void setMyEnroll(Integer myEnroll) {
        this.myEnroll = myEnroll;
    }

    public List<String> getApplys() {
        return applys;
    }

    public void setApplys(List<String> applys) {
        this.applys = applys;
    }

    public Integer getMyConllect() {
        return myConllect;
    }

    public String getBaseCover() {
        return baseCover;
    }

    public void setBaseCover(String baseCover) {
        this.baseCover = baseCover;
    }

    public void setMyConllect(Integer myConllect) {
        this.myConllect = myConllect;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImgCover() {
        return imgCover;
    }

    public void setImgCover(String imgCover) {
        this.imgCover = imgCover;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getTaskNum() {
        return taskNum;
    }

    public void setTaskNum(Integer taskNum) {
        this.taskNum = taskNum;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getEnroll() {
        return enroll;
    }

    public void setEnroll(Integer enroll) {
        this.enroll = enroll;
    }

    public Integer getCollect() {
        return collect;
    }

    public void setCollect(Integer collect) {
        this.collect = collect;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Long getBaseId() {
        return baseId;
    }

    public void setBaseId(Long baseId) {
        this.baseId = baseId;
    }

    public String getBaseName() {
        return baseName;
    }

    public void setBaseName(String baseName) {
        this.baseName = baseName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Integer getDurationType() {
        return durationType;
    }

    public void setDurationType(Integer durationType) {
        this.durationType = durationType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public List<String> getAttentions() {
        return attentions;
    }

    public void setAttentions(List<String> attentions) {
        this.attentions = attentions;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getSign() {
        return sign;
    }

    public void setSign(Integer sign) {
        this.sign = sign;
    }

    public Integer getCloseType() {
        return closeType;
    }

    public void setCloseType(Integer closeType) {
        this.closeType = closeType;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
