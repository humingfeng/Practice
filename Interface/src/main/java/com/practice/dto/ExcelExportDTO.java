package com.practice.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Excel export DTO
 * @author Xushd 2018/3/27 17:28
 */
public class ExcelExportDTO implements Serializable{

    private Map<String,Integer> titleFileds;

    private List<Map<String,String>> valueFileds;

    public ExcelExportDTO() {
    }

    public Map<String, Integer> getTitleFileds() {
        return titleFileds;
    }

    public void setTitleFileds(Map<String, Integer> titleFileds) {
        this.titleFileds = titleFileds;
    }

    public List<Map<String, String>> getValueFileds() {
        return valueFileds;
    }

    public void setValueFileds(List<Map<String, String>> valueFileds) {
        this.valueFileds = valueFileds;
    }
}
