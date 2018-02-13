package com.practice.dto;

import java.io.Serializable;

/**
 * @author Xushd  2018/2/13 22:28
 */
public class SignRecordDTO implements Serializable{

    private String signIn;

    private Integer signMark;

    private String signOut;

    private Integer signOutMark;

    private String groupTime;

    public SignRecordDTO() {
    }

    public Integer getSignMark() {
        return signMark;
    }

    public void setSignMark(Integer signMark) {
        this.signMark = signMark;
    }

    public Integer getSignOutMark() {
        return signOutMark;
    }

    public void setSignOutMark(Integer signOutMark) {
        this.signOutMark = signOutMark;
    }

    public String getSignIn() {
        return signIn;
    }

    public void setSignIn(String signIn) {
        this.signIn = signIn;
    }

    public String getSignOut() {
        return signOut;
    }

    public void setSignOut(String signOut) {
        this.signOut = signOut;
    }

    public String getGroupTime() {
        return groupTime;
    }

    public void setGroupTime(String groupTime) {
        this.groupTime = groupTime;
    }
}
