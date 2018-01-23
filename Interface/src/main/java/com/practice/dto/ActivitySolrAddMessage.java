package com.practice.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Xushd  2018/1/23 22:47
 */
public class ActivitySolrAddMessage implements Serializable{

    private Long id;

    private String message;

    private Date date;

    public ActivitySolrAddMessage() {
    }

    public ActivitySolrAddMessage(Long id, String message, Date date) {
        this.id = id;
        this.message = message;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ActivitySolrAddMessage{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", date=" + date +
                '}';
    }
}
