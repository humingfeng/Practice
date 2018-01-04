package com.practice.po;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Xushd  2018/1/4 21:42
 */
public class ManageActivitySign implements Serializable{
    private Long id;

    private Long activityId;

    private Integer signIn;

    private String signInErcode;

    private Integer signOut;

    private String signOutErcode;

    private Long signOutTime;

    private Date updateTime;

    private Long updateUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Integer getSignIn() {
        return signIn;
    }

    public void setSignIn(Integer signIn) {
        this.signIn = signIn;
    }

    public String getSignInErcode() {
        return signInErcode;
    }

    public void setSignInErcode(String signInErcode) {
        this.signInErcode = signInErcode == null ? null : signInErcode.trim();
    }

    public Integer getSignOut() {
        return signOut;
    }

    public void setSignOut(Integer signOut) {
        this.signOut = signOut;
    }

    public String getSignOutErcode() {
        return signOutErcode;
    }

    public void setSignOutErcode(String signOutErcode) {
        this.signOutErcode = signOutErcode == null ? null : signOutErcode.trim();
    }

    public Long getSignOutTime() {
        return signOutTime;
    }

    public void setSignOutTime(Long signOutTime) {
        this.signOutTime = signOutTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }
}