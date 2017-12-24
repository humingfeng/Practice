package com.practice.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @author Xushd  2017/12/23 21:01
 */
public class MenuVO implements Serializable {


    private Long id;

    private String icon;

    private Integer status;

    private String url;

    private String name;

    private Long parentId;

    private int checked;


    private List<MenuVO> children;

    public int getChecked() {
        return checked;
    }

    public void setChecked(int checked) {
        this.checked = checked;
    }

    public MenuVO() {
    }

    public List<MenuVO> getChildren() {
        return children;
    }

    public void setChildren(List<MenuVO> children) {
        this.children = children;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "MenuVO{" +
                "id=" + id +
                ", icon='" + icon + '\'' +
                ", status=" + status +
                ", url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                '}';
    }
}
