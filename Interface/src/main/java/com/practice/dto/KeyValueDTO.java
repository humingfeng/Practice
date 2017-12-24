package com.practice.dto;

import java.io.Serializable;

/**
 * @author Xushd  2017/12/23 17:21
 */
public class KeyValueDTO implements Serializable{

    private String key;

    private Long id;

    private String value;

    public KeyValueDTO() {
    }

    public KeyValueDTO(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public KeyValueDTO(Long id, String value) {
        this.id = id;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
