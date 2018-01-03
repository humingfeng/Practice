package com.practice.dto;

import java.io.Serializable;

/**
 * Token User Info
 * @author Xushd  2017/12/22 0:07
 */
public class TokenUserDTO implements Serializable{

    private Long id;

    private String account;

    private String headImg;

    private Long oid;

    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public TokenUserDTO() {
    }

    public TokenUserDTO(Long id, String account,String headImg,Long oid) {
        this.id = id;
        this.account = account;
        this.headImg = headImg;
        this.oid = oid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    @Override
    public String toString() {
        return "TokenUserDTO{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", headImg='" + headImg + '\'' +
                '}';
    }
}
