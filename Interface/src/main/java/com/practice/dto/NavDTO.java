package com.practice.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author Xushd  2017/12/22 21:24
 */
public class NavDTO implements Serializable{

    private String title;
    private String icon;
    private String href;
    private boolean spread;
    private List<NavDTO> children;


    public NavDTO() {
    }

    public NavDTO(String title, String icon, String href, boolean spread) {
        this.title = title;
        this.icon = icon;
        this.href = href;
        this.spread = spread;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public boolean isSpread() {
        return spread;
    }

    public void setSpread(boolean spread) {
        this.spread = spread;
    }

    public List<NavDTO> getChildren() {
        return children;
    }

    public void setChildren(List<NavDTO> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "NavDTO{" +
                "title='" + title + '\'' +
                ", icon='" + icon + '\'' +
                ", href='" + href + '\'' +
                ", spread=" + spread +
                ", children=" + children +
                '}';
    }
}
