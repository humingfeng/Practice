package com.practice.po;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Xushd  2017/12/23 14:54
 */
public class ManageVersion implements Serializable{
    private Long id;

    private String versionNum;

    private String versionGeneral;

    private String leader;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String items;

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(String versionNum) {
        this.versionNum = versionNum == null ? null : versionNum.trim();
    }

    public String getVersionGeneral() {
        return versionGeneral;
    }

    public void setVersionGeneral(String versionGeneral) {
        this.versionGeneral = versionGeneral == null ? null : versionGeneral.trim();
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader == null ? null : leader.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}