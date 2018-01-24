package com.practice.dto;

import java.util.List;

/**
 * @author Xushd on 2018/1/24 15:42
 */
public class SolrResultDTO<T> {
    private Long count;

    private List<T> list;

    public SolrResultDTO() {
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
