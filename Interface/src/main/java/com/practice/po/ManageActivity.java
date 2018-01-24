package com.practice.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Xushd  2018/1/1 17:24
 */
public class ManageActivity implements Serializable {

    private String baseName;

    private String type;

    private String classify;

    private String theme;

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

    private String closeTimeStr;

    private String timeStr;

    public String getCloseTimeStr() {
        return closeTimeStr;
    }

    public void setCloseTimeStr(String closeTimeStr) {
        this.closeTimeStr = closeTimeStr;
    }

    public String getTimeStr() {
        return timeStr;
    }

    public void setTimeStr(String timeStr) {
        this.timeStr = timeStr;
    }

    public String getBaseName() {
        return baseName;
    }

    public void setBaseName(String baseName) {
        this.baseName = baseName;
    }

    private Long id;

    private Long typeId;

    private Long classifyId;

    private Long themeId;

    private String name;

    private Long organizeId;

    private Long baseId;

    private Float duration;

    private Integer durationType;

    private Date beginTime;

    private Date endTime;

    private Integer self;

    private Integer sign;

    private Integer number;

    private Integer closeType;

    private Date closeTime;

    private BigDecimal money;

    private Integer status;

    private Integer checkLeader;

    private Integer checkApply;

    private Integer checkSign;

    private Integer checkSupervise;

    private Integer checkTask;

    private Integer checkIntroduce;

    private Integer checkEnroll;

    private Integer checkEvaluate;

    private Integer delflag;

    private String reason;

    private Long updateUser;

    private Date updateTime;

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
        this.name = name == null ? null : name.trim();
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

    public Float getDuration() {
        return duration;
    }

    public void setDuration(Float duration) {
        this.duration = duration;
    }

    public Integer getDurationType() {
        return durationType;
    }

    public void setDurationType(Integer durationType) {
        this.durationType = durationType;
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

    public Integer getSelf() {
        return self;
    }

    public void setSelf(Integer self) {
        this.self = self;
    }

    public Integer getSign() {
        return sign;
    }

    public void setSign(Integer sign) {
        this.sign = sign;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getCloseType() {
        return closeType;
    }

    public void setCloseType(Integer closeType) {
        this.closeType = closeType;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCheckLeader() {
        return checkLeader;
    }

    public void setCheckLeader(Integer checkLeader) {
        this.checkLeader = checkLeader;
    }

    public Integer getCheckApply() {
        return checkApply;
    }

    public void setCheckApply(Integer checkApply) {
        this.checkApply = checkApply;
    }

    public Integer getCheckSign() {
        return checkSign;
    }

    public void setCheckSign(Integer checkSign) {
        this.checkSign = checkSign;
    }

    public Integer getCheckSupervise() {
        return checkSupervise;
    }

    public void setCheckSupervise(Integer checkSupervise) {
        this.checkSupervise = checkSupervise;
    }

    public Integer getCheckTask() {
        return checkTask;
    }

    public void setCheckTask(Integer checkTask) {
        this.checkTask = checkTask;
    }

    public Integer getCheckIntroduce() {
        return checkIntroduce;
    }

    public void setCheckIntroduce(Integer checkIntroduce) {
        this.checkIntroduce = checkIntroduce;
    }

    public Integer getCheckEnroll() {
        return checkEnroll;
    }

    public void setCheckEnroll(Integer checkEnroll) {
        this.checkEnroll = checkEnroll;
    }

    public Integer getCheckEvaluate() {
        return checkEvaluate;
    }

    public void setCheckEvaluate(Integer checkEvaluate) {
        this.checkEvaluate = checkEvaluate;
    }

    public Integer getDelflag() {
        return delflag;
    }

    public void setDelflag(Integer delflag) {
        this.delflag = delflag;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public Long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}