package com.practice.dto;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Map;

/**
 * 通用分页查询 dto 用户controller接受 请求参数
 * Created by Xushd on 2017/3/2.
 *
 * @author Xushd
 */
public class PageSearchParam implements Serializable {


    private Integer pageIndex;

    private Integer pageSize;

    private Map<String,String> searchFileds;

    private Integer pageStatus;


    public PageSearchParam(Integer pageIndex, Integer pageSize) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }

    public Integer getPageStatus() {
        return pageStatus;
    }

    public void setPageStatus(Integer pageStatus) {
        this.pageStatus = pageStatus;
    }

    public Integer getPageIndex() {
        if(pageIndex==0) {
            pageIndex = 1;
        }
        return pageIndex;
    }

    public PageSearchParam() {
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        if (pageSize==0) {
            pageSize = 10;
        }
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Map<String, String> getSearchFileds() {
        return searchFileds;
    }

    public void setSearchFileds(Map<String, String> searchFileds) {
        this.searchFileds = searchFileds;
    }

    public String getFiled(String key){
        if (this.searchFileds==null) {
            return null;
        }
        if (StringUtils.isEmpty(searchFileds.get(key))) {
            return null;
        }
        return searchFileds.get(key);
    }


    @Override
    public String toString() {
        return "PageSearchParam{" +
                "pageIndex=" + pageIndex +
                ", pageSize=" + pageSize +
                ", searchFileds=" + searchFileds +
                '}';
    }
}
