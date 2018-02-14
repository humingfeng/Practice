package com.practice.service;

import com.practice.result.JsonResult;

/**
 * @author Xushd on 2018/2/14 10:08
 */
public interface StatisticsService {

    /**
     * Get my statistics
     * @param token
     * @return
     */
    JsonResult getStatistics(String token);
}
