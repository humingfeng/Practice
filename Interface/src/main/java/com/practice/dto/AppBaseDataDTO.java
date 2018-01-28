package com.practice.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author Xushd  2018/1/26 22:51
 */
public class AppBaseDataDTO implements Serializable {

    private List<KeyValueDTO> period;

    private List<KeyValueDTO> actvityType;

    public AppBaseDataDTO() {
    }

    public List<KeyValueDTO> getPeriod() {
        return period;
    }

    public void setPeriod(List<KeyValueDTO> period) {
        this.period = period;
    }

    public List<KeyValueDTO> getActvityType() {
        return actvityType;
    }

    public void setActvityType(List<KeyValueDTO> actvityType) {
        this.actvityType = actvityType;
    }
}
