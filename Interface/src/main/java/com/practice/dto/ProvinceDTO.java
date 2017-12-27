package com.practice.dto;

import java.io.Serializable;

/**
 * @author Xushd on 2017/12/27 16:47
 */
public class ProvinceDTO implements Serializable {

    private Long pid;

    private String name;

    public ProvinceDTO() {
    }

    public ProvinceDTO(Long pid, String name) {
        this.pid = pid;
        this.name = name;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ProvinceDTO{" +
                "pid=" + pid +
                ", name='" + name + '\'' +
                '}';
    }
}
