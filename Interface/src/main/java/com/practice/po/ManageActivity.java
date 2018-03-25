package com.practice.po;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Xushd  2018/3/20 22:54
 */
public class ManageActivity implements Serializable {

    private String baseName;

    private String type;

    private String classify;

    private String theme;

    private String timeStr;

    private String price;

    private String closeTimeStr;

    private String taskCloseTimeStr;

    public String getCloseTimeStr() {
        return closeTimeStr;
    }

    public void setCloseTimeStr(String closeTimeStr) {
        this.closeTimeStr = closeTimeStr;
    }

    public String getTaskCloseTimeStr() {
        return taskCloseTimeStr;
    }

    public void setTaskCloseTimeStr(String taskCloseTimeStr) {
        this.taskCloseTimeStr = taskCloseTimeStr;
    }

    public String getBaseName() {
        return baseName;
    }

    public void setBaseName(String baseName) {
        this.baseName = baseName;
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

    public String getTimeStr() {
        return timeStr;
    }

    public void setTimeStr(String timeStr) {
        this.timeStr = timeStr;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    private Long id;

    private Long typeId;

    private Long classifyId;

    private Long themeId;

    private String name;

    private Long organizeId;

    private Long baseId;

    private Integer duration;

    private Integer durationType;

    private String validTime;

    private Date beginTime;

    private Date endTime;

    private Date taskCloseTime;

    private Integer self;

    private Integer signInRun;

    private Integer collective;

    private Integer sign;

    private Integer number;

    private Integer minNum;

    private Integer stock;

    private Integer immediately;

    private Integer closeType;

    private Date closeTime;

    private Integer money;

    private String moneyDesc;

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

    private Long createUser;

    private Date createTime;

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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getDurationType() {
        return durationType;
    }

    public void setDurationType(Integer durationType) {
        this.durationType = durationType;
    }

    public String getValidTime() {
        return validTime;
    }

    public void setValidTime(String validTime) {
        this.validTime = validTime == null ? null : validTime.trim();
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

    public Date getTaskCloseTime() {
        return taskCloseTime;
    }

    public void setTaskCloseTime(Date taskCloseTime) {
        this.taskCloseTime = taskCloseTime;
    }

    public Integer getSelf() {
        return self;
    }

    public void setSelf(Integer self) {
        this.self = self;
    }

    public Integer getSignInRun() {
        return signInRun;
    }

    public void setSignInRun(Integer signInRun) {
        this.signInRun = signInRun;
    }

    public Integer getCollective() {
        return collective;
    }

    public void setCollective(Integer collective) {
        this.collective = collective;
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

    public Integer getMinNum() {
        return minNum;
    }

    public void setMinNum(Integer minNum) {
        this.minNum = minNum;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getImmediately() {
        return immediately;
    }

    public void setImmediately(Integer immediately) {
        this.immediately = immediately;
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

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public String getMoneyDesc() {
        return moneyDesc;
    }

    public void setMoneyDesc(String moneyDesc) {
        this.moneyDesc = moneyDesc == null ? null : moneyDesc.trim();
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

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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