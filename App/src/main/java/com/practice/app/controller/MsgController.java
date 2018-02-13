package com.practice.app.controller;

import com.practice.result.JsonResult;
import com.practice.service.MessageService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Xushd  2018/2/13 23:40
 */
@RequestMapping(value = "/app/auth/msg")
@RestController
public class MsgController {

    @Resource
    private MessageService messageService;

    /**
     * List Message
     * @param activityId
     * @param token
     * @param pageIndex
     * @return
     */
    @RequestMapping(value = "/list/{activityId}/{pageIndex}")
    public JsonResult listActivityMsg(@PathVariable Long activityId,
                                      @RequestAttribute String token,
                                      @PathVariable Integer pageIndex){
       return messageService.getMessageList(activityId,pageIndex,token);
    }
}
