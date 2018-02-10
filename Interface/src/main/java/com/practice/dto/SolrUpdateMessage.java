package com.practice.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Xushd on 2018/2/9 16:05
 */
public class SolrUpdateMessage implements Serializable{

    /**
     * 1 更新like 2 更新collect 3 更新enroll 4 status
     */
    private Integer type;

    private Long id;

    /**
     * 1 创建 2 执行完成
     */
    private Integer status;

    private Date createTime;

    private Date updateTime;

    public SolrUpdateMessage() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
