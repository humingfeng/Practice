package com.practice.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author Xushd  2018/2/8 20:54
 */
public class FilterPeriodDTO implements Serializable{

    private List<KeyValueDTO> sList;

    private List<KeyValueDTO> mList;

    private List<KeyValueDTO> xList;

    public FilterPeriodDTO() {
    }

    public List<KeyValueDTO> getsList() {
        return sList;
    }

    public void setsList(List<KeyValueDTO> sList) {
        this.sList = sList;
    }

    public List<KeyValueDTO> getmList() {
        return mList;
    }

    public void setmList(List<KeyValueDTO> mList) {
        this.mList = mList;
    }

    public List<KeyValueDTO> getxList() {
        return xList;
    }

    public void setxList(List<KeyValueDTO> xList) {
        this.xList = xList;
    }
}
