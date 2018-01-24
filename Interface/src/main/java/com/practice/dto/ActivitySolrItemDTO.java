package com.practice.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Xushd  2018/1/23 20:50
 */
public class ActivitySolrItemDTO implements Serializable{

    private Long id;

    private Long typeId;

    private Long classifyId;

    private Long themeId;

    private String name;

    private String imgCover;

    private Long organizeId;

    private Long baseId;

    private Float timeHour;

    private Integer self;

    private Float money;

    private Date beginTime;

    private Date endTime;

    private Date closeTime;

    private Integer closeType;

    private Long like;

    private Integer number;

    private Integer ducationType;

    private Integer sign;

    private Integer enroll;

    private String apply;

    private String typeName;

    private String classifyName;

    private String themeName;

    private String organizeName;

    private String baseName;

    private String pinyin;

    public ActivitySolrItemDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Long getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(Long classifyId) {
        this.classifyId = classifyId;
    }

    public Long getThemeId() {
        return themeId;
    }

    public void setThemeId(Long themeId) {
        this.themeId = themeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getOrganizeId() {
        return organizeId;
    }

    public void setOrganizeId(Long organizeId) {
        this.organizeId = organizeId;
    }

    public Long getBaseId() {
        return baseId;
    }

    public void setBaseId(Long baseId) {
        this.baseId = baseId;
    }

    public Float getTimeHour() {
        return timeHour;
    }

    public void setTimeHour(Float timeHour) {
        this.timeHour = timeHour;
    }

    public Integer getSelf() {
        return self;
    }

    public void setSelf(Integer self) {
        this.self = self;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    public Long getLike() {
        return like;
    }

    public void setLike(Long like) {
        this.like = like;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public String getOrganizeName() {
        return organizeName;
    }

    public void setOrganizeName(String organizeName) {
        this.organizeName = organizeName;
    }

    public String getBaseName() {
        return baseName;
    }

    public void setBaseName(String baseName) {
        this.baseName = baseName;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getImgCover() {
        return imgCover;
    }

    public void setImgCover(String imgCover) {
        this.imgCover = imgCover;
    }

    public Integer getEnroll() {
        return enroll;
    }

    public void setEnroll(Integer enroll) {
        this.enroll = enroll;
    }

    public String getApply() {
        return apply;
    }

    public void setApply(String apply) {
        this.apply = apply;
    }

    public Integer getDucationType() {
        return ducationType;
    }

    public void setDucationType(Integer ducationType) {
        this.ducationType = ducationType;
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

    @Override
    public String toString() {
        return "ActivitySolrItemDTO{" +
                "id=" + id +
                ", typeId=" + typeId +
                ", classifyId=" + classifyId +
                ", themeId=" + themeId +
                ", name='" + name + '\'' +
                ", imgCover='" + imgCover + '\'' +
                ", organizeId=" + organizeId +
                ", baseId=" + baseId +
                ", timeHour=" + timeHour +
                ", self=" + self +
                ", money=" + money +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                ", closeTime=" + closeTime +
                ", like=" + like +
                ", number=" + number +
                ", enroll=" + enroll +
                ", apply='" + apply + '\'' +
                ", typeName='" + typeName + '\'' +
                ", classifyName='" + classifyName + '\'' +
                ", themeName='" + themeName + '\'' +
                ", organizeName='" + organizeName + '\'' +
                ", baseName='" + baseName + '\'' +
                ", pinyin='" + pinyin + '\'' +
                '}';
    }
}
