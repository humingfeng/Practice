package com.practice.service;

import com.practice.result.JsonResult;

/**
 * @author Xushd on 2018/2/14 11:04
 */
public interface NewsService {
    /**
     * List news
     * @param pageIndex
     * @return
     */
    JsonResult listNews(Integer pageIndex);
}
