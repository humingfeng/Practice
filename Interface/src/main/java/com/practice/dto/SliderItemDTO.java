package com.practice.dto;

import java.io.Serializable;

/**
 * @author Xushd  2018/1/20 22:02
 */
public class SliderItemDTO implements Serializable {

    private Long id;

    private String img;

    private String url;

    private String name;

    private Integer type;

    private Integer index;

    public SliderItemDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "SliderItemDTO{" +
                "id=" + id +
                ", img='" + img + '\'' +
                ", url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", index=" + index +
                '}';
    }
}
