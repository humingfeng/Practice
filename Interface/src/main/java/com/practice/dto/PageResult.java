package com.practice.dto;


import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.List;

/**
 * 分页查询返回结果
 *
 * @author Xushd on 2018/1/9 16:48
 */
public class PageResult<T> implements Serializable {


    private int total;
    private int pages;
    private int pageNum;
    private List<T> list;


    public PageResult(int total, List<T> list, int pageNum, int pages) {
        this.total = total;
        this.list = list;
        this.pageNum = pageNum;
        this.pages = pages;
    }

    public PageResult(PageInfo pageInfo) {
        this.total = (int) pageInfo.getTotal();
        this.list = pageInfo.getList();
        this.pageNum = pageInfo.getPageNum();
        this.pages = pageInfo.getPages();
    }

    public PageResult(PageInfo pageInfo, List<T> list) {
        this.total = (int) pageInfo.getTotal();
        this.list = list;
        this.pageNum = pageInfo.getPageNum();
        this.pages = pageInfo.getPages();
    }

    public PageResult() {
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }


    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
