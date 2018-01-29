package com.practice.app.controller;

import com.practice.result.JsonResult;
import com.practice.service.ActivityService;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Xushd on 2018/1/29 14:12
 */
@RequestMapping(value = "/auth/my")
@RestController
public class MyController {

    @Resource
    private ActivityService activityService;

    /**
     * List my collect
     * @param token
     * @return
     */
    @RequestMapping(value = "/collect/list")
    public JsonResult listCollect(@RequestAttribute String token){

        return activityService.listCollect(token);

    }
}
