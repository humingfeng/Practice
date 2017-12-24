package com.practice.dto;

import java.io.Serializable;

/**
 * @author Xushd  2017/12/24 0:17
 */
public class VersionItemDTO implements Serializable {

    private String title;

    private String author;

    public VersionItemDTO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "VersionItemDTO{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
