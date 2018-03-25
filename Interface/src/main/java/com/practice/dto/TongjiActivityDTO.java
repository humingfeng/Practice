package com.practice.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author Xushd  2018/3/11 0:26
 */
public class TongjiActivityDTO implements Serializable {

    private Integer enrollCount;

    private Integer schoolCount;

    private List<KeyValueDTO> school;

    private Integer periodCount;

    private List<KeyValueDTO> period;

    public TongjiActivityDTO() {
    }

    public Integer getEnrollCount() {
        return enrollCount;
    }

    public void setEnrollCount(Integer enrollCount) {
        this.enrollCount = enrollCount;
    }

    public Integer getSchoolCount() {
        return schoolCount;
    }

    public void setSchoolCount(Integer schoolCount) {
        this.schoolCount = schoolCount;
    }

    public List<KeyValueDTO> getSchool() {
        return school;
    }

    public void setSchool(List<KeyValueDTO> school) {
        this.school = school;
    }

    public Integer getPeriodCount() {
        return periodCount;
    }

    public void setPeriodCount(Integer periodCount) {
        this.periodCount = periodCount;
    }

    public List<KeyValueDTO> getPeriod() {
        return period;
    }

    public void setPeriod(List<KeyValueDTO> period) {
        this.period = period;
    }
}
