package com.practice.service;

import com.practice.dto.CountDTO;
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

    /**
     * Get tongji by activity user
     * @param token
     * @return
     */
    JsonResult getTongjiByActivity(String token);

    /**
     * Get tongji by activity
     * @param id
     * @return
     */
    JsonResult getTongjiByActivityInfo(Long id);

    /**
     * Get index count
     * @return
     */
    CountDTO getCountDTO();
}
