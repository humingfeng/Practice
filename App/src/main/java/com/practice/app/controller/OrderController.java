package com.practice.app.controller;

import com.practice.result.JsonResult;
import com.practice.service.OrderService;
import com.practice.utils.JsonUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Xushd on 2018/2/1 13:21
 */
@RequestMapping("/app/auth/order")
@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    /**
     * Get order preview
     * @param activityId
     * @param token
     * @return
     */
    @RequestMapping(value = "/preview/{activityId}")
    public JsonResult getOrderInfoPreview(@PathVariable Long activityId, @RequestAttribute String token){
        return orderService.getOrderInfoPreview(activityId,token);
    }

    /**
     * Create order
     * @param activityId
     * @param studentId
     * @return
     */
    @RequestMapping(value = "/create/order")
    public JsonResult createOrder(Long activityId,Long studentId,@RequestAttribute String token) throws Exception {
        return orderService.createOrder(activityId,studentId,token);
    }

    /**
     * Get order pay info
     * @param orderNum
     * @param token
     * @return
     */
    @RequestMapping(value = "/pay/info")
    public JsonResult getOrderPayInfo(String orderNum,@RequestAttribute String token){
        return orderService.getOrderPayInfo(orderNum,token);
    }
}
