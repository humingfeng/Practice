package com.practice.app.controller;

import com.practice.result.JsonResult;
import com.practice.service.OrderService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Xushd on 2018/2/1 13:21
 */
@RestController("/app/auth/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    /**
     * Get
     * @param activityId
     * @param token
     * @return
     */
    @RequestMapping(value = "/preview/{activityId}")
    public JsonResult getOrderInfoPreview(@PathVariable Long activityId, @RequestAttribute String token){
        return orderService.getOrderInfoPreview(activityId,token);
    }
}
