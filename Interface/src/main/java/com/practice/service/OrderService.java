package com.practice.service;

import com.practice.dto.OrderPayDelayMessage;
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

    /**
     * Create order
     * @param activityId
     * @param studentId
     * @param token
     * @return
     * @throws Exception
     */
    JsonResult createOrder(Long activityId, Long studentId, String token) throws Exception;

    /**
     * Order 15min over
     * @param orderPayDelayMessage
     */
    void closeOrderPay(OrderPayDelayMessage orderPayDelayMessage);

    /**
     * Get order pay Info
     * @param orderNum
     * @param token
     * @return
     */
    JsonResult getOrderPayInfo(String orderNum, String token);
}
