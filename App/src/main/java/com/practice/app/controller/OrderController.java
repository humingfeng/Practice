package com.practice.app.controller;

import com.alibaba.fastjson.JSONObject;
import com.practice.result.JsonResult;
import com.practice.service.OrderService;
import com.practice.service.PayService;
import com.practice.utils.JsonUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Xushd on 2018/2/1 13:21
 */
@RequestMapping("/app/auth/order")
@RestController
public class OrderController {

    @Resource
    private OrderService orderService;
    @Resource
    private PayService payService;
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

    /**
     * List my order
     * @param token
     * @param pageIndex
     * @return
     */
    @RequestMapping(value = "/my/list/{pageIndex}")
    public JsonResult listOrder(@RequestAttribute String token,@PathVariable Integer pageIndex){
        return orderService.listMyOrder(token,pageIndex);
    }

    /**
     * Get order pay string
     * @param orderNum
     * @param method
     * @return
     */
    @RequestMapping(value = "/pay/{method}/infostring/{orderNum}")
    public JsonResult getOrderPayString(@PathVariable String orderNum,@PathVariable Integer method){
        if(method==1){
            String aliPayOrderString = payService.getAliPayOrderString(orderNum);

            if(aliPayOrderString==null){
                return JsonResult.error("支付宝订单创建失败，请联系客服");
            }else{
                return JsonResult.build(200,"OK",aliPayOrderString);
            }

        }else{
            return JsonResult.success();
        }

    }

    /**
     * Get order pay status
     * @param orderNum
     * @return
     */
    @RequestMapping(value = "/pay/result/{orderNum}")
    public JsonResult getOrderPayStatus(@PathVariable String orderNum){
        return orderService.getOrderPayStatus(orderNum);
    }
}
