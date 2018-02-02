package com.practice.dto;

/**
 * @author Xushd on 2018/2/2 14:20
 */
public class ActivitySkuDTO {

    private Long id;

    private String name;

    private Integer price;

    public ActivitySkuDTO() {
    }

    public ActivitySkuDTO(Long id, String name, Integer price) {
        this.id = id;
        this.name = name;
        this.price = price;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ActivitySkuDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
