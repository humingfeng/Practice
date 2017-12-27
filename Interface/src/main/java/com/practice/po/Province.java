package com.practice.po;

import java.io.Serializable;

/**
 *
 * @author Xushd on 2017/12/27 16:07
 */
public class Province implements Serializable{
    private Long id;

    private Long provinceId;

    private String province;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }
}