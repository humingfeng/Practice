package com.practice.service;

import com.practice.result.JsonResult;

/**
 * @author Xushd on 2018/1/30 15:24
 */
public interface OrderService {
    /**
     * Get order preview info
     * @param activityId
     * @param token
     * @return
     */
    JsonResult getOrderInfoPreview(Long activityId, String token);
}
