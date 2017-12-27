package com.practice.dto;

import java.io.Serializable;

/**
 * @author Xushd on 2017/12/27 16:49
 */
public class AreaDTO implements Serializable {

    private Long aid;

    private String name;

    public AreaDTO() {
    }

    public AreaDTO(Long aid, String name) {
        this.aid = aid;
        this.name = name;
    }

    public Long getAid() {
        return aid;
    }

    public void setAid(Long aid) {
        this.aid = aid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AreaDTO{" +
                "aid=" + aid +
                ", name='" + name + '\'' +
                '}';
    }
}
