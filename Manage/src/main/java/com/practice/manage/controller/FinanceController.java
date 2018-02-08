package com.practice.manage.controller;

import com.github.pagehelper.page.PageParams;
import com.practice.dto.PageSearchParam;
import com.practice.result.JsonResult;
import com.practice.service.OrderService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author Xushd  2018/2/7 20:27
 */
@Controller
public class FinanceController {

    @Resource
    private OrderService orderService;
    /**
     * Order index
     * @return
     */
    @RequestMapping(value = "/auth/finance/order")
    public String indexOrder(){
        return "finance/order";
    }

    /**
     * List order
     * @param param
     * @return
     */
    @RequestMapping(value = "/auth/finance/order/list")
    @ResponseBody
    public JsonResult ajaxOrderList(PageSearchParam param){
        return orderService.listOrder(param);
    }

    /**
     * Get order detail
     * @param id
     * @return
     */
    @RequestMapping(value = "/auth/finance/order/{id}")
    @ResponseBody
    public JsonResult ajaxOrder(@PathVariable Long id){
        return orderService.getOrderInfoDetail(id);
    }



}
