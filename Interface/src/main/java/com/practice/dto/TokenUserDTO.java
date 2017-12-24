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

    public TokenUserDTO() {
    }

    public TokenUserDTO(Long id, String account,String headImg) {
        this.id = id;
        this.account = account;
        this.headImg = headImg;
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
