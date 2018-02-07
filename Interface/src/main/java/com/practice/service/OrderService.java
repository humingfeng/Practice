package com.practice.service;

import com.github.pagehelper.page.PageParams;
import com.practice.dto.OrderPayDelayMessage;
import com.practice.dto.PageSearchParam;
import com.practice.po.OrderInfo;
import com.practice.result.JsonResult;

import java.util.Map;

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

    /**
     * List my order
     * @param token
     * @param pageIndex
     * @return
     */
    JsonResult listMyOrder(String token, Integer pageIndex);

    /**
     * Get order pay status
     * @param orderNum
     * @return
     */
    JsonResult getOrderPayStatus(String orderNum);

    /**
     * Update order and insert record
     * @param params
     */
    void updateAndRecordPayInfo(Map<String, String> params);

    /**
     * Get order Info
     * @param orderNum
     * @return
     */
    OrderInfo getOderInfoByOrderNum(String orderNum);

    /**
     * List order
     * @param param
     * @return
     */
    JsonResult listOrder(PageSearchParam param);

    /**
     * Get order detail
     * @param id
     * @return
     */
    JsonResult getOrderInfoDetail(Long id);
}
