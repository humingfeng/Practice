package com.practice.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author Xushd on 2018/2/23 15:30
 */
public class ProCityDTO implements Serializable{


    private Long id;

    private String name;

    private String spell;

    private String frist;


    public ProCityDTO() {
    }

    public String getFrist() {
        return frist;
    }

    public void setFrist(String frist) {
        this.frist = frist;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpell() {
        return spell;
    }

    public void setSpell(String spell) {
        this.spell = spell;
    }


}
