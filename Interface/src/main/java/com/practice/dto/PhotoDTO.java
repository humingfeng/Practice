package com.practice.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author Xushd  2018/2/22 23:27
 */
public class PhotoDTO implements Serializable{

    private List<String> img;

    private String desc;

    private String time;

    private Long id;

    public PhotoDTO() {
    }

    public List<String> getImg() {
        return img;
    }

    public void setImg(List<String> img) {
        this.img = img;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
