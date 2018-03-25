package com.practice.dto;

import com.practice.vo.ActivityListItemVO;

import java.io.Serializable;
import java.util.List;

/**
 * @author Xushd  2018/3/10 22:35
 */
public class TongJiByActivityDTO implements Serializable {

    private Integer activityTotal;

    private Integer thememTotal;

    private Integer classifyTotal;

    private Integer mainTotal;

    private List<ActivityListItemVO> list;

    public TongJiByActivityDTO() {
    }

    public TongJiByActivityDTO(Integer init) {
        this.activityTotal = init;
        this.thememTotal = init;
        this.classifyTotal = init;
        this.mainTotal = init;
    }

    public List<ActivityListItemVO> getList() {
        return list;
    }

    public void setList(List<ActivityListItemVO> list) {
        this.list = list;
    }

    public Integer getActivityTotal() {
        return activityTotal;
    }

    public void setActivityTotal(Integer activityTotal) {
        this.activityTotal = activityTotal;
    }

    public Integer getThememTotal() {
        return thememTotal;
    }

    public void setThememTotal(Integer thememTotal) {
        this.thememTotal = thememTotal;
    }

    public Integer getClassifyTotal() {
        return classifyTotal;
    }

    public void setClassifyTotal(Integer classifyTotal) {
        this.classifyTotal = classifyTotal;
    }

    public Integer getMainTotal() {
        return mainTotal;
    }

    public void setMainTotal(Integer mainTotal) {
        this.mainTotal = mainTotal;
    }
}
