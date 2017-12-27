package com.practice.dto;

import java.io.Serializable;

/**
 *
 * @author Xushd on 2017/12/27 16:48
 */
public class CityDTO implements Serializable {

    private Long cid;
    private String name;

    public CityDTO() {
    }

    public CityDTO(Long cid, String name) {
        this.cid = cid;
        this.name = name;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CityDTO{" +
                "cid=" + cid +
                ", name='" + name + '\'' +
                '}';
    }
}
