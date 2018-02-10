package com.practice.vo;

import java.io.Serializable;

/**
 * @author Xushd on 2018/1/24 14:37
 */
public class ActivityListItemVO implements Serializable{

    private Long id;

    private String name;

    private String imgCover;

    private String duration;

    private String price;

    private String beginTime;

    private String endTime;

    private Integer number;

    private Integer enrolled;

    private String score;

    private Integer sign;

    private Integer durationType;

    private Integer closeType;

    private String closeTime;

    private String time;

    private Integer supervise;

    private Integer status;

    private Integer self;

    private Integer collect;

    public Integer getSelf() {
        return self;
    }

    public void setSelf(Integer self) {
        this.self = self;
    }

    public Integer getCollect() {
        return collect;
    }

    public void setCollect(Integer collect) {
        this.collect = collect;
    }

    public Integer getSupervise() {
        return supervise;
    }

    public void setSupervise(Integer supervise) {
        this.supervise = supervise;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public ActivityListItemVO() {
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getDurationType() {
        return durationType;
    }

    public void setDurationType(Integer durationType) {
        this.durationType = durationType;
    }

    public Integer getCloseType() {
        return closeType;
    }

    public void setCloseType(Integer closeType) {
        this.closeType = closeType;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public Integer getSign() {
        return sign;
    }

    public void setSign(Integer sign) {
        this.sign = sign;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgCover() {
        return imgCover;
    }

    public void setImgCover(String imgCover) {
        this.imgCover = imgCover;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getEnrolled() {
        return enrolled;
    }

    public void setEnrolled(Integer enrolled) {
        this.enrolled = enrolled;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "ActivitySearchVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imgCover='" + imgCover + '\'' +
                ", duration='" + duration + '\'' +
                ", price='" + price + '\'' +
                ", beginTime='" + beginTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", number=" + number +
                ", enrolled=" + enrolled +
                ", score=" + score +
                ", sign=" + sign +
                ", ducationType=" + durationType +
                ", closeType=" + closeType +
                ", closeTime='" + closeTime + '\'' +
                '}';
    }
}
