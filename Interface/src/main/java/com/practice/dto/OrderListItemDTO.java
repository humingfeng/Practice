package com.practice.dto;

import java.io.Serializable;

/**
 * @author Xushd  2018/2/3 12:02
 */
public class OrderListItemDTO implements Serializable{

    private String orderNum;

    private String orderName;

    private String price;

    private String createTime;

    private Integer status;

    private String description;

    private String imgCover;

    public OrderListItemDTO() {
    }


    public String getImgCover() {
        return imgCover;
    }

    public void setImgCover(String imgCover) {
        this.imgCover = imgCover;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "OrderListItemDTO{" +
                "orderNum='" + orderNum + '\'' +
                ", orderName='" + orderName + '\'' +
                ", price='" + price + '\'' +
                ", createTime='" + createTime + '\'' +
                ", status=" + status +
                ", description='" + description + '\'' +
                '}';
    }
}
