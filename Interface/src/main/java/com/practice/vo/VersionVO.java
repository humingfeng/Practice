package com.practice.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @author Xushd  2017/12/23 14:54
 */
public class VersionVO implements Serializable {

    private Long id;
    private String title;
    private String time;
    private String author;
    private String version;
    private List<VersionVO> items;

    public VersionVO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<VersionVO> getItems() {
        return items;
    }

    public void setItems(List<VersionVO> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "VersionVO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", time='" + time + '\'' +
                ", author='" + author + '\'' +
                ", version='" + version + '\'' +
                ", items=" + items +
                '}';
    }
}
